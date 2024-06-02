package hcmus.zingmp3.artist;

import com.fasterxml.jackson.annotation.JsonFormat;
//import hcmus.zingmp3.award.model.AwardRestRequest;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistRestRequest {
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String alias;

    private UUID thumbnail;

    @Hidden
    @Min(0)
    @Builder.Default
    private long totalFollow = 0;
    @Size(max = 255)
    private String sortBiography;
    @Size(max = 10000)
    private String biography;
    @Size(max = 100)
    private String national;
    @Size(max = 100)
    private String realName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Builder.Default
    private Set<UUID> awards = new HashSet<>();
}
