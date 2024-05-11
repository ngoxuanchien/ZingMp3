package hcmus.zingmp3.content_delivery.service.impl;

import hcmus.zingmp3.content_delivery.exception.NotFoundException;
import hcmus.zingmp3.content_delivery.service.component.FileDataService;
import hcmus.zingmp3.content_delivery.model.dto.AudioFileDataDTO;
import hcmus.zingmp3.content_delivery.model.entity.AudioFileData;
import hcmus.zingmp3.content_delivery.model.mapper.AudioFileDataMapper;
import hcmus.zingmp3.content_delivery.repository.AudioFileDataRepository;
import hcmus.zingmp3.content_delivery.service.AudioFileDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AudioFileDataServiceImpl implements AudioFileDataService {
    private final AudioFileDataMapper audioFileDataMapper;
    private final FileDataService fileDataService;
    private final AudioFileDataRepository audioFileDataRepository;

    @Override
    public AudioFileDataDTO createAudioFileData(AudioFileDataDTO audioFileDataDTO) {
        AudioFileData audioFileData = audioFileDataMapper
                .toEntity(audioFileDataDTO);
        fileDataService
                .saveAudio(audioFileData);
        return audioFileDataMapper
                .toDTO(audioFileDataRepository
                        .save(audioFileData));
    }

    @Override
    public AudioFileDataDTO getAudioFileData(UUID id) {
        AudioFileData audioFileData = audioFileDataRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Audio file not found: " + id));
        return audioFileDataMapper.toDTO(audioFileData);
    }

    @Override
    public void deleteAudioFileData(UUID id) {
        AudioFileData audioFileData = audioFileDataRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Audio file not found: " + id));
        fileDataService.deleteAudio(audioFileData);
        audioFileDataRepository.deleteById(id);
    }

    @Override
    public AudioFileDataDTO updateAudioFileData(UUID id, AudioFileDataDTO audioFileDataDTO) {
        AudioFileData oldAudioFileData = audioFileDataRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Audio file not found: " + id));
        AudioFileData newAudioFileData = audioFileDataMapper.toEntity(audioFileDataDTO);
        fileDataService.replaceAudio(oldAudioFileData, newAudioFileData);
        return audioFileDataMapper.toDTO(audioFileDataRepository.save(newAudioFileData));
    }

    @Override
    public List<AudioFileDataDTO> getAllAudioFile(Pageable pageable) {
        return audioFileDataMapper
                .toDTO(audioFileDataRepository.findAll(pageable).getContent());
    }

    @Override
    public AudioFileDataDTO getOrCreateIfNotExist(AudioFileDataDTO audioFileDataDTO) {
        UUID id = audioFileDataDTO.getId();
        Optional<AudioFileData> audioFileData;
        if (id != null) {
            audioFileData = audioFileDataRepository.findById(audioFileDataDTO.getId());
            if (audioFileData.isPresent()) {
                return audioFileDataMapper.toDTO(audioFileData.get());
            }
        }

        if (audioFileDataDTO.getObject() != null && audioFileDataDTO.getName() != null && audioFileDataDTO.getSize() != 0) {
            audioFileData = audioFileDataRepository
                    .findByObjectAndNameAndSize(audioFileDataDTO.getObject(), audioFileDataDTO.getName(), audioFileDataDTO.getSize());
            if (audioFileData.isPresent()) {
                return audioFileDataMapper.toDTO(audioFileData.get());
            }
        }

        return createAudioFileData(audioFileDataDTO);
    }
}
