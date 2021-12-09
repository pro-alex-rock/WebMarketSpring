package com.web.controller;

import com.model.User;
import com.service.SecurityService;
import com.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService userService;
    private final SecurityService securityService;

    public LoginController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam("login") String login, @RequestParam("password") String password, Model model) {
        Optional<User> userOptional = userService.getByName(login);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<Cookie> cookieOptional = securityService.refreshCookies(user, password);
            cookieOptional.ifPresent(model::addAttribute);
            return "redirect:/products";
        }
        return "redirect:/registration";
    }
}
