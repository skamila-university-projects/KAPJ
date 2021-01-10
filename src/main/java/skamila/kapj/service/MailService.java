package skamila.kapj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import skamila.kapj.configuration.SpringConfiguration;
import skamila.kapj.domain.AppUser;

@Service("mailService")
public class MailService {

    private final JavaMailSender emailSender;

    @Autowired
    public MailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendMail(AppUser appUser) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(SpringConfiguration.MAIL);
        message.setTo(appUser.getEmail());
        message.setSubject("Doctor24 - link aktywacyjny");
        String welcome = "Witaj " + appUser.getFirstName() + "!\nSerdecznie witamy cię w witrynie Doctor24. Aby aktywować konto kliknij w link poniżej:\n";
        String link = "http://localhost:8080/activateAccount?token=" + appUser.getToken();
        message.setText(welcome + link);
        emailSender.send(message);
    }


}
