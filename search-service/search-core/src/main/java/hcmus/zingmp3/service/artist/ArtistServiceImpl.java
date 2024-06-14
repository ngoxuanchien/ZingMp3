package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.web.model.response.ArtistResponse;
import hcmus.zingmp3.web.model.response.mapper.ArtistResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistQueryService queryService;
    private final ArtistResponseMapper mapper;

    @Override
    public ArtistResponse getArtistById(UUID id) {
        return mapper.toResponse(queryService.getById(id));
    }

    @Override
    public List<ArtistResponse> getAllArtists() {
        return mapper.toResponse(queryService.getAll());
    }

    @Override
    public List<ArtistResponse> getAllArtistsByName(String name) {
        return mapper.toResponse(queryService.getAllByName(name));
    }
}
