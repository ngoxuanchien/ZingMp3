package hcmus.zingmp3.common.events.song;

import hcmus.zingmp3.common.domain.aggregate.Aggregate;
import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.events.AbstractEvent;
import hcmus.zingmp3.common.events.EventType;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SongApprovedEvent extends AbstractEvent {

    public SongApprovedEvent(
            final Song payload
    ) {
        super(null, EventType.SONG_APPROVED, payload);
    }

    @Override
    public void apply(Aggregate aggregate) {

    }
}
