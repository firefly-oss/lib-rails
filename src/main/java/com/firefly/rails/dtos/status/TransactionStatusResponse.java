package com.firefly.rails.dtos.status;
import lombok.Builder; import lombok.Data;
@Data @Builder
public class TransactionStatusResponse { private String id; private String status; }
