package hcmus.zingmp3.music_service.artist;

import hcmus.zingmp3.music_service.artist.model.Artist;
import hcmus.zingmp3.music_service.artist.model.ArtistRequest;
import hcmus.zingmp3.music_service.artist.model.ArtistResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceTest {
    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private ArtistMapper artistMapper;

    @InjectMocks
    private ArtistServiceImpl artistService;

    @Test
    public void ArtistService_CreateArtist_ReturnArtistResponse() {
        ArtistRequest artistRequest = ArtistRequest.builder()
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday(LocalDate.parse("1994-07-05"))
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();

        Artist artistBeforeSave = Artist.builder()
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday(LocalDate.parse("1994-07-05"))
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();

        Artist artistAfterSave = Artist.builder()
                .id(UUID.randomUUID())
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday(LocalDate.parse("1994-07-05"))
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();

        ArtistResponse artistResponse = ArtistResponse.builder()
                .id(artistAfterSave.getId())
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday("1994-07-05")
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();

        when(artistMapper.toEntity(artistRequest)).thenReturn(artistBeforeSave);
        when(artistRepository.save(artistBeforeSave)).thenReturn(artistAfterSave);
        when(artistMapper.toDTO(artistAfterSave)).thenReturn(artistResponse);

        ArtistResponse savedArtistResponse = artistService.createArtist(artistRequest);

        assertNotNull(savedArtistResponse);
        assertEquals(savedArtistResponse, artistResponse);

        verify(artistMapper, times(1)).toEntity(artistRequest);
        verify(artistRepository, times(1)).save(artistBeforeSave);
        verify(artistMapper, times(1)).toDTO(artistAfterSave);
    }
}
