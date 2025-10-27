package com.firefly.rails.domain.railspecific;

import lombok.Builder;
import lombok.Data;

/** Faster Payments (UK) specific attributes */
public class FPSSpecific {

    @Data
    @Builder
    public static class FPSPayment {
        /** Sort code */
        private String sortCode;
        
        /** Account number */
        private String accountNumber;
        
        /** Payment scheme (FPS) */
        private String paymentScheme;
        
        /** Confirmation of Payee (CoP) result */
        private ConfirmationOfPayee confirmationOfPayee;
    }

    @Data
    @Builder
    public static class ConfirmationOfPayee {
        private CoPResult result;
        private String matchedName;
        private String reason;
    }

    public enum CoPResult {
        MATCH,
        NO_MATCH,
        CLOSE_MATCH,
        NOT_CHECKED
    }
}
