package com.firefly.rails.dtos.fees;

import com.firefly.rails.domain.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FXFeeRequest {
    private Money amount;
    private Currency sourceCurrency;
    private Currency targetCurrency;
}
