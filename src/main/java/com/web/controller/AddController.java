package com.web.controller;

import com.model.Product;
import com.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add")
public class AddController {
    private final ProductService productService;

    public AddController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String addPage() {
        return "add";
    }

    @PostMapping
    public String add(@ModelAttribute("product")Product product) {
        productService.create(product);
        return "redirect:/products";
    }
}
