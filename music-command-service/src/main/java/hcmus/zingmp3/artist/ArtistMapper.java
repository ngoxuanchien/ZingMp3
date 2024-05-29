package hcmus.zingmp3.artist;

import hcmus.zingmp3.artist.model.Artist;
import hcmus.zingmp3.artist.model.ArtistAvro;
import hcmus.zingmp3.artist.model.ArtistRestRequest;
import hcmus.zingmp3.artist.model.ArtistRestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
class ArtistMapper {

    public Artist toEntity(ArtistRestRequest dto) {
        var entity = new Artist();
        BeanUtils.copyProperties(dto, entity);

        return entity;
    }

    public ArtistRestResponse toDto(Artist entity) {
        var dto = new ArtistRestResponse();
        BeanUtils.copyProperties(entity, dto);

        return dto;
    }

    public ArtistAvro toAvro(Artist entity) {
        var dto = new ArtistAvro();
        dto.setId(entity.getId().toString());
        dto.setName(entity.getName());
        dto.setAlias(entity.getAlias());
        dto.setPlaylistId(entity.getPlaylistId().toString());
        dto.setThumbnail(entity.getThumbnail().toString());
        dto.setTotalFollow(entity.getTotalFollow());
        dto.setSortBiography(entity.getSortBiography());
        dto.setBiography(entity.getBiography());
        dto.setNational(entity.getNational());
        dto.setRealName(entity.getRealName());
        dto.setBirthday(entity.getBirthday().toString());
        dto.setAwards(
                entity.getAwards()
                        .stream()
                        .map(award -> award.getId()
                                .toString())
                        .collect(Collectors.toList()));
        // set Song
        return dto;
    }
}

