package com.controller;

import com.model.Product;
import com.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/edit")
public class EditController {
    private final DefaultService<Product> productService;

    @Autowired
    public EditController(DefaultService<Product> productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") String id, Model model) {
        Product product = productService.selectOne(Integer.parseInt(id));
        model.addAttribute("product", product);
        return "redirect:/edit";
    }

    @PostMapping
    private String edit(@ModelAttribute("product")Product product) {
        productService.updateOne(product);
        return "redirect:/products";
    }

}
