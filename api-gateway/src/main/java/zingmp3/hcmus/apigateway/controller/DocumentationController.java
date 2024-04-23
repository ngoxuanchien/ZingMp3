package zingmp3.hcmus.apigateway.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class DocumentationController {

    @GetMapping(value = "/v3/api-docs/auth-service", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAuthServiceApiDocs() throws IOException {
        Resource resource = new ClassPathResource("api-docs.json");
        return new String(Files.readAllBytes(resource.getFile().toPath()));
    }
}