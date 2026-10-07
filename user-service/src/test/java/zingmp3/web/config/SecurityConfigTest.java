package zingmp3.web.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import zingmp3.service.distributor.DistributorService;
import zingmp3.service.image.ImageService;
import zingmp3.web.config.converter.JwtAuthConverter;
import zingmp3.web.controller.DistributorController;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SecurityConfigTest {

    // Nested config so the test doesn't boot UserServiceApplication (Eureka, Keycloak admin client, Kafka).
    @Configuration
    @Import({SecurityConfig.class, JwtAuthConverter.class, DistributorController.class})
    static class TestConfig {
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DistributorService distributorService;

    @MockBean
    private ImageService imageService;

    @MockBean
    private JwtDecoder jwtDecoder;

    @Test
    void anonymousCannotRegisterDistributor() throws Exception {
        mockMvc.perform(post("/api/distributors/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void userCannotRegisterDistributor() throws Exception {
        mockMvc.perform(post("/api/distributors/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminCanRegisterDistributor() throws Exception {
        ResultMatcher notDenied = result -> assertThat(result.getResponse().getStatus()).isNotIn(401, 403);
        mockMvc.perform(post("/api/distributors/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))))
                .andExpect(notDenied);
    }
}
