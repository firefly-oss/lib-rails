package com.firefly.rails.dtos.compliance;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class SanctionsScreeningResponse {
    private boolean match;
    private double matchScore;
    private List<SanctionMatch> matches;
    
    @Data
    @Builder
    public static class SanctionMatch {
        private String listName;
        private String matchedName;
        private String sanctionType;
        private double confidence;
    }
}
