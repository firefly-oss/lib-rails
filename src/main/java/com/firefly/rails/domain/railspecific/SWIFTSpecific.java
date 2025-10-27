package com.firefly.rails.domain.railspecific;

import lombok.Builder;
import lombok.Data;

/** SWIFT-specific payment attributes */
public class SWIFTSpecific {

    @Data
    @Builder
    public static class SWIFTPayment {
        /** SWIFT message type (MT103, MT202, etc.) */
        private String messageType;
        
        /** Bank Operation Code */
        private String bankOperationCode;
        
        /** Instruction Code */
        private String instructionCode;
        
        /** Sender to Receiver Information */
        private String senderToReceiverInfo;
        
        /** Regulatory Reporting */
        private String regulatoryReporting;
        
        /** Remittance Information */
        private String remittanceInformation;
        
        /** Intermediary bank details */
        private IntermediaryBank intermediaryBank;
        
        /** Charges instruction (OUR, SHA, BEN) */
        private ChargesInstruction chargesInstruction;
    }

    @Data
    @Builder
    public static class IntermediaryBank {
        private String swiftCode;
        private String name;
        private String accountNumber;
    }

    public enum ChargesInstruction {
        OUR,  // All charges paid by sender
        SHA,  // Shared charges
        BEN   // All charges paid by beneficiary
    }
}
