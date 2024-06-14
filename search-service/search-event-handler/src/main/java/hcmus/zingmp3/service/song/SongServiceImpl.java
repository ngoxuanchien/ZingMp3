package hcmus.zingmp3.service.song;

import hcmus.zingmp3.domain.model.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongCommandService commandService;

    @Override
    public Song create(Song object) {
        return commandService.create(object);
    }

    @Override
    public Song update(Song object) {
        return commandService.update(object);
    }

    @Override
    public void delete(Song object) {
        commandService.delete(object);
    }
}
