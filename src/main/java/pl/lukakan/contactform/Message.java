package pl.lukakan.contactform;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public class Message {

    private final String body;
    private final String title;
    private final String replyTo;
    private final TemplateEngine templateEngine;
    private final String sendTo;
    private final String sendFrom;
    private final String template;

    public Message(SendMessageForm messageForm, TemplateEngine templateEngine, String sendTo, String sendFrom, String template, String replyTo) {
        this.sendTo = sendTo;
        this.templateEngine = templateEngine;
        this.title = messageForm.getTitle();
        this.replyTo = replyTo;
        this.sendFrom = sendFrom;
        this.template = template;
        this.body = prepareMessageBody(messageForm);
    }

    private String prepareMessageBody(SendMessageForm message) {
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

    public String getSendTo() {
        return sendTo;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public String getSendFrom() {
        return sendFrom;
    }
}
