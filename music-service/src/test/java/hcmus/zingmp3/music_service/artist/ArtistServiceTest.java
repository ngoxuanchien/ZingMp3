package hcmus.zingmp3.music_service.artist;

import hcmus.zingmp3.music_service.artist.model.Artist;
import hcmus.zingmp3.music_service.artist.model.ArtistRequest;
import hcmus.zingmp3.music_service.artist.model.ArtistResponse;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ArtistServiceTest {

    @InjectMocks
    ArtistServiceImpl artistService;

    @Mock
    ArtistMapper artistMapper;

    @Mock
    ArtistRepository artistRepository;

    UUID uuid;
    ArtistRequest request;
    Artist entityBeforeSave;
    Artist entityAfterSave;
    ArtistResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        uuid = UUID.fromString("8455fd71-4dbe-4f62-acd7-d4c44755b012");

        request = ArtistRequest.builder()
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday(LocalDate.parse("1994-07-05"))
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();

        entityBeforeSave = Artist.builder()
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday(LocalDate.parse("1994-07-05"))
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();

        entityAfterSave = Artist.builder()
                .id(uuid)
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday(LocalDate.parse("1994-07-05"))
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();

        response = ArtistResponse.builder()
                .id(uuid)
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday("1994-07-05")
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();
    }

    @AfterAll
    static void afterAll() {
        System.out.println("All tests are done.");
    }

    @Test
    void createArtist() {
        // Given

        // Mock the call
        when(artistMapper.toEntity(request)).thenReturn(entityBeforeSave);
        when(artistRepository.save(entityBeforeSave)).thenReturn(entityAfterSave);
        when(artistMapper.toDTO(entityAfterSave)).thenReturn(response);

        // When
        ArtistResponse artistResponse = artistService.createArtist(request);

        // Then
        assertNotNull(artistResponse.getId());
        assertEquals(artistResponse, response);

        verify(artistMapper, times(1)).toEntity(request);
        verify(artistRepository, times(1)).save(entityBeforeSave);
        verify(artistMapper, times(1)).toDTO(entityAfterSave);

        System.out.println("Test createArtist is done.");
    }

    @Test
    void getArtistById() {
        // Given

        // Mock the call
        when(artistRepository.findById(uuid)).thenReturn(Optional.ofNullable(entityAfterSave));
        when(artistMapper.toDTO(entityAfterSave)).thenReturn(response);

        // When
        ArtistResponse artistResponse = artistService.getArtistById(uuid.toString());

        // Then
        assertNotNull(artistResponse.getId());
        assertEquals(artistResponse, response);

        verify(artistRepository, times(1)).findById(uuid);
        verify(artistMapper, times(1)).toDTO(entityAfterSave);

        System.out.println("Test getArtistById is done.");
    }

    @Test
    void getAllArtists() {
    }

    @Test
    void updateArtist() {
        // Given

        // Mock the call
        when(artistRepository.findById(uuid)).thenReturn(Optional.ofNullable(entityAfterSave));
        when(artistMapper.toEntity(request)).thenReturn(entityBeforeSave);
        when(artistRepository.save(entityBeforeSave)).thenReturn(entityAfterSave);
        when(artistMapper.toDTO(entityAfterSave)).thenReturn(response);

        // When
        ArtistResponse artistResponse = artistService.updateArtist(uuid.toString(), request);

        // Then
        assertNotNull(artistResponse.getId());
        assertEquals(artistResponse, response);

        verify(artistRepository, times(1)).findById(uuid);
        verify(artistMapper, times(1)).toEntity(request);
        verify(artistRepository, times(1)).save(entityBeforeSave);
        verify(artistMapper, times(1)).toDTO(entityAfterSave);

        System.out.println("Test updateArtist is done.");
    }

    @Test
    void deleteArtist() {
    }

    @Test
    void getOrCreateByAlias() {
    }
}
