package hcmus.zingmp3.genre;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GenreMapper {
    public GenreRestResponse toDTO(Genre genre) {
        if (genre == null) {
            return null;
        }

        return GenreRestResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .title(genre.getTitle())
                .alias(genre.getAlias())
                .songs(genre.getSongs())
                .build();
    }
}
