package tn.enicarthage.EniConnect.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class IEmailServiceImpl implements IEmailService {
    @Value("${spring.mail.username}")
    private String fromEmail;

  /*  @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body) {
        try {
            MimeMessage mimeMessage =  javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setCc(cc);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            for (int i = 0; i < file.length; i++) {
                mimeMessageHelper.addAttachment(file[i].getOriginalFilename(), new ByteArrayResource(file[i].getBytes()));
            }

            javaMailSender.send(mimeMessage);

            return "mail send";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
  @Autowired
  private JavaMailSender emailSender;
@Override
    public void sendSimpleMessage(
        String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ines.zairi@enicar.ucar.tn");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);

    }
}


