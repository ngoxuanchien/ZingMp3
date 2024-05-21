package hcmus.zingmp3.music_service.artist.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistRequest {
    @Size(max = 255)
    private String name;
    @NotBlank
    @Size(max = 255)
    private String alias;
//    @UUID
//    private String playlistId;
    @UUID
    @Hidden
    private String thumbnail;
    @Hidden
    @Min(0)
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

    @Hidden
    private Set<String> awards = new HashSet<>();

    @JsonIgnore
    private MultipartFile thumbnailFile;

    public void addAllAwards(Set<String> awards) {
        if (awards == null) {
            return;
        }
        this.awards.addAll(awards);
    }

}
