package hcmus.zingmp3.artist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtistMapper {

    public Artist toEntity(ArtistRestRequest dto) {
        if (dto == null) {
            return null;
        }

        return Artist.builder()
                .alias(dto.getAlias())
                .name(dto.getName())
                .thumbnail(dto.getThumbnail())
                .sortBiography(dto.getSortBiography())
                .biography(dto.getBiography())
                .national(dto.getNational())
                .realName(dto.getRealName())
                .birthday(dto.getBirthday())
                .awards(dto.getAwards())
                .build();
    }

    public ArtistRestResponse toDto(Artist entity) {
        if (entity == null) {
            return null;
        }

        return ArtistRestResponse.builder()
                .id(entity.getId())
                .alias(entity.getAlias())
                .name(entity.getName())
                .thumbnail(entity.getThumbnail().toString())
                .sortBiography(entity.getSortBiography())
                .biography(entity.getBiography())
                .national(entity.getNational())
                .realName(entity.getRealName())
                .birthday(entity.getBirthday().toString())
                .awards(entity.getAwards())
                .build();
    }


}

