package home.blackharold.sweater.controller;

import home.blackharold.sweater.domain.Message;
import home.blackharold.sweater.domain.User;
import home.blackharold.sweater.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    
    @Autowired
    private MessageRepository messageRepository;
    
    @GetMapping ("/")
    public String home() {
        System.out.println("Контроллер загрузился");
        return "home";
    }
    
    
    @GetMapping ("/main")
    public String main(@RequestParam (required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages = messageRepository.findAll();
        
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        System.out.println("Вход в main");
        return "main";
    }
    
    @PostMapping ("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag, user);
        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        System.out.println("Возвращен main");
        return "main";
    }
}
