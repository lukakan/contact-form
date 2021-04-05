package pl.lukakan.contactform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(Message message, String targetEmailAddress, String senderEmailAddress) throws MessagingException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, "UTF-8");
        helper.setFrom(senderEmailAddress);
        helper.setTo(targetEmailAddress);
        helper.setReplyTo(message.getAuthor());
        helper.setSubject(message.getTitle());
        helper.setText(message.getBody(), true);
        javaMailSender.send(mail);

    }
}
