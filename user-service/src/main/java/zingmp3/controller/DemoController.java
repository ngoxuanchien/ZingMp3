package zingmp3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/demo")
public class DemoController {
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public String demo() {
        return "Demo";
    }

    @GetMapping("/admin")
    public String demoAdmin() {
        return "adminnnnnnn";
    }
}
