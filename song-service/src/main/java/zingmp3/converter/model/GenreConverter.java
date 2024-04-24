package zingmp3.converter.model;

import org.springframework.stereotype.Component;
import zingmp3.dto.GenreDto;
import zingmp3.model.Genre;

@Component
public class GenreConverter implements Converter<Genre, GenreDto> {
    @Override
    public GenreDto convert(Genre genre) {
        return GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .title(genre.getTitle())
                .alias(genre.getAlias())
                .link(genre.getLink())
                .build();
    }

    @Override
    public Genre reverseConvert(GenreDto genre) {
        return Genre.builder()
                .id(genre.getId())
                .name(genre.getName())
                .title(genre.getTitle())
                .alias(genre.getAlias())
                .link(genre.getLink())
                .build();
    }
}
