package zingmp3.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import zingmp3.auditing.ApplicationAuditAware;

import java.util.UUID;

@Configuration
public class ApplicationConfig {
    @Bean
    public AuditorAware<UUID> auditorAware() {
        return new ApplicationAuditAware();
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
