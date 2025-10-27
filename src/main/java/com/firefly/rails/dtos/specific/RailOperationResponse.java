package com.firefly.rails.dtos.specific;
import lombok.Builder; import lombok.Data;
import java.util.Map;
@Data @Builder
public class RailOperationResponse { private Map<String, Object> parameters; }
