package hcmus.zingmp3.genre.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreRequest {
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String title;

    @NotBlank
    @Size(max = 255)
    private String alias;
}
