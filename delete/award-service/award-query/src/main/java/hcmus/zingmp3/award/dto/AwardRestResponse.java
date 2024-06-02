package hcmus.zingmp3.award.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AwardRestResponse {
    private UUID id;
    private String name;
    private Set<UUID> artists;
}
