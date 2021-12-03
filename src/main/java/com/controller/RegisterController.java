package com.controller;

import com.model.User;
import com.service.SecurityService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/registration")
public class RegisterController {
    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public RegisterController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping
    public String registrationPage() {
        return "registration";
    }

    @PostMapping
    public String registration(HttpServletRequest request, Model model) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String sole = securityService.getUUID();
        String passwordEncoded = securityService.getPasswordEncode(password + sole);

        User user = new User();
        user.setName(login);
        user.setPassword(passwordEncoded);
        user.setSole(sole);

        userService.create(user);
        return "redirect:/products";
    }
}
