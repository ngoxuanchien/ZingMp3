package hcmus.zingmp3.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    private static Integer currentId = 1;
    @Builder.Default
    private Integer id = currentId++;
    private String name;
    private String link;
    private String alias;
    private UUID playlistId;
    private String thumbnail;
    private int totalFollow;
    private Set<Award> awards;
    private String biography;
    private String sortBiography;
    private String national;
    private String realName;
    private LocalDate birthday;
}
