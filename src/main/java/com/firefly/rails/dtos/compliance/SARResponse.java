package com.firefly.rails.dtos.compliance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SARResponse {
    private String sarId;
    private boolean filed;
    private String filingReference;
}
