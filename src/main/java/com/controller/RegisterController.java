package com.controller;

import com.model.Credentials;
import com.service.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    //public String registration(HttpServletRequest request, Model model) {
    public String registration(@RequestParam("login") String login, @RequestParam("password") String password, Model model) {
        /*String login = request.getParameter("login");
        String password = request.getParameter("password");*/
        Credentials credentials = new Credentials(login, password);
        securityService.signUp(credentials);
        model.addAttribute(securityService.createCookie());
        return "redirect:/login";
    }
}
