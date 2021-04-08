package pl.lukakan.contactform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.thymeleaf.TemplateEngine;

@Repository
public class MessageRepository {
    private final TemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String domainMail;
    private static final String ownerTemplate = "ownerMailTemplate";
    private static final String customerTemplate = "customerMailTemplate";

    @Autowired
    public MessageRepository(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public Message createOwnerMessage(SendMessageForm messageForm) {
        return new Message(messageForm, templateEngine, domainMail, domainMail, ownerTemplate, messageForm.getAuthorEmailAddress());
    }

    public Message createCustomerMessage(SendMessageForm messageForm) {
        return new Message(messageForm, templateEngine, messageForm.getAuthorEmailAddress(), domainMail, customerTemplate, domainMail);
    }
}
