package zingmp3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/songs/demo")
public class DemoController {
    @GetMapping
    public String demo() {
        return "Hello from demo";
    }
}
