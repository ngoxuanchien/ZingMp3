package hcmus.zingmp3.searchservice.service;


import hcmus.zing_mp3.Song;
import hcmus.zing_mp3.SongServiceGrpc;
import hcmus.zing_mp3.Status;
import hcmus.zingmp3.searchservice.dto.SongDTO;
import hcmus.zingmp3.searchservice.repository.SongDAO;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {
    @GrpcClient("song-service")
    SongServiceGrpc.SongServiceBlockingStub synchronousClient;

    @GrpcClient("song-service")
    SongServiceGrpc.SongServiceStub asynchronousClient;

    private final SongDAO songDAO;

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

    public SongDTO getSongById(Integer songId) {
        Song songRequest = Song.newBuilder().setId(songId).build();
        Song songResponse = synchronousClient.getSongById(songRequest);
        return convertToSongDto(songResponse);
    }

    public List<SongDTO> getAllSongs() throws InterruptedException {
        final Song songRequest = Song.newBuilder().build();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final List<SongDTO> songsResponse = new ArrayList<>();
        log.info("GET all Songs");
        asynchronousClient.getAllSongs(songRequest, new StreamObserver<Song>() {
            @Override
            public void onNext(Song song) {
                songDAO.save(convertToSongDto(song));
                songsResponse.add(convertToSongDto(song));
            }

            @Override
            public void onError(Throwable throwable) {
                countDownLatch.countDown();
            }

            @Override
            public void onCompleted() {
                countDownLatch.countDown();
            }
        });

        System.out.println(songsResponse.size());

        boolean await = countDownLatch.await(1, TimeUnit.MINUTES);
        return await ? songsResponse: Collections.emptyList();
    }


    public SongDTO createSong(SongDTO request) {
        final Song songRequest = convertToSong(request);
        Song songResponse = synchronousClient.addSong(songRequest);
        return convertToSongDto(songResponse);
    }

    public SongDTO updateSong(Integer songId, SongDTO request) {
        request.setId(songId);
        final Song songRequest = convertToSong(request);
        Song songResponse = synchronousClient.updateSong(songRequest);
        return convertToSongDto(songResponse);
    }

    public void deleteSong(Integer songId) {
        final Song songRequest = Song.newBuilder().setId(songId).build();
        Status status = synchronousClient.deleteSong(songRequest);
    }
}
