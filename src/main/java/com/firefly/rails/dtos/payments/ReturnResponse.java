package com.firefly.rails.dtos.payments;
import lombok.Builder; import lombok.Data;
@Data @Builder
public class ReturnResponse { private String id; private String status; }
