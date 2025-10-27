package com.firefly.rails.dtos.compliance;

import com.firefly.rails.domain.Money;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DueDiligenceRequest {
    private String customerId;
    private Money amount;
    private String destinationCountry;
    private String purpose;
}
