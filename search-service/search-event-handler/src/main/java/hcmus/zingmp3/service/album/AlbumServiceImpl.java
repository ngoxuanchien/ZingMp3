package hcmus.zingmp3.service.album;

import hcmus.zingmp3.domain.model.Album;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumCommandService commandService;

    @Override
    public Album create(Album object) {
        return commandService.create(object);
    }

    @Override
    public Album update(Album object) {
        return commandService.update(object);
    }

    @Override
    public void delete(Album object) {
        commandService.delete(object);
    }
}
