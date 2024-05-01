package hcmus.zingmp3.playbackservice.service.impl;

import hcmus.zingmp3.playbackservice.dto.SongImageDTO;
import hcmus.zingmp3.playbackservice.entity.SongImageEntity;
import hcmus.zingmp3.playbackservice.mapper.SongImageMapper;
import hcmus.zingmp3.playbackservice.repository.SongImageRepository;
import hcmus.zingmp3.playbackservice.service.SongImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongImageServiceImpl implements SongImageService {
    private final SongImageRepository songImageRepository;
    private final SongImageMapper songImageMapper;

    @Override
    public Image viewById(String id) {
        return null;
    }

    @Override
    public byte[] loadImage(String id) throws IOException {
        Optional<SongImageEntity> songImage = songImageRepository
                .findById(id)
                .blockOptional();
        if (songImage.isPresent()) {
            String filePath = songImage.get().getFilePath();
            return Files.readAllBytes(new File(filePath).toPath());

        } else {
            return Files.readAllBytes(new File("D:\\HCMUS\\Term8\\DoAnCongNghePhanMem\\ZingMp3\\Backend\\ZingMp3\\data\\song\\thumbnail\\Ai-La-Em-Beat-Duong-Edward-SlimV.jpg").toPath());
        }
    }

    @Override
    public Mono<SongImageDTO> uploadImage(SongImageDTO request) {
        File file = new File("./data/song/thumbnail/" + request.getFilePath());
        if (file.exists()) {
            request.setFilePath(file.getAbsolutePath());

            return songImageRepository
                    .save(songImageMapper
                            .toEntity(request)
                            .setAsNew())
                    .map(songImageMapper::toDTO);
        } else {
            throw new RuntimeException("File not found");
        }

    }
}
