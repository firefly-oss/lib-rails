package com.firefly.rails.dtos.fees;

import com.firefly.rails.domain.*;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class FeeComparisonResult {
    private List<SettlementOption> options;
    
    @Data
    @Builder
    public static class SettlementOption {
        private SettlementSpeed speed;
        private Money totalFees;
        private Money netAmount;
        private String estimatedArrivalTime;
    }
}
