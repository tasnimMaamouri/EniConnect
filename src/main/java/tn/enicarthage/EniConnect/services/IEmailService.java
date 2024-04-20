package tn.enicarthage.EniConnect.services;

import org.springframework.web.multipart.MultipartFile;

public interface IEmailService {
    String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body);
}
