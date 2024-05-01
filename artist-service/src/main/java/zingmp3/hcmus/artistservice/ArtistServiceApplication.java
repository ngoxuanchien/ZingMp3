package zingmp3.hcmus.artistservice;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import zingmp3.hcmus.artistservice.data.LoadData;
import zingmp3.hcmus.artistservice.data.LoadDataV2;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@SpringBootApplication
public class ArtistServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArtistServiceApplication.class, args);
    }

//    @Bean
//    public GroupedOpenApi tweetsOpenApi(@Value("${application.version}") String appVersion) {
//        String[] paths = { "/tweets/**" };
//        return GroupedOpenApi.builder().
//                group("tweets")
//                .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("Tweets API").version(appVersion)))
//                .pathsToMatch(paths)
//                .build();
//    }


    @Bean
    public CommandLineRunner commandLineRunner(
            LoadDataV2 loadData
    ) {
        return args -> {
            loadData.loadData("ZWZAC9BF");
        };
    }
}
