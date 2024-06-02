package hcmus.zingmp3.artist;

import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ArtistMapper {

    private final ArtistGrpcClient artistClient;
    private final ArtistGrpcMapper mapper;

    public ArtistRestResponse toDTO(UUID id) {
        return mapper.toDto(artistClient.findArtistById(id));
    }

    public Set<ArtistRestResponse> toDTOs(Set<UUID> artists) {
        return artists.stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }
}
