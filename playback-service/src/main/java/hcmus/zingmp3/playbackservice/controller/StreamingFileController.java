package hcmus.zingmp3.playbackservice.controller;

import hcmus.zingmp3.playbackservice.dto.StreamingFileDTO;
import hcmus.zingmp3.playbackservice.service.StreamingFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/playback/streaming")
public class StreamingFileController {
    private final DataBufferFactory dataBufferFactory;

    private final StreamingFileService streamingFileService;

    @PostMapping
    public Mono<StreamingFileDTO> saveStreamFile(@RequestBody StreamingFileDTO request) {
        return streamingFileService.saveStreamingFile(request);
    }

    @GetMapping("/{id}")
    public Mono<StreamingFileDTO> getStreamFileById(@PathVariable String id) {
        return streamingFileService.getStreamingFileById(id);
    }

    @GetMapping("/test")
    @ResponseBody
    public Flux<DataBuffer> test() {
        List<byte[]> songParts = splitSongIntoParts();
        return Flux.fromIterable(songParts)
                .map(dataBufferFactory::wrap);
    }

    private List<byte[]> splitSongIntoParts() {
        try {
            // Đường dẫn tới file nhạc
            String songPath = "C:\\Users\\ngoxu\\Downloads\\Âm Thầm Bên Em  OFFICIAL MUSIC VIDEO  Sơn Tùng MTP_320kbps.mp3";

            // Đọc file nhạc
            byte[] songBytes = Files.readAllBytes(Paths.get(songPath));

            // Chia nhỏ file nhạc thành các phần nhỏ
            int partSize = 100; // Kích thước của mỗi phần nhỏ
            int songLength = songBytes.length;
            List<byte[]> songParts = new ArrayList<>();

            for (int i = 0; i < songLength; i += partSize) {
                int end = Math.min(i + partSize, songLength);
                byte[] part = Arrays.copyOfRange(songBytes, i, end);
                songParts.add(part);
            }

            return songParts;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
