# Architecture

This document describes the hexagonal architecture of the Firefly Banking Rails library.

## Hexagonal Architecture

The library implements **Ports and Adapters** pattern to separate business logic from external banking rail integrations.

### Core Concepts

**Ports**: Interfaces defining capabilities  
**Adapters**: Rail-specific implementations  
**Domain**: Business logic independent of external systems

```
┌─────────────────────────────────────────┐
│       Application Layer                 │
│  (Business Logic & Use Cases)           │
└──────────────┬──────────────────────────┘
               │ depends on
               ▼
┌─────────────────────────────────────────┐
│       Domain Layer (lib-rails)          │
│                                         │
│  ┌──────────────────────────────────┐  │
│  │ Ports (Interfaces)               │  │
│  │  • RailAdapter                   │  │
│  │  • PaymentRailPort               │  │
│  │  • SettlementPort, etc.          │  │
│  └──────────────────────────────────┘  │
│                                         │
│  ┌──────────────────────────────────┐  │
│  │ Domain Models                    │  │
│  │  • Money, Currency               │  │
│  │  • BankAccount, RailType         │  │
│  └──────────────────────────────────┘  │
│                                         │
│  ┌──────────────────────────────────┐  │
│  │ DTOs                             │  │
│  │  • Request/Response objects      │  │
│  └──────────────────────────────────┘  │
└──────────────┬──────────────────────────┘
               │ implemented by
               ▼
┌─────────────────────────────────────────┐
│    Infrastructure (Implementations)     │
│                                         │
│  ┌───────────┐  ┌───────────┐         │
│  │    ACH    │  │   SWIFT   │  ...    │
│  │  Adapter  │  │  Adapter  │         │
│  └───────────┘  └───────────┘         │
└─────────────────────────────────────────┘
```

## Port Interfaces

### 1. RailAdapter
Main entry point providing access to all ports.

### 2. PaymentRailPort
Payment initiation and management: create, get, cancel, list.

### 3. SettlementPort
Settlement reporting and tracking.

### 4. StatusPort
Real-time payment status inquiry and tracking.

### 5. MandatePort
Direct debit mandate creation and management.

### 6. BulkPaymentPort
Batch/bulk payment submission and tracking.

### 7. ReconciliationPort
Transaction reconciliation and discrepancy detection.

### 8. RailSpecificPort
Extensibility for rail-unique features (e.g., SWIFT MT messages, SEPA instant).

## Design Patterns

### Hexagonal Architecture (Ports & Adapters)
- **Core domain** independent of rails
- **Ports** define contracts
- **Adapters** implement for specific rails

### Repository Pattern
Each port acts as a repository for its domain.

### Strategy Pattern
Different rail implementations are swappable strategies.

### Registry Pattern
Rail-specific operations use a registry for dynamic discovery.

## Package Structure

```
com.firefly.rails
├── adapter/
│   ├── RailAdapter.java
│   └── ports/
│       ├── PaymentRailPort.java
│       ├── SettlementPort.java
│       ├── StatusPort.java
│       └── ...
├── services/
│   └── AbstractRailService.java
├── controllers/
│   └── AbstractPaymentRailController.java
├── domain/
│   ├── Money.java
│   ├── Currency.java
│   ├── BankAccount.java
│   ├── RailType.java
│   └── ...
├── dtos/
│   ├── payments/
│   ├── settlement/
│   ├── status/
│   └── ...
├── exceptions/
│   └── RailException.java
└── config/
    └── RailProperties.java
```

## Data Flow Example

### Payment Initiation

```
1. HTTP Request → Controller
2. Controller → AbstractRailService
3. Service → RailAdapter.payments()
4. PaymentRailPort → Implementation (e.g., ACHPaymentRailPort)
5. Implementation maps request → Calls ACH API
6. Maps response back → Returns PaymentResponse
7. Response → Client
```

## Benefits

### Testability
Easy to mock rail interactions in tests.

### Flexibility
Switch rails without code changes.

### Maintainability
Clear separation of concerns.

### Scalability
Add new rails independently.

### Consistency
Unified API regardless of rail.

## Dependency Rules

**Application → Domain (Ports) ← Infrastructure (Adapters)**

Rules:
1. ✅ Application depends on Domain
2. ✅ Infrastructure depends on Domain
3. ❌ Domain NEVER depends on Infrastructure
4. ❌ Domain NEVER depends on Application

This ensures the domain remains independent and portable.
