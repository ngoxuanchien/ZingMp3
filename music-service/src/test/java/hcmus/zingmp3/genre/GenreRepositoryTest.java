package hcmus.zingmp3.genre;

import hcmus.zingmp3.genre.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static hcmus.zingmp3.genre.GenreDataForTest.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void GenreRepository_Save_ReturnGenre() {
        Genre genre = GenreDataForTest.GENRE_BEFORE_SAVE.getFirst();

        Genre savedGenre = genreRepository.save(genre);

        assertNotNull(savedGenre);
        assertNotNull(savedGenre.getId());
    }


    @Test
    void GenreRepository_FindById_ReturnGenre() {
        Genre genre = GenreDataForTest.GENRE_AFTER_SAVE.getFirst();

        genre = genreRepository.save(genre);

        Genre foundGenre = genreRepository.findById(genre.getId()).orElse(null);

        assertNotNull(foundGenre);
        assertEquals(foundGenre, genre);
    }

    @Test
    void GenreRepository_FindByAlias_ReturnGenre() {
        Genre genre = GenreDataForTest.GENRE_AFTER_SAVE.getFirst();

        genre = genreRepository.save(genre);

        Genre foundGenre = genreRepository.findByAlias(genre.getAlias()).orElse(null);

        assertNotNull(foundGenre);
        assertEquals(foundGenre, genre);
    }

    @Test
    void GenreRepository_FindAll_ReturnMoreThanOneGenre() {
        List<Genre> givenGenres = GenreDataForTest.GENRE_BEFORE_SAVE;

        genreRepository.saveAll(givenGenres);

        List<Genre> genres = genreRepository.findAll();

        assertNotNull(genres);
        assertEquals(genres.size(), givenGenres.size());
    }

    @Test
    void GenreRepository_Update_ReturnUpdatedGenre() {
        Genre genre = GenreDataForTest.GENRE_BEFORE_SAVE.getFirst();

        genre = genreRepository.save(genre);

        Genre updatedGenre = new Genre();
        BeanUtils.copyProperties(genre, updatedGenre);
        genre.setName("Updated Name");

        Genre savedGenre = genreRepository.save(updatedGenre);

        assertNotNull(savedGenre);
        assertEquals(savedGenre, updatedGenre);
    }

    @Test
    void GenreRepository_Delete_ReturnNull() {
        Genre genre = GenreDataForTest.GENRE_BEFORE_SAVE.getFirst();

        genre = genreRepository.save(genre);

        genreRepository.deleteById(genre.getId());

        Genre foundGenre = genreRepository.findById(genre.getId()).orElse(null);

        assertNull(foundGenre);
    }

}