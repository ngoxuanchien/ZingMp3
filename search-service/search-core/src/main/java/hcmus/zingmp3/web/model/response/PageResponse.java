package hcmus.zingmp3.web.model.response;

import java.util.List;

public record PageResponse(
        List<?> items,
        boolean hasMore,
        int total
) {

}
