package com.firefly.rails.dtos.payments;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class ValidationResponse {
    private boolean valid;
    private List<ValidationError> errors;
    private List<ValidationWarning> warnings;
    
    @Data
    @Builder
    public static class ValidationError {
        private String code;
        private String field;
        private String message;
    }
    
    @Data
    @Builder
    public static class ValidationWarning {
        private String code;
        private String message;
    }
}
