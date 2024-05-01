package zingmp3.hcmus.artistservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ArtistOrComposer {
    @Id
    private String id;
}
