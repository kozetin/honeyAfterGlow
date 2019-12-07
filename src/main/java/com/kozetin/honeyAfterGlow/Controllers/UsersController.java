package com.kozetin.honeyAfterGlow.Controllers;

import com.kozetin.honeyAfterGlow.Domain.Role;
import com.kozetin.honeyAfterGlow.Domain.User;
import com.kozetin.honeyAfterGlow.Repository.UserRepository;
import com.kozetin.honeyAfterGlow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

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

    @PostMapping
    public String userEdit(Model model, @RequestParam String email, @RequestParam Map<String, String> form, @RequestParam("userId") User user) {

        if (!userService.editUser(email, form, user)) {
            model.addAttribute("message","Email is empty");
            model.addAttribute("userslist",userRepository.findAll());
            return "users";
        }

        return "redirect:/users";

    }
}
