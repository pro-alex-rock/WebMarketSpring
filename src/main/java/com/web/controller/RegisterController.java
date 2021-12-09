package com.web.controller;

import com.model.Credentials;
import com.service.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import java.util.UUID;

@Controller
@RequestMapping("/registration")
public class RegisterController {
    private final SecurityService securityService;

    public RegisterController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping
    public String registrationPage() {
        return "registration";
    }

    @PostMapping
    public String registration(@RequestParam("login") String login, @RequestParam("password") String password, Model model) {
        Credentials credentials = new Credentials(login, password);
        securityService.signUp(credentials);
        String token = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("user-token", token);
        model.addAttribute(cookie);
        return "redirect:/login";
    }
}
