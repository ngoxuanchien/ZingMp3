package zingmp3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String link;
    private boolean spotlight;
    private String alias;
    private String thumbnail;
    private String thumbnailM;
    private boolean isOA;
    private boolean isOABrand;
    private Integer totalFollow;
}
