package hcmus.zingmp3.searchservice;

import hcmus.zingmp3.searchservice.dto.SongDTO;
import hcmus.zingmp3.searchservice.repository.SongDAO;
import hcmus.zingmp3.searchservice.service.SongService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static org.springframework.cloud.client.discovery.ReactiveDiscoveryClient.LOG;

@SpringBootApplication
@EnableDiscoveryClient
public class SearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);

        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
        connectionFactory.afterPropertiesSet();

        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(StringRedisSerializer.UTF_8);
        template.afterPropertiesSet();

        template.opsForValue().set("foo", "bar");

        LOG.info("Value at foo:" + template.opsForValue().get("foo"));

        connectionFactory.destroy();
    }

    @Bean
    public CommandLineRunner commandLineRunner(SongService songService) {
        return args -> {
            songService.getAllSongs();
        };
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(SongDAO songDAO) {
//        return args -> {
//            SongDTO song = SongDTO.builder()
//                    .id(1)
//                    .songName("Âm thầm bên em (Single)")
//                    .thumbnail("https://i.ytimg.com/vi/30KI5SuECuc/hqdefault.jpg")
//                    .songWriter("Sơn Tùng M-TP")
//                    .lyric("Bài Hát: Âm Thầm Bên Em\n")
//                    .duration(291)
//                    .providedBy("VIVI ENM")
//                    .liked(83)
//                    .played(10)
//                    .build();
//
//            songDAO.save(song);
//        };
//    }

}
