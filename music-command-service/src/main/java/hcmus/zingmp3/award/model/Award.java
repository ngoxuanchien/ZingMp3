package hcmus.zingmp3.award.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "award_command")
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, columnDefinition = "nvarchar(255)")
    private String name;

//    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "artist_award",
//            joinColumns = @JoinColumn(name = "award_id"),
//            inverseJoinColumns = @JoinColumn(name = "artist_id")
//    )
//    @Builder.Default
//    private List<Artist> artists = new ArrayList<>();
}
