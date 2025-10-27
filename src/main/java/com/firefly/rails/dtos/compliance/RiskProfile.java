package com.firefly.rails.dtos.compliance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RiskProfile {
    private String customerId;
    private RiskLevel riskLevel;
    private String riskScore;
    private boolean isPEP;
    private boolean isHighRiskCountry;
    private String lastReviewDate;
    
    public enum RiskLevel {
        LOW,
        MEDIUM,
        HIGH,
        VERY_HIGH
    }
}
