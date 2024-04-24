package zingmp3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zingmp3.converter.model.ArtistConverter;
import zingmp3.dto.ArtistDto;
import zingmp3.exception.ArtistNotFoundException;
import zingmp3.model.Artist;
import zingmp3.repository.ArtistRepository;
import zingmp3.service.ArtistService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistConverter artistConverter;

    @Override
    public List<ArtistDto> findAll() {
        return artistRepository.findAll()
                .stream()
                .map(artistConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ArtistDto findById(UUID id) {
        return artistConverter.convert(
                artistRepository.findById(id)
                        .orElseThrow(() -> new ArtistNotFoundException("Artist not found"))
        );
    }

    @Override
    public ArtistDto create(ArtistDto artistDto) {
        Artist artist = artistConverter.reverseConvert(artistDto);
        artist.setId(null);
        return artistConverter.convert(artistRepository.save(artist));
    }

    @Override
    public ArtistDto update(UUID id, ArtistDto artistDto) {
        if (!artistRepository.existsById(id)) {
            throw new ArtistNotFoundException("Artist not found");
        }
        Artist artist = artistConverter.reverseConvert(artistDto);
        artist.setId(id);
        return artistConverter.convert(artistRepository.save(artist));
    }

    @Override
    public void delete(UUID id) {
        if (!artistRepository.existsById(id)) {
            throw new ArtistNotFoundException("Artist not found");
        }
        artistRepository.deleteById(id);
    }
}
