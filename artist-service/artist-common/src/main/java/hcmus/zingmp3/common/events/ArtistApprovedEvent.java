package hcmus.zingmp3.common.events;

import hcmus.zingmp3.common.domain.aggregate.Aggregate;
import hcmus.zingmp3.common.domain.model.Artist;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ArtistApprovedEvent extends AbstractEvent {

    public ArtistApprovedEvent(
            final Artist payload
    ) {
        super(null, EventType.ARTIST_APPROVED, payload);
    }

    @Override
    public void apply(Aggregate aggregate) {

    }
}
