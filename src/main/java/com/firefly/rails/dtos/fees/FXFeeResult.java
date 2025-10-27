package com.firefly.rails.dtos.fees;

import com.firefly.rails.domain.Money;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class FXFeeResult {
    private BigDecimal exchangeRate;
    private BigDecimal midMarketRate;
    private BigDecimal markup;
    private Money fxFee;
    private Money convertedAmount;
}
