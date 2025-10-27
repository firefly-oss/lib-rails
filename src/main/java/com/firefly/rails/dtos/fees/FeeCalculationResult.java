package com.firefly.rails.dtos.fees;

import com.firefly.rails.domain.Money;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class FeeCalculationResult {
    private Money totalFees;
    private Money baseAmount;
    private Money netAmount;
    private List<FeeComponent> feeBreakdown;
    private BigDecimal exchangeRate;
    private Money exchangeRateMargin;
    
    @Data
    @Builder
    public static class FeeComponent {
        private String feeType;
        private Money amount;
        private String description;
    }
}
