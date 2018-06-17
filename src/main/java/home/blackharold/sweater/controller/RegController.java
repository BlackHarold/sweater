package home.blackharold.sweater.controller;

import home.blackharold.sweater.domain.Roles;
import home.blackharold.sweater.domain.User;
import home.blackharold.sweater.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping ("/registration")
    public String registration() {
        System.out.println("Переход на форму login");
        return "registration";
    }
    
    @PostMapping ("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDB = userRepository.findByName(user.getName());
        System.out.println("POSTmapping registration");
        
        if (userFromDB != null) {
            System.out.println("Такой пользователь уже существует!");
            model.put("message", "Такой пользователь уже существует!");
            return "registration";
        }
        
            System.out.println(user.getName());
            user.setActive(true);
            user.setRoles(Collections.singleton(Roles.USER));
            userRepository.save(user);
            
        return "login";
    }
}
