package zingmp3.hcmus.artistservice.model.error;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistError {
    @Id
    private String id;
}
