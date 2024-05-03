package hcmus.zingmp3.playbackservice.service.impl;

import hcmus.zingmp3.playbackservice.dto.PlaylistImageDTO;
import hcmus.zingmp3.playbackservice.entity.PlaylistImageEntity;
import hcmus.zingmp3.playbackservice.mapper.PlaylistImageMapper;
import hcmus.zingmp3.playbackservice.repository.PlaylistImageRepository;
import hcmus.zingmp3.playbackservice.service.PlaylistImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistImageServiceImpl implements PlaylistImageService {
    private final PlaylistImageRepository playlistImageRepository;
    private final PlaylistImageMapper playlistImageMapper;

    @Override
    public byte[] getImageById(String id) throws IOException {
        Optional<PlaylistImageEntity> playlistImage = playlistImageRepository
                .findById(id)
                .blockOptional();

        if (playlistImage.isPresent()) {
            String filePath = playlistImage.get().getFilePath();
            return Files.readAllBytes(new File(filePath).toPath());
        } else {
            return Files.readAllBytes(new File("D:\\HCMUS\\Term8\\DoAnCongNghePhanMem\\ZingMp3\\Backend\\ZingMp3\\data\\song\\thumbnail\\Ai-La-Em-Beat-Duong-Edward-SlimV.jpg").toPath());
        }
    }

    @Override
    public Mono<PlaylistImageDTO> uploadImage(PlaylistImageDTO playlistImageDTO) {
        File file = new File("./data/playlist/thumbnail/" + playlistImageDTO.getFilePath());
        if (file.exists()) {
            playlistImageDTO.setFilePath("./data/playlist/thumbnail/" + playlistImageDTO.getFilePath());

            return playlistImageRepository
                        .save(playlistImageMapper
                                .toEntity(playlistImageDTO)
                                .setAsNew())
                        .map(playlistImageMapper::toDTO);
        } else {
            System.out.println(file.getAbsolutePath());
//            throw new RuntimeException("File not found");
            return Mono.empty();
        }
    }
}
