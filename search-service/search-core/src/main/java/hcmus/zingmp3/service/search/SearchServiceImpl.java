package hcmus.zingmp3.service.search;

import hcmus.zingmp3.domain.model.Song;
import hcmus.zingmp3.repository.elasticsearch.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SongRepository songRepository;

    @Override
    public List<?> search(String query, Pageable pageable) {
        Song song = Song.builder().title(query).build();

        String[] fields = {"title"};

        return songRepository.searchSimilar(song, fields, pageable).getContent();
    }
}
