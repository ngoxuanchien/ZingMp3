package hcmus.zingmp3.service.album;

import hcmus.zingmp3.web.model.response.mapper.AlbumResponseMapper;
import hcmus.zingmp3.web.model.response.AlbumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumQueryService queryService;
    private final AlbumResponseMapper mapper;

    @Override
    public AlbumResponse getAlbumById(UUID id) {
        return mapper.toResponse(queryService.getById(id));
    }

    @Override
    public List<AlbumResponse> getAllAlbums() {
        return mapper.toResponse(queryService.getAll());
    }

    @Override
    public List<AlbumResponse> getAllAlbumsByTitle(String title) {
        return mapper.toResponse(queryService.getAlbumsByTitle(title));
    }
}
