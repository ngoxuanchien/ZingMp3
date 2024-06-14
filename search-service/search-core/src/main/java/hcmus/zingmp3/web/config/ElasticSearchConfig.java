package hcmus.zingmp3.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.Nonnull;


@Configuration
@EnableElasticsearchRepositories(basePackages = "hcmus.zingmp3.repository")
@ComponentScan(basePackages = "hcmus.zingmp3.repository")
public class ElasticSearchConfig extends ElasticsearchConfiguration {
    @Override
    public @Nonnull ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
    }
}
