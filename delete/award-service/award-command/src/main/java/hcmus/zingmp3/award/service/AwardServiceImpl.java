package hcmus.zingmp3.award.service;

import hcmus.zingmp3.exeception.EntityAlreadyExistsException;
import hcmus.zingmp3.exeception.EntityNotFoundException;
import hcmus.zingmp3.award.mapper.AwardMapper;
import hcmus.zingmp3.award.model.Award;
import hcmus.zingmp3.award.dto.AwardRestRequest;
import hcmus.zingmp3.award.dto.AwardRestResponse;
import hcmus.zingmp3.award.repository.AwardRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {

    private final AwardRepository repository;
    private final AwardMapper mapper;
    private final AwardKafkaProducer producer;

    @Override
    public AwardRestResponse createAward(AwardRestRequest request) {
        exitByName(request.getName());
        Award newAward = mapper.toEntity(request);

        return mapper.toDTO(createAward(newAward));
    }

    private void mergeAward(Award award, AwardRestRequest request) {
        if (StringUtils.isNoneBlank(request.getName())) {
            award.setName(request.getName());
        }
    }

    @Override
    public AwardRestResponse updateAward(UUID id, AwardRestRequest request) {
        exitByName(request.getName());
        var award = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Cannot update award: No award found with the provided ID: %s", id)
                ));

        mergeAward(award, request);
        var updatedAward = repository.save(award);

        producer.updateAward(updatedAward);
        return mapper.toDTO(updatedAward);
    }

    @Override
    public void deleteAward(UUID id) {
        Award deletedAward = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Cannot delete award: No award found with the provided ID: %s", id)
                ));

        repository.deleteById(id);
        producer.deleteAward(deletedAward);
    }

    @Override
    public Award getOrCreateAward(Award request) {
        return repository.findByName(request.getName())
                .orElseGet(() -> createAward(request));
    }


    private void addArtist(UUID uuid, UUID artistId) {
        validateInput(uuid, artistId);

        var award = findAwardById(uuid);

        if (award.getArtistIds().contains(artistId)) {
            System.out.println("Cannot update award: Artist already exists in the award.");
            return;
        }

        award.getArtistIds().add(artistId);
        repository.save(award);
        producer.updateAward(award);
    }

    private void validateInput(UUID uuid, UUID artistId) {
        if (uuid == null || artistId == null) {
            throw new IllegalArgumentException("UUID and artistId must not be null.");
        }
    }

    private void remove(UUID awardId, UUID artistId) {
        validateInput(awardId, artistId);

        var award = findAwardById(awardId);

        if (award.getArtistIds().contains(artistId)) {
            award.getArtistIds().remove(artistId);
            repository.save(award);
            producer.updateAward(award);
        }
    }

    private Award findAwardById(UUID awardId) {
        return repository.findById(awardId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Cannot update award: No award found with the provided ID: %s", awardId)
                ));
    }

    @Override
    public void updateAward(UUID artistId, Set<UUID> awardIds) {
        if (artistId == null || awardIds == null) {
            return;
        }

        awardIds.forEach(awardId -> addArtist(awardId, artistId));

        repository.findAllByArtistIdsContaining(artistId)
                .forEach(award -> {
                    if (!awardIds.contains(award.getId())) {
                        remove(award.getId(), artistId);
                    }
                });
    }

    @Override
    public void removeArtist(UUID artistId, Set<UUID> awardIds) {
        if (artistId == null || awardIds == null) {
            return;
        }

        awardIds.forEach(awardId -> remove(awardId, artistId));
    }


    private Award createAward(Award award) {
        var newAward = repository.save(award);
        producer.createAward(newAward);
        return newAward;
    }

    private void exitByName(String name) {
        if (repository.existsByName(name)) {
            throw new EntityAlreadyExistsException(
                    String.format("Cannot create award: Award with name %s already exists", name)
            );
        }
    }
}
