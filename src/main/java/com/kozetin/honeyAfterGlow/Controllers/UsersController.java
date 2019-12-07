package com.kozetin.honeyAfterGlow.Controllers;

import com.kozetin.honeyAfterGlow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users",userRepository.findAll());

        return "users";
    }
}
