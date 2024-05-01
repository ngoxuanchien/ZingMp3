package zingmp3.hcmus.playlistservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class ArtistDTO {

    private String id;
    private String name;
    private String link;
    private String alias;
    private UUID playlistId;
    private String thumbnail;
    private int totalFollow;
    private Set<AwardDTO> awards;
    private String biography;
    private String sortBiography;
    private String national;
    private String realName;
    private LocalDate birthday;
}
