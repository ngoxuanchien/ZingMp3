package hcmus.zingmp3.artist;

import hcmus.zingmp3.artist.model.Artist;
import hcmus.zingmp3.artist.model.ArtistResponse;
import hcmus.zingmp3.award.AwardService;
import hcmus.zingmp3.thumbnail.ThumbnailClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import static hcmus.zingmp3.artist.ArtistDataForTest.AFTER_SAVE_ARTISTS;
import static hcmus.zingmp3.artist.ArtistDataForTest.ARTIST_RESPONSES;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistMapperTest {

    @InjectMocks
    ArtistMapper artistMapper;

    @Mock
    AwardService awardService;

    @Mock
    ThumbnailClientService thumbnailClientService;

    @Test
    void ArtistMapper_ToDTO_ReturnArtistResponse() {
        Artist artist = AFTER_SAVE_ARTISTS.getFirst();
        ArtistResponse artistResponse = ARTIST_RESPONSES.getFirst();

//        when(thumbnailClientService.getById(artist.getThumbnail())).thenReturn(null);
//
//        // When
//        ArtistResponse response = artistMapper.toDTO(artist);
//
//        // Then
//        assertNotNull(response);
//        assertEquals(artistResponse, response);
    }

}