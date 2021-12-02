package com.controller;

import com.model.Product;
import com.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add")
public class AddController {
    private final DefaultService<Product> productService;

    @Autowired
    public AddController(DefaultService<Product> productService) {
        this.productService = productService;
    }

    @GetMapping
    public String addPage() {
        return "add";
    }

    @PostMapping
    public String add(@ModelAttribute("product")Product product, Model model) {
        productService.create(product);
        return "redirect:/products";
    }
}
