package zingmp3.hcmus.playlistservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import zingmp3.hcmus.playlistservice.auditing.ApplicationAuditAware;

import java.util.UUID;

@Configuration
public class ApplicationConfig {
    @Bean
    public AuditorAware<UUID> auditorAware() {
        return new ApplicationAuditAware();
    }
}
