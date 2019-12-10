package com.kozetin.honeyAfterGlow.Controllers;

import com.kozetin.honeyAfterGlow.Domain.User;
import com.kozetin.honeyAfterGlow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() { return "registration"; }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        String addUserMessage = userService.addUser(user);
        if (!addUserMessage.equals("OK")) {
            model.addAttribute("message",addUserMessage);
            return "registration";
        }

        return "redirect:/login";
    }
}
