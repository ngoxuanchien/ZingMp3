package zingmp3.hcmus.playlistservice.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
//@EnableR2dbcRepositories
//@RequiredArgsConstructor
public class ApplicationConfig  {

//    @Value("${spring.r2dbc.connection-url}")
//    private String connectionUrl;
//
//    @Override
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        System.out.println(connectionUrl);
//
//        return ConnectionFactories.get(connectionUrl);
//    }
//
//    @Bean
//    public R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory) {
//        return new R2dbcEntityTemplate(connectionFactory);
//    }
//
//    @Override
//    protected List<Object> getCustomConverters() {
//        return super.getCustomConverters();
//    }

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        final int size = 16 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies
                .builder()
                .codecs(codecs -> codecs.defaultCodecs()
                        .maxInMemorySize(size))
                .build();
        return WebClient
                .builder()
                .exchangeStrategies(strategies);
    }
}
