package hcmus.zingmp3.genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreRestRequest {
    @NotBlank
    @Size(max = 255)
    private String alias;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String title;

    private Set<UUID> songs;
}
