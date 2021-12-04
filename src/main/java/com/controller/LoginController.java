package com.controller;

import com.model.User;
import com.service.SecurityService;
import com.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
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
    public String login(HttpServletRequest request, Model model) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Optional<User> userOptional = userService.getByName(login);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String passwordEncode = securityService.getPasswordEncode(password + user.getSole());
            if (Objects.equals(passwordEncode, user.getPassword())) {
                Cookie cookie = new Cookie("user-token", user.getSole());
                //@Value("${cookie.expiration.date}")
                int cookieExpiration = 60000;
                cookie.setMaxAge(cookieExpiration);
                model.addAttribute(cookie);
                return "redirect:/products";
            }
        }
        return "redirect:/registration";
    }
}
