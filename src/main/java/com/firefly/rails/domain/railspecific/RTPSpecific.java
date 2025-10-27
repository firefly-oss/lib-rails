package com.firefly.rails.domain.railspecific;

import lombok.Builder;
import lombok.Data;

/** Real-Time Payments (US) specific attributes */
public class RTPSpecific {

    @Data
    @Builder
    public static class RTPPayment {
        /** Request for Payment ID (if responding to RfP) */
        private String requestForPaymentId;
        
        /** Extended remittance information */
        private String extendedRemittanceInfo;
        
        /** Return request capability */
        private boolean returnRequestEnabled;
    }

    @Data
    @Builder
    public static class RequestForPayment {
        private String requestId;
        private String debtorAccountNumber;
        private String creditorAccountNumber;
        private String requestDescription;
        private String dueDate;
    }
}
