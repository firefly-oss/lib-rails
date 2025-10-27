package com.firefly.rails.dtos.compliance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KYCVerificationResponse {
    private boolean verified;
    private String verificationLevel;
    private String failureReason;
    private boolean documentVerified;
    private boolean addressVerified;
    private boolean identityVerified;
}
