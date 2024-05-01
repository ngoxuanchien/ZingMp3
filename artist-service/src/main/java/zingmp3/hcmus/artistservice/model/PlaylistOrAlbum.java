package zingmp3.hcmus.artistservice.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistOrAlbum {
    @Id
    private String id;
}
