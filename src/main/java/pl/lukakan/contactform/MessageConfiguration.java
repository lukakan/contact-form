package pl.lukakan.contactform;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
public class MessageConfiguration {
    @Value("${client-message.property.header}")
    private String clientMailHeader;

    @Value("${client-message.property.content}")
    private String clientMessageContent;

    @Value("${owner-message.property.header}")
    private String ownerMailHeader;

    @Value("${message-send.success}")
    private String messageSendSuccess;

    @Value("${message-send.error}")
    private String messageSendError;

    @Value("${spring.mail.username}")
    private String ownerMailAddress;

    public String getClientMailHeader() {
        return clientMailHeader;
    }

    public String getClientMessageContent() {
        return clientMessageContent;
    }

    public String getOwnerMailHeader() {
        return ownerMailHeader;
    }

    public String getMessageSendSuccess() {
        return messageSendSuccess;
    }

    public String getMessageSendError() {
        return messageSendError;
    }

    public String getOwnerMailAddress() {
        return ownerMailAddress;
    }
}
