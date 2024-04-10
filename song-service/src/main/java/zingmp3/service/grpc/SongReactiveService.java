package zingmp3.service.grpc;

import hcmus.zing_mp3.ReactorSongServiceGrpc;
import hcmus.zing_mp3.Song;
import hcmus.zing_mp3.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zingmp3.dto.SongDTO;
import zingmp3.repository.SongRepository;
import zingmp3.service.SongService;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
@Slf4j
public class SongReactiveService extends ReactorSongServiceGrpc.SongServiceImplBase {
    private final SongRepository songRepository;
    private final SongService songService;



    private Song convertToSong(SongDTO song) {

        return Song.newBuilder()
                .setId(song.getId())
                .setName(song.getSongName())
                .setSongWriter(song.getSongWriter())
                .setThumbnail(song.getThumbnail())
                .setLyric(song.getLyric())
                .setDuration(song.getDuration())
                .setPlayed(song.getPlayed())
                .setLiked(song.getLiked())
                .setProvidedBy(song.getProvidedBy())
                .build();
    }

    private SongDTO convertToSongDto(Song song) {
        return SongDTO.builder()
                .id(song.getId())
                .songName(song.getName())
                .songWriter(song.getSongWriter())
                .lyric(song.getLyric())
                .duration(song.getDuration())
                .thumbnail(song.getThumbnail())
                .providedBy(song.getProvidedBy())
                .liked(song.getLiked())
                .played(song.getPlayed())
                .build();
    }

    @Override
    public Flux<Song> getAllSongs(Song request) {
        List<SongDTO> songList = songService.findAll();
        log.info("Get all song");
        List<Song> convertedSongList = songList.stream()
                .map(this::convertToSong)
                .toList();


        return Flux.fromIterable(convertedSongList);
    }

    @Override
    public Mono<Song> getSongById(Song request) {
        SongDTO song = songService.findById(request.getId());
        log.info("Song id: {}", song.getId());
        return Mono.just(convertToSong(song));
    }

    @Override
    public Mono<Song> addSong(Song request) {
        SongDTO song = songService.createSong(convertToSongDto(request));
        return Mono.just(convertToSong(song));
    }

    @Override
    public Mono<Song> updateSong(Song request) {
        SongDTO songDTO = songService.updateSong(request.getId(), convertToSongDto(request));

        return Mono.just(convertToSong(songDTO));
    }

    @Override
    public Mono<Status> deleteSong(Song request) {
        songService.delete(request.getId());
        return Mono.just(Status.newBuilder().setMessage("Deleted").build());
    }
}
