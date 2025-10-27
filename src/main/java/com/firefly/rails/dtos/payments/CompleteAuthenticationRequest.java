package com.firefly.rails.dtos.payments;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompleteAuthenticationRequest {
    /** Authorization ID */
    private String authorizationId;
    
    /** Authentication response (OTP, token, etc.) */
    private String authenticationResponse;
    
    /** Device/session information */
    private String sessionId;
}
