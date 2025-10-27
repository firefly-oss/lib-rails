package com.firefly.rails.dtos.compliance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SanctionsScreeningRequest {
    private String name;
    private String address;
    private String country;
    private String dateOfBirth;
    private String nationalId;
}
