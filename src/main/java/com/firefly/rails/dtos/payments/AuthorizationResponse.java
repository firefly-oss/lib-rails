package com.firefly.rails.dtos.payments;

import com.firefly.rails.domain.*;
import lombok.Builder;
import lombok.Data;
import java.time.Instant;

@Data
@Builder
public class AuthorizationResponse {
    /** Authorization ID (used for confirmation) */
    private String authorizationId;
    
    /** Authorization status */
    private AuthorizationStatus status;
    
    /** Whether SCA is required */
    private boolean scaRequired;
    
    /** Authentication challenge (if SCA required) */
    private AuthenticationChallenge authenticationChallenge;
    
    /** Reserved amount */
    private Money reservedAmount;
    
    /** Authorization expiry time */
    private Instant expiresAt;
    
    /** Additional information */
    private String message;
    
    public enum AuthorizationStatus {
        AUTHORIZED,
        PENDING_AUTHENTICATION,
        DECLINED,
        EXPIRED
    }
    
    @Data
    @Builder
    public static class AuthenticationChallenge {
        private AuthenticationMethod method;
        private String challengeToken;
        private String redirectUrl;
        private String qrCode;
        private Instant expiresAt;
    }
}
