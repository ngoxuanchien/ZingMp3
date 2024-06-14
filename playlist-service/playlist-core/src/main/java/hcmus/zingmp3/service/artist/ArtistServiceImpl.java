package hcmus.zingmp3.service.artist;

import hcmus.zingmp3.ArtistRequestGrpc;
import hcmus.zingmp3.ArtistResponseGrpc;
import hcmus.zingmp3.ArtistServiceGrpc;
import hcmus.zingmp3.web.dto.ArtistResponse;
import hcmus.zingmp3.web.dto.mapper.ArtistMapper;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    @GrpcClient("artist-service")
    ArtistServiceGrpc.ArtistServiceBlockingStub artistClient;

    private final ArtistMapper mapper;


    @Override
    public ArtistResponse getById(UUID uuid) {
        try {
            var request = ArtistRequestGrpc.newBuilder().setId(uuid.toString()).build();
            return mapper.toDto(artistClient.getById(request));

        } catch (StatusRuntimeException e) {
//            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
//                throw new ResourceNotFoundException(e.getMessage());
//            }
//            throw e;
            return mapper.toDto(ArtistResponseGrpc.newBuilder().setId(uuid.toString()).build());
        }
    }

    @Override
    public boolean isExist(UUID uuid) {
        try {
            var request = ArtistRequestGrpc.newBuilder().setId(uuid.toString()).build();
            return artistClient.isExist(request).getId().equals(uuid.toString());

        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                return false;
            }
            throw e;
        }
    }

    @Override
    public List<ArtistResponse> getAllById(List<UUID> uuids) {
        return uuids.stream()
                .map(this::getById)
                .toList();
    }
}
