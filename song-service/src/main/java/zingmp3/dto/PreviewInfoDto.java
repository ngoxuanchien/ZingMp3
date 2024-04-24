package zingmp3.dto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PreviewInfoDto {
    private UUID id;
    private Integer startTime;
    private Integer endTime;
}
