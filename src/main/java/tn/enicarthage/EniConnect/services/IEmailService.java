package tn.enicarthage.EniConnect.services;

import org.springframework.web.multipart.MultipartFile;

public interface IEmailService {
    //String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body);

    void sendSimpleMessage(
            String to, String subject, String text);
}
