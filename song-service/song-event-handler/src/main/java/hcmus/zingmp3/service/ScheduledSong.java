package hcmus.zingmp3.service;

import hcmus.zingmp3.common.domain.model.Song;
import hcmus.zingmp3.common.domain.model.SongStatus;
import hcmus.zingmp3.common.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledSong {

    private final SongRepository songRepository;

    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void scheduleFixedRateTask() {
        List<Song> songs = songRepository.findAllByStatus(SongStatus.APPROVED);
        songs.forEach(song -> {
            LocalDate releaseDate = Instant.ofEpochMilli(song.getReleaseDate())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalDate currentDate = LocalDate.now();

            if (!releaseDate.isAfter(currentDate)) {
                song.released();
                songRepository.save(song);
            }
        });

        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }
}
