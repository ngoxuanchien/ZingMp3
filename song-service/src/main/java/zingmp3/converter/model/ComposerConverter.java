package zingmp3.converter.model;

import org.springframework.stereotype.Component;
import zingmp3.dto.ComposerDto;
import zingmp3.model.Composer;

@Component
public class ComposerConverter implements Converter<Composer, ComposerDto> {
    @Override
    public ComposerDto convert(Composer composer) {
        return ComposerDto.builder()
                .id(composer.getId())
                .name(composer.getName())
                .link(composer.getLink())
                .spotlight(composer.isSpotlight())
                .alias(composer.getAlias())
                .cover(composer.getCover())
                .thumbnail(composer.getThumbnail())
                .totalFollow(composer.getTotalFollow())
                .build();
    }

    @Override
    public Composer reverseConvert(ComposerDto composer) {
        return Composer.builder()
                .id(composer.getId())
                .name(composer.getName())
                .link(composer.getLink())
                .spotlight(composer.isSpotlight())
                .alias(composer.getAlias())
                .cover(composer.getCover())
                .thumbnail(composer.getThumbnail())
                .totalFollow(composer.getTotalFollow())
                .build();
    }
}
