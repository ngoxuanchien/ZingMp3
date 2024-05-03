package hcmus.zingmp3.playbackservice.service.impl;

import hcmus.zingmp3.playbackservice.dto.StreamingFileDTO;
import hcmus.zingmp3.playbackservice.mapper.StreamingFileMapper;
import hcmus.zingmp3.playbackservice.repository.StreamingFileRepository;
import hcmus.zingmp3.playbackservice.service.StreamingFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.File;

@Service
@RequiredArgsConstructor
public class StreamingFileServiceImpl implements StreamingFileService {


    private final StreamingFileRepository streamingFileRepository;
    private final StreamingFileMapper streamingFileMapper;

    @Override
    public Mono<StreamingFileDTO> getStreamingFileById(String id) {
        return streamingFileRepository
                .findById(id)
                .map(streamingFileMapper::toDTO);
    }

    @Override
    public Mono<StreamingFileDTO> saveStreamingFile(StreamingFileDTO streamingFileDTO) {
        File file128kps = new File("./data/song/128/" + streamingFileDTO.getPath128kps());
        File file320kps = new File("./data/song/320/" + streamingFileDTO.getPath320kps());
        if (file128kps.exists()) {
            streamingFileDTO.setPath128kps("./data/song/128/" + streamingFileDTO.getPath128kps());
        } else {
            streamingFileDTO.setPath128kps(null);
        }

        if (file320kps.exists()) {
            streamingFileDTO.setPath320kps(file320kps.getAbsolutePath());
        } else {
            streamingFileDTO.setPath320kps(null);
        }
        return streamingFileRepository
                .save(streamingFileMapper
                        .toEntity(streamingFileDTO)
                        .setAsNew())
                .map(streamingFileMapper::toDTO);
    }
}
