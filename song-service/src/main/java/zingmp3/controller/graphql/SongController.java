//package zingmp3.controller.graphql;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.graphql.data.method.annotation.Argument;
//import org.springframework.graphql.data.method.annotation.MutationMapping;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.stereotype.Controller;
//import zingmp3.dto.SongDTO;
//import zingmp3.service.SongService;
//
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//@Slf4j
//public class SongController {
//    private final SongService songService;
//
//    @QueryMapping
//    SongDTO getSongById(@Argument Integer id) {
//        return songService.findById(id);
//    }
//
//    @QueryMapping
//    List<SongDTO> getAllSongs() {
//        return songService.findAll();
//    }
//
//    @MutationMapping
//    SongDTO addSong(@Argument SongDTO songInput) {
//        return songService.createSong(songInput);
//    }
//
//    @MutationMapping
//    SongDTO updateSong(@Argument Integer id, @Argument SongDTO songInput) {
//        return songService.updateSong(id, songInput);
//    }
//
//    @MutationMapping
//    String deleteSong(@Argument Integer id) {
//        songService.delete(id);
//        return "";
//    }
//}
