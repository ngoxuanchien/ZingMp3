package hcmus.zingmp3.web.model.response.mapper;

import hcmus.zingmp3.domain.section.AbstractSection;
import hcmus.zingmp3.domain.section.Page;
import hcmus.zingmp3.web.model.response.PageResponse;

import java.util.List;

public class PageMapper {

    public PageResponse toDto(Page entities) {
        return new PageResponse(
                entities.getItems(),
                entities.isHasMore(),
                entities.getTotal()
        );
    }
}
