package pl.lukakan.contactform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;

@Controller
public class MainController {

    private final MailService mailService;
    private final MessageCreator messageCreator;

    @Autowired
    public MainController(MailService mailService, MessageCreator messageCreator) {
        this.mailService = mailService;
        this.messageCreator = messageCreator;
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("message", new SendMessageForm());
        return "contact";
    }

    @PostMapping("/send")
    public String sendMessage(SendMessageForm message, RedirectAttributes redirectAttributes) {
        Message ownerMessage = messageCreator.createOwnerMessage(message);
        Message clientMessage = messageCreator.createCustomerMessage(message);

        try {
            mailService.sendMail(ownerMessage);
            mailService.sendMail(clientMessage);
            redirectAttributes.addFlashAttribute("feedback", "success");
        } catch (MessagingException e) {
            redirectAttributes.addFlashAttribute("feedback", "error");
            e.printStackTrace();
        }
        return "redirect:/contact";
    }
}
