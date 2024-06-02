package hcmus.zingmp3.audio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "song_audio")
public class Audio {
    @Id
    @Column(name = "audio_id")
    private UUID id;
}
