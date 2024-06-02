package hcmus.zingmp3.songevent;

import hcmus.zingmp3.song.Song;
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
public class SongEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String eventType;

    @ManyToOne(fetch = FetchType.EAGER)
    private Song song;

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
