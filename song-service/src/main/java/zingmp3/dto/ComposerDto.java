package zingmp3.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class ComposerDto {
    private UUID id;
    private String name;
    private String link;
    private boolean spotlight;
    private String alias;
    private String cover;
    private String thumbnail;
    private Integer totalFollow;
}

