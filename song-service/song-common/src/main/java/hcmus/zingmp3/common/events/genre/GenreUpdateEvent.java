package hcmus.zingmp3.common.events.genre;

import hcmus.zingmp3.common.domain.aggregate.Aggregate;
import hcmus.zingmp3.common.domain.model.Genre;
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
public class GenreUpdateEvent extends AbstractEvent {
    public GenreUpdateEvent(
            final Genre payload
    ) {
        super(null, EventType.GENRE_UPDATE, payload);
    }

    @Override
    public void apply(Aggregate aggregate) {

    }
}
