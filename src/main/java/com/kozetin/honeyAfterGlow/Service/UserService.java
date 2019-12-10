package com.kozetin.honeyAfterGlow.Service;

import com.kozetin.honeyAfterGlow.Domain.Role;
import com.kozetin.honeyAfterGlow.Domain.User;
import com.kozetin.honeyAfterGlow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public String addUser(User user) {
        //email validation
        if (user.getEmail().isEmpty() || user.getEmail()==null) {
            return "Введите значение в поле Адресс электронной почты";
        }//

        User userFromDb = userRepository.findByEmail(user.getEmail());

        //registration data validation
        if (userFromDb != null) {
            return "Пользователь с таким Email уже зарегистрирован";
        }

        if (user.getFirstName().isEmpty() || user.getFirstName()==null) {
            return "Введите значение в поле Имя";
        }

        if (user.getLastName().isEmpty() || user.getLastName()==null) {
            return "Введите значение в поле Фамилия";
        }

        if (user.getPassword().isEmpty() || user.getPassword()==null) {
            return "Введите значение в поле Пароль";
        }
        //

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "OK";
    }

    public Boolean editUser (String email, Map<String,String> form, User user) {
        if (email.isEmpty()) { return false; } else {
            user.setEmail(email);
        }

        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);

        return true;
    }
}
