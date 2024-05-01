package zingmp3.hcmus.playlistservice;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import zingmp3.hcmus.playlistservice.dto.SongDTO;
import zingmp3.hcmus.playlistservice.dto.StreamingDTO;
import zingmp3.hcmus.playlistservice.service.SongService;

@RequiredArgsConstructor
public class SongServiceTest {

    private final SongService songService;
    @Test
    void testSaveSong() {
        SongDTO songDTO = SongDTO.builder()
                .title("Test song")
                .thumbnail("thumbnail")
                .alias("alias")
                .distributor("distributor")
                .streaming(StreamingDTO.builder().build())
                .build();

        songService.save(songDTO, "playlistId");


    }
}
