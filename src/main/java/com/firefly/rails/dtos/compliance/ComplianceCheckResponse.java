package com.firefly.rails.dtos.compliance;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ComplianceCheckResponse {
    private boolean approved;
    private ComplianceStatus status;
    private List<String> flags;
    private String riskLevel;
    private boolean requiresManualReview;
    private String reason;
    
    public enum ComplianceStatus {
        APPROVED,
        REJECTED,
        MANUAL_REVIEW,
        ENHANCED_DUE_DILIGENCE_REQUIRED
    }
}
