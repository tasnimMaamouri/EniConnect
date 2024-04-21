package tn.enicarthage.EniConnect.services;

import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.multipart.MultipartFile;

public interface IEmailService {
    void sendEmail(String to, String subject, String text) throws MessagingException;

}
