package hcmus.zingmp3.service.email;

import hcmus.zingmp3.notification.domain.model.SystemEmail;

public interface EmailService {
    void sendEmail(SystemEmail systemEmail);
}
