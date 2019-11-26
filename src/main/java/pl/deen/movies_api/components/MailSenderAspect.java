package pl.deen.movies_api.components;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.deen.movies_api.service.MailService;

import javax.mail.MessagingException;

@Aspect
@Component
public class MailSenderAspect {

    private MailService mailService;

    @Autowired
    public MailSenderAspect(MailService mailService) {
        this.mailService = mailService;
    }

    @After("@annotation(pl.deen.movies_api.SendMail)")
    public void sendMail() throws MessagingException {
        mailService.sendMail("*****",
                "MoviesAPI",
                "Dodano nowy film<br>", true);
    }
}
