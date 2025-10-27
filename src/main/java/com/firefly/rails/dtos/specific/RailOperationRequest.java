package com.firefly.rails.dtos.specific;
import lombok.Builder; import lombok.Data;
import java.util.Map;
@Data @Builder
public class RailOperationRequest { private Map<String, Object> parameters; }
