package zingmp3.service;

import org.springframework.stereotype.Service;
import zingmp3.dto.StreamingDto;

import java.util.List;
import java.util.UUID;

public interface StreamingService {
    StreamingDto findById(UUID id);
    List<StreamingDto> findAll();
    StreamingDto create(StreamingDto streamingRecord);
    StreamingDto update(UUID id, StreamingDto streamingRecord);
    void delete(UUID id);
}
