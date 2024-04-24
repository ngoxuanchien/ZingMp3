package zingmp3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Composer {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String link;
    private boolean spotlight;
    private String alias;
    private String cover;
    private String thumbnail;
    private Integer totalFollow;
}
