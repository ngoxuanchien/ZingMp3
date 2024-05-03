package hcmus.zingmp3.playbackservice.service.impl;

import hcmus.zingmp3.playbackservice.dto.ArtistImageDTO;
import hcmus.zingmp3.playbackservice.entity.ArtistImageEntity;
import hcmus.zingmp3.playbackservice.entity.SongImageEntity;
import hcmus.zingmp3.playbackservice.mapper.ArtistImageMapper;
import hcmus.zingmp3.playbackservice.repository.ArtistImageRepository;
import hcmus.zingmp3.playbackservice.service.ArtistImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistImageServiceImpl implements ArtistImageService {

    private final ArtistImageRepository artistImageRepository;
    private final ArtistImageMapper artistImageMapper;

    @Override
    public byte[] getImageById(String id) throws IOException {
        Optional<ArtistImageEntity> artistImage = artistImageRepository
                .findById(id)
                .blockOptional();
        if (artistImage.isPresent()) {
            String filePath = artistImage.get().getFilePath();
            return Files.readAllBytes(new File(filePath).toPath());
        } else {
            return Files.readAllBytes(new File("D:\\HCMUS\\Term8\\DoAnCongNghePhanMem\\ZingMp3\\Backend\\ZingMp3\\data\\song\\thumbnail\\Ai-La-Em-Beat-Duong-Edward-SlimV.jpg").toPath());
        }
    }

    @Override
    public Mono<ArtistImageDTO> uploadImage(ArtistImageDTO artistImageDTO) {
        File file = new File("./data/artist/thumbnail/" + artistImageDTO.getFilePath());
        if (file.exists()) {
            artistImageDTO.setFilePath("./data/artist/thumbnail/" + artistImageDTO.getFilePath());
            return artistImageRepository
                    .save(artistImageMapper
                            .toEntity(artistImageDTO)
                            .setAsNew())
                    .map(artistImageMapper::toDTO);
        } else {
            System.out.println(file.getAbsolutePath());
            return Mono.empty();
        }

    }
}
