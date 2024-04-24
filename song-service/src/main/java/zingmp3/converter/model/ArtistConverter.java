package zingmp3.converter.model;

import org.springframework.stereotype.Component;
import zingmp3.dto.ArtistDto;
import zingmp3.model.Artist;

@Component
public class ArtistConverter implements Converter<Artist, ArtistDto> {
    @Override
    public ArtistDto convert(Artist source) {
        return ArtistDto.builder()
                .id(source.getId())
                .name(source.getName())
                .link(source.getLink())
                .spotlight(source.isSpotlight())
                .alias(source.getAlias())
                .thumbnail(source.getThumbnail())
                .thumbnailM(source.getThumbnailM())
                .isOA(source.isOA())
                .isOABrand(source.isOABrand())
                .totalFollow(source.getTotalFollow())
                .build();

    }

    @Override
    public Artist reverseConvert(ArtistDto source) {
        return Artist.builder()
                .id(source.getId())
                .name(source.getName())
                .link(source.getLink())
                .spotlight(source.isSpotlight())
                .alias(source.getAlias())
                .thumbnail(source.getThumbnail())
                .thumbnailM(source.getThumbnailM())
                .isOA(source.isOA())
                .isOABrand(source.isOABrand())
                .totalFollow(source.getTotalFollow())
                .build();
    }
}
