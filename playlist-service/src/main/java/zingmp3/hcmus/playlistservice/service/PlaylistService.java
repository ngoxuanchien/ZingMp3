package zingmp3.hcmus.playlistservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zingmp3.hcmus.playlistservice.dto.album.AlbumDTO;
import zingmp3.hcmus.playlistservice.dto.artist.ArtistDTO;
import zingmp3.hcmus.playlistservice.dto.playlist.PlaylistCreationDTO;
import zingmp3.hcmus.playlistservice.dto.playlist.PlaylistDTO;
import zingmp3.hcmus.playlistservice.dto.playlist.PlaylistInListDTO;
import zingmp3.hcmus.playlistservice.dto.song.SongDTO;
import zingmp3.hcmus.playlistservice.entity.Playlist;
import zingmp3.hcmus.playlistservice.entity.Song;
import zingmp3.hcmus.playlistservice.exception.BusinessException;
import zingmp3.hcmus.playlistservice.repository.PlaylistRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public List<PlaylistInListDTO> getAll() {
        // Mock getting user id
        long userId = 1;
        List<Playlist> playlists = playlistRepository.findPlaylistByOwnerId(userId);

        List<PlaylistInListDTO> result = playlists.stream().map(p -> {
            PlaylistInListDTO playlist = new PlaylistInListDTO();
            playlist.setId(p.getId());
            playlist.setName(p.getName());
            playlist.setUpdatedAt(p.getUpdatedAt());
            playlist.setId(p.getId());

            playlist.setOwnerName("Hien");

            return playlist;
        }).collect(Collectors.toList());

        return result;
    }

    public PlaylistDTO getPlaylist(long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new BusinessException("Playlist not found"));

        PlaylistDTO result = new PlaylistDTO();
        result.setId(playlist.getId());
        result.setName(playlist.getName());
        result.setPublic(playlist.isPublic());

        // Mock owner name
        result.setOwnerName("Hien");

        result.setSongsCount(playlist.getSongs().size());

        // Todo: make grpc call to retrieve songs' information
        // Mock
        result.setDuration(3000);
        result.setSongs(playlist.getSongs().stream().map(
                s -> SongDTO.builder()
                        .id(s.getSongId())
                        .artist(new ArtistDTO("Billie Eilish", "https://example.com/billie-eilish"))
                        .thumbnail("https://assets.audiomack.com/mechanicmusic/4b9b9c3df10005e34a1811b773c73c68a87d1e1d4183e98f0e9daf261a5e55d5.jpeg?width=1000&height=1000&max=true")
                        .album(new AlbumDTO(1, "No 1", "url"))
                        .build()
        ).collect(Collectors.toList()));

        return result;
    }

    public void addSong(long playlistId, long songId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new BusinessException("Playlist not found"));

        Song newSong = Song.builder().songId(songId).songOrder(playlist.getSongs().size()).playlist(playlist).build();

        if (playlist.getSongs().contains(newSong)) {
            throw new BusinessException("Song has existed in the playlist");
        }

        // Todo: Make grpc call to check if the song exists

        playlist.getSongs().add(newSong);
        playlistRepository.save(playlist);
    }

    public void removeSong(long playlistId, long songId) {
        // Todo: checking if user is the playlist owner

        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() -> {
            throw new BusinessException("Playlist not found");
        });

        Song song = Song.builder().songId(songId).build();
        if (!playlist.getSongs().contains(song)) {
            throw new BusinessException("Song doesn't exist in this playlist");
        }

        playlist.getSongs().removeIf(song::equals);
        playlistRepository.save(playlist);

    }

    public void arrangeSong(long playlistId, long songId, int newOrder) {
        // Not implemented
    }
    public void create(PlaylistCreationDTO playlistDTO) {
        if (playlistRepository.existsByNameIgnoreCase(playlistDTO.getName())) {
            throw new BusinessException("Playlist's name existed");
        }

        Playlist playlist = new Playlist();
        playlist.setName(playlistDTO.getName());

        // Mock owner id
        playlist.setOwnerId(1);
        playlist.setPublic(playlistDTO.isPublic());
        playlist.setRandomPlaying(playlistDTO.isRandomPlaying());
        playlist.setUpdatedAt(LocalDateTime.now());

        playlistRepository.save(playlist);
    }

    public void update(long playlistId, PlaylistCreationDTO playlistDTO) {
        // Todo: checking if user is playlist owner

        var playlist = playlistRepository.findById(playlistId).orElseThrow(() -> new BusinessException("Playlist not found"));

        playlist.setName(playlistDTO.getName());
        playlist.setPublic(playlistDTO.isPublic());
        playlist.setRandomPlaying(playlistDTO.isRandomPlaying());

        playlistRepository.save(playlist);
    }

    public void deletePlaylist(long playlistId) {
        // Todo: checking if user is playlist owner

        playlistRepository.deleteById(playlistId);
    }
}
