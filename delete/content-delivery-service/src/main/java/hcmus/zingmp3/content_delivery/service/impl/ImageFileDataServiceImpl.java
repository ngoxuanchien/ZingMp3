package hcmus.zingmp3.content_delivery.service.impl;

import hcmus.zingmp3.content_delivery.service.component.FileDataService;
import hcmus.zingmp3.content_delivery.exception.NotFoundException;
import hcmus.zingmp3.content_delivery.model.dto.ImageFileDataDTO;
import hcmus.zingmp3.content_delivery.thumbnail.ImageFileData;
import hcmus.zingmp3.content_delivery.model.mapper.ImageFileDataMapper;
import hcmus.zingmp3.content_delivery.repository.ImageFileDataRepository;
import hcmus.zingmp3.content_delivery.service.ImageFileDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageFileDataServiceImpl implements ImageFileDataService {

    private final ImageFileDataRepository imageFileDataRepository;
    private final ImageFileDataMapper imageFileDataMapper;
    private final FileDataService fileDataService;

    @Override
    public ImageFileDataDTO getImageFileData(UUID id) {
        ImageFileData imageFileData = imageFileDataRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Image file not found"));
        return imageFileDataMapper.toDTO(imageFileData);
    }

    @Override
    public List<ImageFileDataDTO> getAllImageFile(Pageable pageable) {
        return imageFileDataMapper
                .toDTO(imageFileDataRepository
                        .findAll(pageable)
                        .getContent());
    }

    @Override
    public ImageFileDataDTO createImageFileData(ImageFileDataDTO imageFileDataDTO) {
        ImageFileData imageFileData = imageFileDataMapper
                .toEntity(imageFileDataDTO);
        fileDataService
                .saveThumbnail(imageFileData);
        return imageFileDataMapper
                .toDTO(imageFileDataRepository
                        .save(imageFileData));
    }

    @Override
    public ImageFileDataDTO updateImageFileData(UUID id, ImageFileDataDTO imageFileDataDTO) {
        return null;
    }

    @Override
    public void deleteImageFileData(UUID id) {

    }

    @Override
    public ImageFileDataDTO getOrCreateIfNotExist(ImageFileDataDTO imageFileDataDTO) {
        UUID id = imageFileDataDTO.getId();
        Optional<ImageFileData> imageFileData;
        if (id != null) {
            imageFileData = imageFileDataRepository.findById(imageFileDataDTO.getId());
            if (imageFileData.isPresent()) {
                return imageFileDataMapper.toDTO(imageFileData.get());
            }
        }

        if (imageFileDataDTO.getObject() != null && imageFileDataDTO.getName() != null && imageFileDataDTO.getSize() != 0) {
            imageFileData = imageFileDataRepository
                    .findByObjectAndNameAndSize(imageFileDataDTO.getObject(), imageFileDataDTO.getName(), imageFileDataDTO.getSize());
            if (imageFileData.isPresent()) {
                return imageFileDataMapper.toDTO(imageFileData.get());
            }
        }

        return createImageFileData(imageFileDataDTO);
    }
}
