package home.blackharold.sweater.controller;

import home.blackharold.sweater.domain.Message;
import home.blackharold.sweater.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {
    
    @Autowired
    private MessageRepository messageRepository;
    
    @GetMapping("/")
    public String home(){
        System.out.println("Контроллер загрузился");
        return "home";
    }
    
    
    @GetMapping ("/main")
    public String main(Map<String, Object> mModel) {
        Iterable<Message> messages = messageRepository.findAll();
        mModel.put("messages", messages);
        System.out.println("Вход в main");
        return "main";
    }
    
    @PostMapping ("/main")
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> mModel) {
        Message message = new Message(text, tag);
        messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();
        mModel.put("messages", messages);
        System.out.println("Возвращен main");
        return "main";
    }
    
    @PostMapping ("/filter")
    public String filter(@RequestParam(required = false, defaultValue = "") String filter, Map<String, Object> mModel) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }
            mModel.put("messages", messages);
            return "main";
    }
}
