package hcmus.zingmp3.web.config;

import hcmus.zingmp3.service.song.SongService;
import hcmus.zingmp3.web.config.converter.JwtAuthConverter;
import hcmus.zingmp3.web.controller.SongController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SecurityConfigTest {

    // Nested config so the test doesn't boot SongCoreApplication (JPA repositories/auditing, gRPC clients).
    @Configuration
    @Import({SecurityConfig.class, JwtAuthConverter.class, SongController.class})
    static class TestConfig {
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongService songService;

    @MockBean
    private JwtDecoder jwtDecoder;

    private static ResultMatcher notDenied() {
        return result -> assertThat(result.getResponse().getStatus()).isNotIn(401, 403);
    }

    @Test
    void anonymousCannotSearchMySongs() throws Exception {
        mockMvc.perform(get("/api/songs/my-songs/search")
                        .param("title", "a")
                        .param("status", "APPROVED"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void userCanSearchMySongs() throws Exception {
        mockMvc.perform(get("/api/songs/my-songs/search")
                        .param("title", "a")
                        .param("status", "APPROVED")
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_USER"))))
                .andExpect(notDenied());
    }

    @Test
    void anonymousCannotGetMySongs() throws Exception {
        mockMvc.perform(get("/api/songs/my-songs"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void anonymousCanGetAllSongs() throws Exception {
        mockMvc.perform(get("/api/songs/all"))
                .andExpect(notDenied());
    }

    @Test
    void distributorCannotApproveSong() throws Exception {
        mockMvc.perform(put("/api/songs/approved/x").with(csrf())
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_DISTRIBUTOR"))))
                .andExpect(status().isForbidden());
    }
}
