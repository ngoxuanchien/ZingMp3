package hcmus.zingmp3;

import hcmus.zingmp3.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class NotificationServiceApplication {

    private final EmailSenderService senderService;

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() {
        senderService.sendEmail("ngoxuanchien9a@gmail.com",
                                "Test email",
                                "Hello, this is a test email from ZingMp3");
    }

}
