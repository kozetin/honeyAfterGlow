package com.kozetin.honeyAfterGlow.Controllers;

import com.kozetin.honeyAfterGlow.Domain.Role;
import com.kozetin.honeyAfterGlow.Domain.User;
import com.kozetin.honeyAfterGlow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String users(Model model) {
        model.addAttribute("userslist",userRepository.findAll());

        return "users";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }
}
