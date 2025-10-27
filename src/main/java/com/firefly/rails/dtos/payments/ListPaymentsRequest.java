package com.firefly.rails.dtos.payments;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class ListPaymentsRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer limit;
    private Integer offset;
}
