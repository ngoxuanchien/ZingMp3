package hcmus.zingmp3.web.config;

import hcmus.zingmp3.service.album.AlbumService;
import hcmus.zingmp3.web.config.converter.JwtAuthConverter;
import hcmus.zingmp3.web.controller.AlbumController;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class SecurityConfigTest {

    // Nested config so the test doesn't boot PlaylistCoreApplication (JPA repositories/auditing).
    @Configuration
    @Import({SecurityConfig.class, JwtAuthConverter.class, AlbumController.class})
    static class TestConfig {
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;

    @MockBean
    private JwtDecoder jwtDecoder;

    private static ResultMatcher notDenied() {
        return result -> assertThat(result.getResponse().getStatus()).isNotIn(401, 403);
    }

    @Test
    void distributorCannotApproveAlbum() throws Exception {
        mockMvc.perform(put("/api/albums/approved/x").with(csrf())
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_DISTRIBUTOR"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void distributorCannotRejectAlbum() throws Exception {
        mockMvc.perform(put("/api/albums/rejected/x").with(csrf())
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_DISTRIBUTOR"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void distributorCannotReleaseAlbum() throws Exception {
        mockMvc.perform(put("/api/albums/released/x").with(csrf())
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_DISTRIBUTOR"))))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminCanApproveAlbum() throws Exception {
        mockMvc.perform(put("/api/albums/approved/x").with(csrf())
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))))
                .andExpect(notDenied());
    }

    @Test
    void distributorCanUpdateAlbum() throws Exception {
        mockMvc.perform(put("/api/albums").with(csrf())
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_DISTRIBUTOR"))))
                .andExpect(notDenied());
    }

    @Test
    void anonymousCannotSearchMyAlbums() throws Exception {
        mockMvc.perform(get("/api/albums/my-albums/search")
                        .param("title", "a")
                        .param("status", "APPROVED"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void anonymousCannotGetMyAlbums() throws Exception {
        mockMvc.perform(get("/api/albums/my-albums"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void anonymousCanGetAllAlbums() throws Exception {
        mockMvc.perform(get("/api/albums/all"))
                .andExpect(notDenied());
    }

    @Test
    void anonymousUnmatchedRequestIsUnauthorized() throws Exception {
        mockMvc.perform(patch("/api/albums/x").with(csrf()))
                .andExpect(status().isUnauthorized());
    }
}
