# Enhanced Features - lib-rails

## Overview

This document describes the advanced features in lib-rails that handle real-world payment scenarios including simulation, validation, two-phase commits, and Strong Customer Authentication (SCA).

## Table of Contents

1. [Validation & Simulation](#validation--simulation)
2. [Two-Phase Commit (Authorization & Confirmation)](#two-phase-commit)
3. [Strong Customer Authentication (SCA)](#strong-customer-authentication)
4. [Rail-Specific Models](#rail-specific-models)
5. [Payment Modes](#payment-modes)

---

## Validation & Simulation

### Pre-Flight Validation

Validate a payment **before** execution to catch errors early:

```java
ValidatePaymentRequest request = ValidatePaymentRequest.builder()
    .amount(new Money(BigDecimal.valueOf(1000), Currency.EUR))
    .debtorAccount(debtorAccount)
    .creditorAccount(creditorAccount)
    .transactionType(TransactionType.CREDIT_TRANSFER)
    .checkBalance(true)
    .validateBeneficiary(true)
    .build();

ValidationResponse validation = railAdapter.payments()
    .validatePayment(request)
    .map(ResponseEntity::getBody)
    .block();

if (!validation.isValid()) {
    validation.getErrors().forEach(error -> 
        System.out.println(error.getField() + ": " + error.getMessage())
    );
}
```

### Payment Simulation

Simulate execution to get fees, exchange rates, and settlement dates:

```java
SimulatePaymentRequest request = SimulatePaymentRequest.builder()
    .amount(new Money(BigDecimal.valueOf(5000), Currency.USD))
    .debtorAccount(debtorAccount)
    .creditorAccount(creditorAccount)
    .transactionType(TransactionType.WIRE_TRANSFER)
    .settlementSpeed(SettlementSpeed.INSTANT)
    .build();

SimulationResponse simulation = railAdapter.payments()
    .simulatePayment(request)
    .map(ResponseEntity::getBody)
    .block();

System.out.println("Total amount: " + simulation.getTotalAmount());
System.out.println("Fees: " + simulation.getFees());
System.out.println("Settlement date: " + simulation.getExpectedSettlementDate());
```

---

## Two-Phase Commit

### Phase 1: Authorization

Authorize a payment (reserve funds, perform checks) without executing:

```java
AuthorizePaymentRequest request = AuthorizePaymentRequest.builder()
    .amount(new Money(BigDecimal.valueOf(2500), Currency.GBP))
    .debtorAccount(debtorAccount)
    .creditorAccount(creditorAccount)
    .transactionType(TransactionType.CREDIT_TRANSFER)
    .authenticationContext(AuthenticationContext.withOtp(otp, callbackUrl))
    .build();

AuthorizationResponse authorization = railAdapter.payments()
    .authorizePayment(request)
    .map(ResponseEntity::getBody)
    .block();

if (authorization.isScaRequired()) {
    // Handle SCA flow (see next section)
    handleSCA(authorization.getAuthenticationChallenge());
} else {
    // Authorization successful, proceed to confirmation
    String authId = authorization.getAuthorizationId();
}
```

### Phase 2: Confirmation

Confirm and execute the authorized payment:

```java
// After successful authorization
PaymentResponse payment = railAdapter.payments()
    .confirmPayment(authorizationId)
    .map(ResponseEntity::getBody)
    .block();

System.out.println("Payment executed: " + payment.getPaymentId());
```

### Cancel Authorization

Cancel before confirmation (release reserved funds):

```java
CancellationResponse cancellation = railAdapter.payments()
    .cancelAuthorization(authorizationId)
    .map(ResponseEntity::getBody)
    .block();
```

---

## Strong Customer Authentication (SCA)

### SCA Methods Supported

- `SMS_OTP` - SMS One-Time Password
- `PUSH_NOTIFICATION` - Mobile app push
- `BIOMETRIC` - Fingerprint, face recognition
- `THREE_DS` / `THREE_DS_2` - 3D Secure for cards
- `OAUTH_REDIRECT` - OAuth/OIDC flow
- `DECOUPLED` - Asynchronous authentication

### SCA Flow Example

#### 1. Authorization with SCA Challenge

```java
AuthorizePaymentRequest request = AuthorizePaymentRequest.builder()
    .amount(amount)
    .debtorAccount(account)
    .creditorAccount(beneficiary)
    .authenticationContext(
        AuthenticationContext.withRedirect(redirectUrl, callbackUrl)
    )
    .build();

AuthorizationResponse auth = railAdapter.payments()
    .authorizePayment(request)
    .map(ResponseEntity::getBody)
    .block();

if (auth.isScaRequired()) {
    AuthenticationChallenge challenge = auth.getAuthenticationChallenge();
    
    switch (challenge.getMethod()) {
        case SMS_OTP:
            // Send OTP to customer
            sendOTP(challenge.getChallengeToken());
            break;
            
        case OAUTH_REDIRECT:
            // Redirect customer to bank
            return "redirect:" + challenge.getRedirectUrl();
            
        case THREE_DS_2:
            // Initiate 3DS flow
            initiate3DS(challenge.getRedirectUrl());
            break;
    }
}
```

#### 2. Complete Authentication

After customer provides authentication:

```java
CompleteAuthenticationRequest request = CompleteAuthenticationRequest.builder()
    .authorizationId(authorizationId)
    .authenticationResponse(customerOtp) // or token from redirect callback
    .sessionId(sessionId)
    .build();

AuthorizationResponse updatedAuth = railAdapter.payments()
    .completeAuthentication(request)
    .map(ResponseEntity::getBody)
    .block();

if (updatedAuth.getStatus() == AuthorizationStatus.AUTHORIZED) {
    // Proceed to confirmation
    confirmPayment(updatedAuth.getAuthorizationId());
}
```

### SCA Exemptions

Request SCA exemption for low-value/low-risk transactions:

```java
AuthorizePaymentRequest request = AuthorizePaymentRequest.builder()
    .amount(new Money(BigDecimal.valueOf(30), Currency.EUR)) // < €30
    .debtorAccount(account)
    .creditorAccount(beneficiary)
    .requestScaExemption(true)
    .authenticationContext(
        AuthenticationContext.withExemption("Low value transaction")
    )
    .build();
```

---

## Rail-Specific Models

### SEPA-Specific

```java
import com.firefly.rails.domain.railspecific.SEPASpecific.*;

SEPACreditTransfer sepaDetails = SEPACreditTransfer.builder()
    .scheme(SEPAScheme.SCT_INST) // Instant SEPA
    .purposeCode("SALA") // Salary payment
    .creditorReference("RF18539007547034")
    .build();

// For Direct Debit
SEPADirectDebit sepaDD = SEPADirectDebit.builder()
    .scheme(SEPAScheme.SDD_CORE)
    .mandateReference("MANDATE-12345")
    .mandateSignatureDate("2024-01-15")
    .sequenceType(SequenceType.RCUR) // Recurring
    .creditorId("DE98ZZZ09999999999")
    .build();
```

### ACH-Specific (US)

```java
import com.firefly.rails.domain.railspecific.ACHSpecific.*;

ACHPayment achDetails = ACHPayment.builder()
    .secCode(SECCode.WEB) // Internet-initiated
    .companyEntryDescription("PAYROLL")
    .individualName("JOHN DOE")
    .sameDayAch(true) // Same-day processing
    .build();
```

### SWIFT-Specific

```java
import com.firefly.rails.domain.railspecific.SWIFTSpecific.*;

SWIFTPayment swiftDetails = SWIFTPayment.builder()
    .messageType("MT103") // Customer credit transfer
    .chargesInstruction(ChargesInstruction.SHA) // Shared charges
    .intermediaryBank(IntermediaryBank.builder()
        .swiftCode("CHASUS33")
        .name("JP Morgan Chase")
        .build())
    .regulatoryReporting("//BENEFRES/US/12345678")
    .build();
```

### FPS-Specific (UK)

```java
import com.firefly.rails.domain.railspecific.FPSSpecific.*;

FPSPayment fpsDetails = FPSPayment.builder()
    .sortCode("12-34-56")
    .accountNumber("12345678")
    .confirmationOfPayee(ConfirmationOfPayee.builder()
        .result(CoPResult.MATCH)
        .matchedName("ACME LTD")
        .build())
    .build();
```

### RTP-Specific (US Real-Time Payments)

```java
import com.firefly.rails.domain.railspecific.RTPSpecific.*;

RTPPayment rtpDetails = RTPPayment.builder()
    .requestForPaymentId("RFP-98765") // If responding to RfP
    .extendedRemittanceInfo("Invoice #INV-2024-001")
    .returnRequestEnabled(true)
    .build();
```

---

## Payment Modes

### Simulation Mode

Execute in simulation mode to test without real money movement:

```java
InitiatePaymentRequest request = InitiatePaymentRequest.builder()
    .amount(amount)
    .debtorAccount(account)
    .creditorAccount(beneficiary)
    .transactionType(TransactionType.CREDIT_TRANSFER)
    .paymentMode(PaymentMode.SIMULATION) // <<< Simulation mode
    .build();

PaymentResponse response = railAdapter.payments()
    .initiatePayment(request)
    .map(ResponseEntity::getBody)
    .block();

// Response will indicate simulation results without actual execution
```

### Live Mode (Default)

```java
InitiatePaymentRequest request = InitiatePaymentRequest.builder()
    .amount(amount)
    .debtorAccount(account)
    .creditorAccount(beneficiary)
    .paymentMode(PaymentMode.LIVE) // Real execution
    .build();
```

---

## Complete Example: SEPA Instant Payment with SCA

```java
@Service
public class PaymentService {
    
    @Autowired
    private RailAdapter railAdapter;
    
    public Mono<PaymentResponse> executeSepaInstantWithSCA(
            PaymentRequest request, 
            String callbackUrl) {
        
        // 1. Validate first
        return railAdapter.payments()
            .validatePayment(createValidationRequest(request))
            .flatMap(validationResp -> {
                if (!validationResp.getBody().isValid()) {
                    return Mono.error(new ValidationException("Invalid payment"));
                }
                
                // 2. Simulate to get fees
                return railAdapter.payments()
                    .simulatePayment(createSimulationRequest(request));
            })
            .flatMap(simulationResp -> {
                // Show fees to user, then authorize
                AuthorizePaymentRequest authRequest = AuthorizePaymentRequest.builder()
                    .amount(request.getAmount())
                    .debtorAccount(request.getDebtorAccount())
                    .creditorAccount(request.getCreditorAccount())
                    .transactionType(TransactionType.CREDIT_TRANSFER)
                    .settlementSpeed(SettlementSpeed.INSTANT)
                    .authenticationContext(
                        AuthenticationContext.withRedirect(
                            "https://bank.com/sca", 
                            callbackUrl
                        )
                    )
                    .build();
                
                // 3. Authorize (may trigger SCA)
                return railAdapter.payments().authorizePayment(authRequest);
            })
            .flatMap(authResp -> {
                AuthorizationResponse auth = authResp.getBody();
                
                if (auth.isScaRequired()) {
                    // Handle SCA flow - redirect user
                    // After SCA completion (in callback), confirm payment
                    return Mono.empty(); // Return to user for SCA
                } else {
                    // 4. Confirm and execute
                    return railAdapter.payments()
                        .confirmPayment(auth.getAuthorizationId())
                        .map(ResponseEntity::getBody);
                }
            });
    }
}
```

---

## Summary

The lib-rails library provides comprehensive support for real-world payment scenarios:

✅ **Validation** - Pre-flight checks before execution  
✅ **Simulation** - Test payments without execution  
✅ **Two-Phase Commit** - Authorization + Confirmation  
✅ **SCA Compliance** - Full PSD2/regulatory support  
✅ **Rail-Specific Models** - SEPA, ACH, SWIFT, FPS, RTP details  
✅ **Payment Modes** - Simulation vs Live execution  
✅ **Exemption Handling** - SCA exemptions for low-risk transactions  

All features are designed to work across **all supported rails** with rail-specific implementations handling the details.
