package zingmp3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Builder
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class Streaming {
    @Id
    @GeneratedValue
    private UUID id;

    private String url128kps;
    private String url320kps;
}
