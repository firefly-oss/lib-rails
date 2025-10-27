package com.firefly.rails.dtos.payments;

import com.firefly.rails.domain.Money;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.math.BigDecimal;

@Data
@Builder
public class SimulationResponse {
    private Money totalAmount;
    private Money fees;
    private Money netAmount;
    private BigDecimal exchangeRate;
    private LocalDate expectedSettlementDate;
    private LocalDate valueDate;
    private String estimatedArrivalTime;
    private boolean isValid;
    private String warningMessage;
}
