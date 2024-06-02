package hcmus.zingmp3.song;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class SongAvroMapper {
    public SongAvro toAvro(Song song) {
        return SongAvro.newBuilder()
                .setId(song.getId().toString())
                .setAlias(song.getAlias())
                .setTitle(song.getTitle())
                .setIsOfficial(song.isOfficial())
                .setIsWorldWide(song.isWorldWide())
                .setThumbnail(song.getThumbnail().toString())
                .setDuration(song.getDuration())
                .setIsPrivate(song.isPrivate())
                .setReleaseDate(song.getReleaseDate())
                .setDistributor(song.getDistributor())
                .setHasLyric(song.isHasLyric())
                .setLikes(song.getLikes())
                .setListen(song.getListen())
                .setComment(song.getComment())
                .setArtists(toCharSequences(song.getArtists()))
                .setGenres(toCharSequences(song.getGenres()))
                .setAudios(toCharSequences(song.getAudios()))
                .setComposers(toCharSequences(song.getComposers()))
                .build();
    }

    public Song toEntity(SongAvro request) {
        return null;
    }

    private CharSequence toCharSequence(UUID id) {
        return id.toString();
    }

    private List<CharSequence> toCharSequences(Set<UUID> ids) {
        return ids.stream()
                .map(this::toCharSequence)
                .toList();
    }
}
