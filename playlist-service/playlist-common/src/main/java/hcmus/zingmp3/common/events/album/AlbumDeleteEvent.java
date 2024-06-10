package hcmus.zingmp3.common.events.album;

import hcmus.zingmp3.common.domain.aggregate.Aggregate;
import hcmus.zingmp3.common.domain.model.Album;
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
public class AlbumDeleteEvent extends AbstractEvent {

    public AlbumDeleteEvent(
            final Album payload
    ) {
        super(null, EventType.ALBUM_DELETE, payload);
    }

    @Override
    public void apply(Aggregate aggregate) {

    }
}