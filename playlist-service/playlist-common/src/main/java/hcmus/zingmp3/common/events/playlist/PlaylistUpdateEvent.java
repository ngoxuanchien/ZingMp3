package hcmus.zingmp3.common.events.playlist;

import hcmus.zingmp3.common.domain.aggregate.Aggregate;
import hcmus.zingmp3.common.domain.model.Playlist;
import hcmus.zingmp3.common.events.AbstractEvent;
import hcmus.zingmp3.common.events.EventType;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PlaylistUpdateEvent extends AbstractEvent {

    public PlaylistUpdateEvent(
            final Playlist payload
    ) {
        super(null, EventType.PLAYLIST_UPDATE, payload);
    }

    @Override
    public void apply(Aggregate aggregate) {

    }
}
