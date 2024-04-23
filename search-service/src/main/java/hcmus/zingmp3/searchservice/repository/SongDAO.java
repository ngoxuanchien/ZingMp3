package hcmus.zingmp3.searchservice.repository;

import hcmus.zingmp3.searchservice.dto.SongDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class SongDAO {
    public static final String HASH_KEY = "Song";

    private final RedisTemplate template;

    public SongDTO save(SongDTO songDTO) {
        template.opsForHash().put(HASH_KEY, songDTO.getId(), songDTO);
        log.info("Save success");
        return songDTO;
    }

    public List<SongDTO> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public SongDTO findSongById(int id) {
        return (SongDTO) template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteSong(int id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "Song removed !! " + id;
    }


}
