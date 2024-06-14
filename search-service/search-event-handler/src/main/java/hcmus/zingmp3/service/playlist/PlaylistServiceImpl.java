package hcmus.zingmp3.service.playlist;

import hcmus.zingmp3.domain.model.Playlist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistCommandService commandService;

    @Override
    public Playlist create(Playlist object) {
        return commandService.create(object);
    }

    @Override
    public Playlist update(Playlist object) {
        return commandService.update(object);
    }

    @Override
    public void delete(Playlist object) {
        commandService.delete(object);
    }
}
