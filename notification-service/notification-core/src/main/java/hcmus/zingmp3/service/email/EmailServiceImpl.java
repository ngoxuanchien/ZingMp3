package hcmus.zingmp3.service.email;

import hcmus.zingmp3.notification.domain.model.SystemEmail;
import hcmus.zingmp3.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final UserService userService;
    private final EmailSenderService emailSenderService;

    @Override
    public void sendEmail(SystemEmail systemEmail) {
        var user = userService.getById(systemEmail.getTo());
        emailSenderService.sendEmail(user.getEmail(), systemEmail.getSubject(), systemEmail.getBody());
    }
}
