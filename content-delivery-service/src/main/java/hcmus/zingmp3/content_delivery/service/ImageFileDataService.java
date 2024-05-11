package hcmus.zingmp3.content_delivery.service;

import hcmus.zingmp3.content_delivery.model.dto.ImageFileDataDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ImageFileDataService {
    ImageFileDataDTO getImageFileData(UUID id);
    List<ImageFileDataDTO> getAllImageFile(Pageable pageable);
    ImageFileDataDTO createImageFileData(ImageFileDataDTO imageFileDataDTO);
    ImageFileDataDTO updateImageFileData(UUID id, ImageFileDataDTO imageFileDataDTO);
    void deleteImageFileData(UUID id);
    ImageFileDataDTO getOrCreateIfNotExist(ImageFileDataDTO imageFileDataDTO);
}
