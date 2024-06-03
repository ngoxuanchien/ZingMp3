package hcmus.zingmp3.common.events;

import hcmus.zingmp3.common.domain.aggregate.Aggregate;

public interface Event {
    void apply(Aggregate aggregate);
}
