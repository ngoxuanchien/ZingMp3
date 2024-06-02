package hcmus.zingmp3.artistEvent;

import hcmus.zingmp3.artist.Artist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Immutable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArtistEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String eventType;

    @ManyToOne(fetch = FetchType.EAGER)
    private Artist artist;

//    @CreatedDate
//    @Column(
//            nullable = false,
//            updatable = false
//    )
    private LocalDateTime createDate;

//    @CreatedBy
//    @Column(
//            nullable = false,
//            updatable = false
//    )
    private UUID createdBy;

}
