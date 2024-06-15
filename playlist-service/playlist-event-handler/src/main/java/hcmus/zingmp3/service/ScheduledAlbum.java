package hcmus.zingmp3.service;

import hcmus.zingmp3.common.domain.model.Album;
import hcmus.zingmp3.common.domain.model.AlbumStatus;
import hcmus.zingmp3.common.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledAlbum {

    private final AlbumRepository albumRepository;

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void scheduleFixedRateTask() {
        List<Album> albums = albumRepository.findAllByStatus(AlbumStatus.APPROVED);
        albums.forEach(album -> {
            LocalDateTime releaseDate = album.getReleaseDate();
            LocalDateTime currentDate = LocalDateTime.now();

            if (!releaseDate.isAfter(currentDate)) {
                album.released();
                albumRepository.save(album);
            }
        });

        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }

}
