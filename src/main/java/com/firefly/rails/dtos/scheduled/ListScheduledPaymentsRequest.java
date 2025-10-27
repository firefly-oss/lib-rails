package com.firefly.rails.dtos.scheduled;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class ListScheduledPaymentsRequest {
    private LocalDate fromDate;
    private LocalDate toDate;
    private String status;
    private Integer limit;
    private Integer offset;
}
