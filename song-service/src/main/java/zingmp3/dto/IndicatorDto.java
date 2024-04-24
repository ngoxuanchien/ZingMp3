package zingmp3.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class IndicatorDto {
    private UUID id;
}
