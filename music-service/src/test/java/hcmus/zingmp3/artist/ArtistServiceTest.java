package hcmus.zingmp3.artist;

import hcmus.zingmp3.artist.model.Artist;
import hcmus.zingmp3.artist.model.ArtistRequest;
import hcmus.zingmp3.artist.model.ArtistResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static hcmus.zingmp3.artist.ArtistDataForTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
        ArtistRequest artistRequest = ARTIST_REQUESTS.getFirst();
        Artist artistBeforeSave = BEFORE_SAVE_ARTISTS.getFirst();
        Artist artistAfterSave = AFTER_SAVE_ARTISTS.getFirst();
        ArtistResponse artistResponse = ARTIST_RESPONSES.getFirst();

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

    @Test
    public void ArtistService_GetArtistById_ReturnArtistDto() {
        // Given
        UUID id = ARTIST_IDS.getFirst();
        Artist artist = AFTER_SAVE_ARTISTS.getFirst();
        ArtistResponse artistResponse = ARTIST_RESPONSES.getFirst();

        // When
        when(artistRepository.findById(id)).thenReturn(Optional.of(artist));
        when(artistMapper.toDTO(artist)).thenReturn(artistResponse);

        // Then
        ArtistResponse foundArtistResponse = artistService.getArtistById(artist.getId().toString());

        // Assert
        assertNotNull(foundArtistResponse);
        assertEquals(foundArtistResponse, artistResponse);

        // Verify
        verify(artistRepository, times(1)).findById(id);
        verify(artistMapper, times(1)).toDTO(artist);
    }

    @Test
    public void ArtistService_GetAllArtists_ReturnListOfArtistResponse() {
        Pageable pageable = Mockito.mock(Pageable.class);
        List<Artist> artists = AFTER_SAVE_ARTISTS;
        Page<Artist> artistPage = new PageImpl<>(artists, PageRequest.of(0, artists.size()), artists.size());
        List<ArtistResponse> artistResponses = ARTIST_RESPONSES;

        when(artistRepository.findAll(pageable)).thenReturn(artistPage);
        when(artistMapper.toDTO(artistPage.getContent())).thenReturn(artistResponses);

        List<ArtistResponse> getArtistResponses = artistService.getAllArtists(pageable);

        assertNotNull(getArtistResponses);
        assertThat(getArtistResponses).hasSize(artists.size());
        assertEquals(getArtistResponses, artistResponses);

        verify(artistRepository, times(1)).findAll(pageable);
        verify(artistMapper, times(1)).toDTO(artistPage.getContent());
    }

    @Test
    public void ArtistService_GetOrCreateByAlias_ReturnArtist() {
        String alias = "alias-1";
        Artist artist = Artist.builder().alias(alias).build();

        when(artistRepository.findByAlias(alias)).thenReturn(Optional.empty());
        when(artistRepository.save(artist)).thenReturn(artist);

        Artist getArtist = artistService.getOrCreateByAlias(alias);

        assertNotNull(getArtist);
        assertEquals(getArtist, artist);

        verify(artistRepository, times(1)).findByAlias(alias);
        verify(artistRepository, times(1)).save(artist);
    }

    @Test
    public void ArtistService_UpdateArtist_ReturnArtistResponse() {
        UUID id = ARTIST_IDS.getFirst();
        Artist artistBeforeSave = BEFORE_SAVE_ARTISTS.getFirst();
        Artist artistAfterSave = AFTER_SAVE_ARTISTS.getFirst();
        ArtistRequest artistRequest = ARTIST_REQUESTS.getFirst();
        ArtistResponse artistResponse = ARTIST_RESPONSES.getFirst();

        when(artistRepository.findById(id)).thenReturn(Optional.of(artistBeforeSave));
        when(artistMapper.toEntity(artistRequest)).thenReturn(artistAfterSave);
        when(artistRepository.save(artistAfterSave)).thenReturn(artistAfterSave);
        when(artistMapper.toDTO(artistAfterSave)).thenReturn(artistResponse);

        ArtistResponse updatedArtistResponse = artistService.updateArtist(id.toString(), artistRequest);

        assertNotNull(updatedArtistResponse);
        assertEquals(updatedArtistResponse, artistResponse);

        verify(artistRepository, times(1)).findById(id);
        verify(artistMapper, times(1)).toEntity(artistRequest);
        verify(artistRepository, times(1)).save(artistAfterSave);
        verify(artistMapper, times(1)).toDTO(artistAfterSave);
    }

    @Test
    public void ArtistService_DeleteArtistById_ReturnVoid() {
        UUID id = ARTIST_IDS.getFirst();
        Artist artist = BEFORE_SAVE_ARTISTS.getFirst();

        when(artistRepository.findById(id)).thenReturn(Optional.of(artist));

        assertAll(() -> artistService.deleteArtist(id.toString()));

        verify(artistRepository, times(1)).findById(id);
        verify(artistRepository, times(1)).deleteById(id);
    }




}
