package com.firefly.rails.domain.railspecific;

import lombok.Builder;
import lombok.Data;

/** ACH-specific payment attributes */
public class ACHSpecific {

    @Data
    @Builder
    public static class ACHPayment {
        /** SEC Code (WEB, PPD, CCD, etc.) */
        private SECCode secCode;
        
        /** Company Entry Description */
        private String companyEntryDescription;
        
        /** Company Discretionary Data */
        private String companyDiscretionaryData;
        
        /** Individual ID Number */
        private String individualIdNumber;
        
        /** Individual Name */
        private String individualName;
        
        /** Addenda records */
        private String addenda;
        
        /** Same-day ACH flag */
        private boolean sameDayAch;
    }

    public enum SECCode {
        WEB,  // Internet-initiated
        PPD,  // Prearranged Payment & Deposit (consumer)
        CCD,  // Corporate Credit or Debit
        TEL,  // Telephone-initiated
        ARC,  // Accounts Receivable Conversion
        BOC,  // Back Office Conversion
        POP,  // Point of Purchase
        RCK,  // Re-presented Check
        CTX,  // Corporate Trade Exchange
        IAT   // International ACH Transaction
    }
}
