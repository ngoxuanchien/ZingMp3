package hcmus.zingmp3.music_service.genre;

import hcmus.zingmp3.music_service.genre.model.Genre;
import hcmus.zingmp3.music_service.genre.model.GenreRequest;
import hcmus.zingmp3.music_service.genre.model.GenreResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static hcmus.zingmp3.music_service.genre.GenreDataForTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @InjectMocks
    private GenreServiceImpl genreService;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private GenreMapper genreMapper;

    @Test
    void GenreService_CreateGenre_ReturnGenreResponse() {
        GenreRequest genreRequest = GENRE_REQUESTS.getFirst();
        Genre genreBeforeSave = GENRE_BEFORE_SAVE.getFirst();
        Genre genreAfterSave = GENRE_AFTER_SAVE.getFirst();
        GenreResponse genreResponse = GENRE_RESPONSES.getFirst();

        when(genreMapper.toEntity(genreRequest)).thenReturn(genreBeforeSave);
        when(genreRepository.save(genreBeforeSave)).thenReturn(genreAfterSave);
        when(genreMapper.toDTO(genreAfterSave)).thenReturn(genreResponse);

        GenreResponse response = genreService.createGenre(genreRequest);

        assertNotNull(response);
        assertEquals(response, genreResponse);

        verify(genreMapper, times(1)).toEntity(genreRequest);
        verify(genreRepository, times(1)).save(genreBeforeSave);
        verify(genreMapper, times(1)).toDTO(genreAfterSave);

    }

    @Test
    void GenreService_GetGenreById_ReturnGenreResponse() {
        Genre genre = GENRE_AFTER_SAVE.getFirst();
        GenreResponse genreResponse = GENRE_RESPONSES.getFirst();

        when(genreRepository.findById(genre.getId())).thenReturn(Optional.of(genre));
        when(genreMapper.toDTO(genre)).thenReturn(genreResponse);

        GenreResponse response = genreService.getGenreById(genre.getId());

        assertNotNull(response);
        assertEquals(response, genreResponse);

        verify(genreRepository, times(1)).findById(genre.getId());
        verify(genreMapper, times(1)).toDTO(genre);
    }

    @Test
    void GenreService_GetAllGenres_ReturnGenreResponseList() {
        Pageable pageable = mock(Pageable.class);
        List<Genre> genres = GENRE_AFTER_SAVE;
        Page<Genre> genrePage = new PageImpl<>(genres, pageable, genres.size());
        List<GenreResponse> genreResponses = GENRE_RESPONSES;


        when(genreRepository.findAll(pageable)).thenReturn(genrePage);
        when(genreMapper.toDTO(genres)).thenReturn(genreResponses);

        var response = genreService.getAllGenres(pageable);

        assertNotNull(response);
        assertEquals(response, genreResponses);

        verify(genreRepository, times(1)).findAll(pageable);
        verify(genreMapper, times(1)).toDTO(genres);
    }

    @Test
    void GenreService_UpdateGenre_ReturnGenreResponse() {
        UUID id = GENRE_IDS.getFirst();
        GenreRequest genreRequest = GENRE_REQUESTS.getFirst();
        Genre genreBeforeSave = GENRE_BEFORE_SAVE.getFirst();
        Genre genreAfterSave = GENRE_AFTER_SAVE.getFirst();
        GenreResponse genreResponse = GENRE_RESPONSES.getFirst();

        when(genreRepository.findById(id)).thenReturn(Optional.of(genreAfterSave));
        when(genreMapper.toEntity(genreRequest)).thenReturn(genreBeforeSave);
        when(genreRepository.save(genreBeforeSave)).thenReturn(genreAfterSave);
        when(genreMapper.toDTO(genreAfterSave)).thenReturn(genreResponse);

        var response = genreService.updateGenre(genreAfterSave.getId(), genreRequest);

        assertNotNull(response);
        assertEquals(response, genreResponse);

        verify(genreRepository, times(1)).findById(genreAfterSave.getId());
        verify(genreMapper, times(1)).toEntity(genreRequest);
        verify(genreRepository, times(1)).save(genreBeforeSave);
        verify(genreMapper, times(1)).toDTO(genreAfterSave);
    }

    @Test
    void GenreService_DeleteGenre_ReturnVoid() {
        UUID id = GENRE_IDS.getFirst();
        Genre genre = GENRE_AFTER_SAVE.getFirst();

        when(genreRepository.findById(id)).thenReturn(Optional.of(genre));

        assertAll(() -> genreService.deleteGenre(id));

        verify(genreRepository, times(1)).findById(id);
        verify(genreRepository, times(1)).deleteById(id);
    }

}