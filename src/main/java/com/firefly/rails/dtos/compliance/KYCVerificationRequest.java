package com.firefly.rails.dtos.compliance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KYCVerificationRequest {
    private String customerId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String nationalId;
    private String address;
    private String documentType;
    private String documentNumber;
}
