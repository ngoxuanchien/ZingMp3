package zingmp3.converter.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zingmp3.dto.SongDto;
import zingmp3.model.Song;

import java.util.stream.Collectors;




@Component
@RequiredArgsConstructor
public class SongConverter implements Converter<Song, SongDto> {
    private final ArtistConverter artistConverter;
    private final PreviewInfoConverter previewInfoConverter;
    private final GenreConverter genreConverter;
    private final IndicatorConverter indicatorConverter;
    private final ComposerConverter composerConverter;
    private final AlbumConverter albumConverter;
    private final StreamingConverter streamingConverter;

    @Override
    public SongDto convert(Song song) {
        return SongDto.builder()
                .id(song.getId())
                .title(song.getTitle())
                .alias(song.getAlias())
                .isOfficial(song.isOfficial())
                .username(song.getUsername())
                .artistsNames(song.getArtistsNames())
                .artists(song.getArtists()
                        .stream()
                        .map(artistConverter::convert)
                        .collect(Collectors.toList())
                )
                .isWorldWide(song.isWorldWide())
                .previewInfo(previewInfoConverter.convert(song.getPreviewInfo()))
                .thumbnailM(song.getThumbnailM())
                .link(song.getLink())
                .thumbnail(song.getThumbnail())
                .duration(song.getDuration())
                .zingChoice(song.isZingChoice())
                .isPrivate(song.isPrivate())
                .preRelease(song.isPreRelease())
                .releaseDate(song.getReleaseDate())
                .genres(song.getGenres()
                        .stream()
                        .map(genreConverter::convert)
                        .collect(Collectors.toList())
                )
                .distributor(song.getDistributor())
                .indicators(song.getIndicators()
                        .stream()
                        .map(indicatorConverter::convert)
                        .collect(Collectors.toList())
                )
                .isIndie(song.isIndie())
                .streamingStatus(song.getStreamingStatus())
                .downloadPrivileges(song.getDownloadPrivileges())
                .allowAudioAds(song.isAllowAudioAds())
                .hasLyric(song.isHasLyric())
                .userId(song.getUserId())
                .composers(song.getComposers()
                        .stream()
                        .map(composerConverter::convert)
                        .collect(Collectors.toList())
                )
                .album(albumConverter.convert(song.getAlbum()))
                .isRBT(song.isRBT())
                .like(song.getLike())
                .listen(song.getListen())
                .liked(song.isLiked())
                .comment(song.getComment())
                .streaming(streamingConverter.convert(song.getStreaming()))
                .build();
    }

    @Override
    public Song reverseConvert(SongDto song) {
        return Song.builder()
                .id(song.getId())
                .title(song.getTitle())
                .alias(song.getAlias())
                .isOfficial(song.isOfficial())
                .username(song.getUsername())
                .artistsNames(song.getArtistsNames())
                .artists(song.getArtists()
                        .stream()
                        .map(artistConverter::reverseConvert)
                        .collect(Collectors.toList())
                )
                .isWorldWide(song.isWorldWide())
                .previewInfo(previewInfoConverter.reverseConvert(song.getPreviewInfo()))
                .thumbnailM(song.getThumbnailM())
                .link(song.getLink())
                .thumbnail(song.getThumbnail())
                .duration(song.getDuration())
                .zingChoice(song.isZingChoice())
                .isPrivate(song.isPrivate())
                .preRelease(song.isPreRelease())
                .releaseDate(song.getReleaseDate())
                .genres(song.getGenres()
                        .stream()
                        .map(genreConverter::reverseConvert)
                        .collect(Collectors.toList())
                )
                .distributor(song.getDistributor())
                .indicators(song.getIndicators()
                        .stream()
                        .map(indicatorConverter::reverseConvert)
                        .collect(Collectors.toList())
                )
                .isIndie(song.isIndie())
                .streamingStatus(song.getStreamingStatus())
                .downloadPrivileges(song.getDownloadPrivileges())
                .allowAudioAds(song.isAllowAudioAds())
                .hasLyric(song.isHasLyric())
                .userId(song.getUserId())
                .composers(song.getComposers()
                        .stream()
                        .map(composerConverter::reverseConvert)
                        .collect(Collectors.toList())
                )
                .album(albumConverter.reverseConvert(song.getAlbum()))
                .isRBT(song.isRBT())
                .like(song.getLike())
                .listen(song.getListen())
                .liked(song.isLiked())
                .comment(song.getComment())
                .streaming(streamingConverter.reverseConvert(song.getStreaming()))
                .build();
    }
}








