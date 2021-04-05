package pl.lukakan.contactform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@Controller
public class MainController {

    private final MailService mailService;
    private final TemplateEngine templateEngine;
    private final MessageConfiguration messageConfiguration;

    @Autowired
    public MainController(MailService mailService, TemplateEngine templateEngine, MessageConfiguration messageConfiguration) {
        this.mailService = mailService;
        this.templateEngine = templateEngine;
        this.messageConfiguration = messageConfiguration;
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("message", new Message());
        return "contact";
    }

    @PostMapping("/send")
    public String sendMessage(Message message, RedirectAttributes redirectAttributes) {
        Message ownerMessage = prepareMessageBody(message, messageConfiguration.getOwnerMailHeader(), message.getContent());
        Message clientMessage = prepareMessageBody(message, messageConfiguration.getClientMailHeader(), messageConfiguration.getClientMessageContent());
        try {
            mailService.sendMail(ownerMessage, messageConfiguration.getOwnerMailAddress(), messageConfiguration.getOwnerMailAddress());
            mailService.sendMail(clientMessage, clientMessage.getAuthor(), messageConfiguration.getOwnerMailAddress());
            redirectAttributes.addFlashAttribute("success", messageConfiguration.getMessageSendSuccess());
        } catch (MessagingException e) {
            redirectAttributes.addFlashAttribute("error", messageConfiguration.getMessageSendError());
            e.printStackTrace();
        }
        return "redirect:/contact";
    }

    private Message prepareMessageBody(Message message, String headerContent, String messageContent) {
        Message result = new Message();
        result.setAuthor(message.getAuthor());
        result.setContent(message.getContent());
        result.setTitle(message.getTitle());
        Context context = new Context();
        context.setVariable("sender", message.getAuthor());
        context.setVariable("header", headerContent);
        context.setVariable("content", messageContent);
        String body = templateEngine.process("mailTemplate", context);
        result.setBody(body);
        return result;
    }
}
