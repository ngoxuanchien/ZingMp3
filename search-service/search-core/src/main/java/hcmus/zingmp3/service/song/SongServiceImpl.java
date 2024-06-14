package hcmus.zingmp3.service.song;

import hcmus.zingmp3.web.model.response.mapper.SongResponseMapper;
import hcmus.zingmp3.web.model.response.SongResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SongServiceImpl implements SongService {

    private final SongQueryService queryService;
    private final SongResponseMapper mapper;

    @Override
    public SongResponse getSongById(UUID id) {
        return mapper.toResponse(queryService.getById(id));
    }

    @Override
    public List<SongResponse> getAllSongs() {
        return mapper.toResponse(queryService.getAll());
    }

    @Override
    public List<SongResponse> getAllSongsByTitle(String title) {
        return mapper.toResponse(queryService.getAllByTitle(title));
    }
}
