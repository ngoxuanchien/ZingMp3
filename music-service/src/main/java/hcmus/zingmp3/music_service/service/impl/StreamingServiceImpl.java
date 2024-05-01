package hcmus.zingmp3.music_service.service.impl;

import hcmus.zingmp3.music_service.dto.StreamingDTO;
import hcmus.zingmp3.music_service.dto.StreamingFileDTO;
import hcmus.zingmp3.music_service.mapper.StreamingMapper;
import hcmus.zingmp3.music_service.repository.StreamingRepository;
import hcmus.zingmp3.music_service.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StreamingServiceImpl implements StreamingService {
    private final StreamingRepository streamingRepository;
    private final StreamingMapper streamingMapper;
    private final WebClient.Builder webClient;

    @Value("${server.host}")
    private String host;

    private Mono<StreamingFileDTO> saveStreamingFile(StreamingFileDTO streamingFileDTO) {
        return webClient
                .build()
                .post()
                .uri("http://playback-service/api/playback/streaming")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(streamingFileDTO)
                .retrieve()
                .bodyToMono(StreamingFileDTO.class)
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.empty();
                });
    }

    @Override
    public Mono<StreamingDTO> findById(String id) {
        return streamingRepository.findById(id)
                    .map(streamingMapper::toDTO);
    }

    @Override
    public Mono<StreamingDTO> create(StreamingDTO streamingDTO, String songAlias) {
        return saveStreamingFile(StreamingFileDTO
                        .builder()
                        .alias(songAlias)
                        .path128kps(streamingDTO.getUrl128kps())
                        .path320kps(streamingDTO.getUrl320kps())
                        .build())
                .flatMap(streamingFileDTO ->
                        streamingRepository
                                .save(streamingMapper.toEntity(streamingDTO)
                                        .setAsNew()
                                        .setUrl128kps("http://" + host + "/api/playback/streaming/play/" + streamingFileDTO.getId()))
//                                        .setUrl320kps(streamingFileDTO.getId()))
                                .map(streamingMapper::toDTO));
    }
}
