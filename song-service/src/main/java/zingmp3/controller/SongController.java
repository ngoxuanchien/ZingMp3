package zingmp3.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import zingmp3.dto.SongDto;
import zingmp3.model.Song;
import zingmp3.repository.SongRepository;
import zingmp3.service.SongService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class SongController {
    private final SongRepository songRepository;
    private final SongService songService;

    @QueryMapping
    Optional<Song> getSong(@Argument Integer id) {
        return songRepository.findById(id);
    }

    @QueryMapping
    List<Song> getAllSong() {
        return songRepository.findAll();
    }

    @MutationMapping
    Song addSong(@Argument SongDto songInput) {
        return songService.addNewSong(songInput);
    }

    @MutationMapping
    Song updateSong(@Argument Integer id, @Argument SongDto songInput) {
        return songService.updateSong(id, songInput);
    }

    @MutationMapping
    String deleteSong(@Argument Integer id) {
        songService.deleteSongInService(id);
        return "";
    }
}
