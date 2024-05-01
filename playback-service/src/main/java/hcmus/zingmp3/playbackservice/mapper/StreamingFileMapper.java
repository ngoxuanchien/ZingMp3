package hcmus.zingmp3.playbackservice.mapper;

import hcmus.zingmp3.playbackservice.dto.StreamingFileDTO;
import hcmus.zingmp3.playbackservice.entity.StreamingFileEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class StreamingFileMapper {
    public StreamingFileDTO toDTO(StreamingFileEntity entity) {
        StreamingFileDTO dto = new StreamingFileDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public StreamingFileEntity toEntity(StreamingFileDTO dto) {
        StreamingFileEntity entity = new StreamingFileEntity();
        BeanUtils.copyProperties(dto, entity, "id");
        return entity;
    }
}
