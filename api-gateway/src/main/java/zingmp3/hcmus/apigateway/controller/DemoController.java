package zingmp3.hcmus.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class DemoController {

    @GetMapping("/demo/client_admin")
    public String helloAdmin() {
        return "Hello client admin from API Gateway";
    }

    @GetMapping("/demo/client_user")
    public String helloUser() {
        return "Hello client user from API Gateway";
    }
}
