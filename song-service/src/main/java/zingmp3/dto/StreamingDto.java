package zingmp3.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class StreamingDto {
    private UUID id;
    @JsonProperty("128")
    private String url128kps;

    @JsonProperty("320")
    private String url320kps;
}