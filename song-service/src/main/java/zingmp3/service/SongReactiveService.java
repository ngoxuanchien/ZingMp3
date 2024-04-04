package zingmp3.service;

import io.grpc.StatusRuntimeException;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.crossstore.ChangeSetPersister;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import zing_mp3.ReactorSongServiceGrpc;
import zing_mp3.Song;
import zing_mp3.Status;
import zingmp3.dto.SongDto;
import zingmp3.repository.SongRepository;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@AllArgsConstructor
public class SongReactiveService extends ReactorSongServiceGrpc.SongServiceImplBase {
    private final SongRepository songRepository;
    private final SongService songService;

    private Song convertToSong(zingmp3.model.Song song) {
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

    private SongDto convertToSongDto(Song song) {
        return SongDto.builder()
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
    public Flux<Song> getAllSong(Song request) {
        List<zingmp3.model.Song> songList = songRepository.findAll();

        List<Song> convertedSongList = songList.stream()
                .map(this::convertToSong)
                .toList();

        return Flux.fromIterable(convertedSongList);
    }


    @Override
    public Mono<Song> getSong(Song request) {
        zingmp3.model.Song song = songRepository.findById(request.getId()).orElseThrow();

        return Mono.just(convertToSong(song));
    }

    @Override
    public Mono<Song> addSong(Song request) {
        zingmp3.model.Song song = songService.addNewSong(convertToSongDto(request));
        return Mono.just(convertToSong(song));
    }

    @Override
    public Mono<Song> updateSong(Song request) {
        zingmp3.model.Song song = songRepository.findById(request.getId()).orElseThrow();
        songService.updateSong(request.getId(), convertToSongDto(request));

        return Mono.just(convertToSong(song));
    }

    @Override
    public Mono<Status> deleteSong(Song request) {
        songService.deleteSongInService(request.getId());
        return Mono.just(Status.newBuilder().setMessage("Deleted").build());
    }
}
