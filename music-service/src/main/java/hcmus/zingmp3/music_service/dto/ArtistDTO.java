package hcmus.zingmp3.music_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDTO {
    private String id;
    private String name;
    private String link;
    private String alias;
    private String playlistId;
    private String thumbnail;
    private int totalFollow;
    private Set<AwardDTO> awards;
    private String biography;
    private String sortBiography;
    private String national;
    private String realName;
    private LocalDate birthday;
}
