package hcmus.zingmp3.genreevent;

import hcmus.zingmp3.genre.Genre;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Immutable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenreEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String eventType;

    @ManyToOne(fetch = FetchType.EAGER)
    private Genre genre;

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
