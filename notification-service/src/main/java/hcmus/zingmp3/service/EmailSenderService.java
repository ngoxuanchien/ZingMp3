package hcmus.zingmp3.service;

public interface EmailSenderService {

    void sendEmail(String toEmail, String subject, String body);

}
