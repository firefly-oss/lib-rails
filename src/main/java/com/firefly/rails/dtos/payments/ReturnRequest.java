package com.firefly.rails.dtos.payments;
import lombok.Builder; import lombok.Data;
@Data @Builder
public class ReturnRequest { private String id; private String status; }
