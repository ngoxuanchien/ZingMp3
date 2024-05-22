package hcmus.zingmp3.music_service.artist;

import hcmus.zingmp3.music_service.artist.model.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ArtistRepositoryTest {
    @Autowired
    private ArtistRepository artistRepository;

    @Test
    public void ArtistRepository_Save_ReturnSaveArtist() {
        // Arrange
        Artist artist = Artist.builder()
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday(LocalDate.parse("1994-07-05"))
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();


        // Act
        Artist savedArtist = artistRepository.save(artist);

        // Assert
        assertThat(savedArtist).isNotNull();
        assertThat(savedArtist.getId()).isNotNull();
    }

    @Test
    public void ArtistRepository_FindAll_ReturnMoreThanOneArtist() {
        // Arrange
        Artist artist1 = Artist.builder()
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday(LocalDate.parse("1994-07-05"))
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();

        Artist artist2 = Artist.builder()
                .name("Lý Hải")
                .alias("ly-hai")
                .birthday(LocalDate.parse("1974-07-05"))
                .national("Vietnam")
                .realName("Lý Hải")
                .sortBiography("Singer")
                .biography("Lý Hải is a Vietnamese singer, songwriter and actor.")
                .build();


        artistRepository.save(artist1);
        artistRepository.save(artist2);

        // Act
        Iterable<Artist> artists = artistRepository.findAll();

        // Assert
        assertThat(artists).isNotNull();
        assertThat(artists).hasSize(2);
    }

    @Test
    public void ArtistRepository_FindById_ReturnArtist() {
        Artist artist = Artist.builder()
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday(LocalDate.parse("1994-07-05"))
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();

        artistRepository.save(artist);

        Optional<Artist> optionalArtistResponse = artistRepository.findById(artist.getId());

        assertThat(optionalArtistResponse).isPresent();

        Artist artistResponse = optionalArtistResponse.get();

        assertThat(artistResponse).isNotNull();
        assertThat(artist).isEqualTo(artistResponse);
    }

    @Test
    public void ArtistRepository_FindByAlias_ReturnArtist() {
        Artist artist = Artist.builder()
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday(LocalDate.parse("1994-07-05"))
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();

        artistRepository.save(artist);

        Optional<Artist> optionalArtistResponse = artistRepository.findByAlias(artist.getAlias());

        assertThat(optionalArtistResponse).isPresent();

        Artist artistResponse = optionalArtistResponse.get();

        assertThat(artistResponse).isNotNull();
        assertThat(artistResponse).isEqualTo(artist);
    }

    @Test
    public void ArtistRepository_Update_ReturnArtistNotNull() {
        Artist artist = Artist.builder()
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday(LocalDate.parse("1994-07-05"))
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();

        artistRepository.save(artist);

        Optional<Artist> optionalArtistSave = artistRepository.findByAlias(artist.getAlias());

        assertThat(optionalArtistSave).isPresent();

        Artist artistSave = optionalArtistSave.get();
        artistSave.setAlias("son-tung-m-tp-1");
        artistSave.setName("Sơn Tùng M-TP 1");

        Artist updatedArtist = artistRepository.save(artistSave);

        assertThat(updatedArtist).isNotNull();
        assertThat(updatedArtist).isEqualTo(artistSave);
    }

    @Test
    public void ArtistRepository_DeleteById_ReturnArtistIsEmpty() {
        Artist artist = Artist.builder()
                .name("Sơn Tùng M-TP")
                .alias("son-tung-m-tp")
                .birthday(LocalDate.parse("1994-07-05"))
                .national("Vietnam")
                .realName("Nguyễn Thanh Tùng")
                .sortBiography("Singer")
                .biography("Sơn Tùng M-TP is a Vietnamese singer, songwriter and actor.")
                .build();

        artistRepository.save(artist);

        artistRepository.deleteById(artist.getId());

        Optional<Artist> optionalArtistDelete = artistRepository.findByAlias(artist.getAlias());

        assertThat(optionalArtistDelete).isEmpty();
    }
}