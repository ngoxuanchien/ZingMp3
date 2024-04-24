package zingmp3.service.impl;

import org.springframework.stereotype.Service;
import zingmp3.dto.StreamingDto;
import zingmp3.service.StreamingService;

import java.util.List;
import java.util.UUID;

@Service
public class StreamingServiceImpl implements StreamingService {
    @Override
    public StreamingDto findById(UUID id) {
        return null;
    }

    @Override
    public List<StreamingDto> findAll() {
        return List.of();
    }

    @Override
    public StreamingDto create(StreamingDto streamingRecord) {
        return null;
    }

    @Override
    public StreamingDto update(UUID id, StreamingDto streamingRecord) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
