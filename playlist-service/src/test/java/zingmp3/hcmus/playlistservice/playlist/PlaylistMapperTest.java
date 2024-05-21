package zingmp3.hcmus.playlistservice.playlist;

import hcmus.zingmp3.SongServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import zingmp3.hcmus.playlistservice.playlist.model.Playlist;
import zingmp3.hcmus.playlistservice.playlist.model.PlaylistRequest;
import zingmp3.hcmus.playlistservice.playlist.model.PlaylistResponse;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PlaylistMapperTest {

    @GrpcClient("music")
    SongServiceGrpc.SongServiceBlockingStub songService;

    PlaylistMapper playlistMapper;

    @BeforeEach
    void setUp() {
        playlistMapper = new PlaylistMapper();
    }

    @Test
    void toDTO() {

    }

    @Test
    void toEntity() {
        PlaylistRequest request = PlaylistRequest.builder()
                .title("Những bài hát hay nhất của Sơn Tùng M-TP")
                .alias("nhung-bai-hat-hay-nhat-cua-son-tung-m-tp")
                .isOfficial(true)
                .isIndie(false)
                .releaseDate("2021-01-01")
                .releasedAt(0)
                .sortDescription("Những bài hát hay nhất của Sơn Tùng M-TP")
                .description("Những bài hát hay nhất của Sơn Tùng M-TP")
                .artistNames("Sơn Tùng M-TP")
                .isPrivate(false)
                .isAlbum(false)
                .isSingle(false)
                .distributor("Zing MP3")
                .songs(Set.of("1", "2", "3"))
                .artists(Set.of("Son-Tung-M-TP"))
                .genres(Set.of("Nhac-Tre"))
                .build();

        Playlist entity = Playlist.builder()
                .title("Những bài hát hay nhất của Sơn Tùng M-TP")
                .alias("nhung-bai-hat-hay-nhat-cua-son-tung-m-tp")
                .isOfficial(true)
                .isIndie(false)
                .releaseDate("2021-01-01")
                .releasedAt(0)
                .sortDescription("Những bài hát hay nhất của Sơn Tùng M-TP")
                .description("Những bài hát hay nhất của Sơn Tùng M-TP")
                .artistNames("Sơn Tùng M-TP")
                .isPrivate(false)
                .isAlbum(false)
                .isSingle(false)
                .distributor("Zing MP3")
                .build();
    }
}