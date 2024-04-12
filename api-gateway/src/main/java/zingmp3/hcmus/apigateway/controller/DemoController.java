package zingmp3.hcmus.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        return "Hello from API Gateway";
    }
}
