package zingmp3.converter.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zingmp3.dto.AlbumDto;
import zingmp3.model.Album;
import zingmp3.model.Genre;
import zingmp3.repository.GenreRepository;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AlbumConverter implements Converter<Album, AlbumDto> {
    private final ArtistConverter artistConverter;
    private final GenreRepository genreRepository;

    @Override
    public AlbumDto convert(Album source) {
        if (source == null) {
            return null;
        }

        return AlbumDto.builder()
                .id(source.getId())
                .title(source.getTitle())
                .thumbnail(source.getThumbnail())
                .isOfficial(source.isOfficial())
                .link(source.getLink())
                .isIndie(source.isIndie())
                .releaseDate(source.getReleaseDate())
                .sortDescription(source.getSortDescription())
                .releasedAt(source.getReleasedAt())
                .genreIds(source.getGenres()
                        .stream()
                        .map(Genre::getId)
                        .collect(Collectors.toList())
                )
                .artists(source.getArtists()
                        .stream()
                        .map(artistConverter::convert)
                        .collect(Collectors.toList())
                )
                .artistNames(source.getArtistsNames())
                .build();
    }

    @Override
    public Album reverseConvert(AlbumDto source) {
        if (source == null) {
            return null;
        }

        return Album.builder()
                .id(source.getId())
                .title(source.getTitle())
                .thumbnail(source.getThumbnail())
                .isOfficial(source.isOfficial())
                .link(source.getLink())
                .isIndie(source.isIndie())
                .releaseDate(source.getReleaseDate())
                .sortDescription(source.getSortDescription())
                .releasedAt(source.getReleasedAt())
                .genres(source.getGenreIds()
                        .stream()
                        .map(genreId -> genreRepository.findById(genreId).orElse(null))
                        .collect(Collectors.toList())
                )
                .artists(source.getArtists()
                        .stream()
                        .map(artistConverter::reverseConvert)
                        .collect(Collectors.toList())
                )
                .artistsNames(source.getArtistNames())
                .build();
    }
}
