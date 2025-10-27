package com.firefly.rails.dtos.compliance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DueDiligenceResponse {
    private boolean enhancedDueDiligenceRequired;
    private String reason;
    private String recommendedAction;
}
