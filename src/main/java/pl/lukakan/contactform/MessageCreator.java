package pl.lukakan.contactform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MessageCreator {
    private static final String OWNER_MAIL_TEMPLATE = "ownerMailTemplate";
    private static final String CUSTOMER_MAIL_TEMPLATE = "customerMailTemplate";
    private final TemplateEngine templateEngine;
    private final String domainMail;


    @Autowired
    public MessageCreator(TemplateEngine templateEngine, @Value("${spring.mail.username}") String domainMail) {
        this.templateEngine = templateEngine;
        this.domainMail = domainMail;
    }

    public Message createOwnerMessage(SendMessageForm messageForm) {
        String body = prepareMessageBody(messageForm, OWNER_MAIL_TEMPLATE);
        return new Message(messageForm, body, domainMail, domainMail, messageForm.getAuthorEmailAddress());
    }

    public Message createCustomerMessage(SendMessageForm messageForm) {
        String body = prepareMessageBody(messageForm, CUSTOMER_MAIL_TEMPLATE);
        return new Message(messageForm, body, messageForm.getAuthorEmailAddress(), domainMail, domainMail);
    }

    private String prepareMessageBody(SendMessageForm message, String template) {
        SendMessageForm result = new SendMessageForm();
        result.setAuthorEmailAddress(message.getAuthorEmailAddress());
        result.setContent(message.getContent());
        result.setTitle(message.getTitle());
        Context context = new Context();
        context.setVariable("senderMail", message.getAuthorEmailAddress());
        context.setVariable("senderName", message.getAuthorName());
        context.setVariable("content", message.getContent());
        return templateEngine.process(template, context);
    }
}
