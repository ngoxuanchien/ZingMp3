package hcmus.zingmp3.content_delivery.service;

import hcmus.zingmp3.content_delivery.model.dto.AudioFileDataDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AudioFileDataService {
    AudioFileDataDTO createAudioFileData(AudioFileDataDTO audioFileDataDTO);
    AudioFileDataDTO getAudioFileData(UUID id);
    void deleteAudioFileData(UUID id);
    AudioFileDataDTO updateAudioFileData(UUID id, AudioFileDataDTO audioFileDataDTO);
    List<AudioFileDataDTO> getAllAudioFile(Pageable pageable);
    AudioFileDataDTO getOrCreateIfNotExist(AudioFileDataDTO audioFileDataDTO);
}
