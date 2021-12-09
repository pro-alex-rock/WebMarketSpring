package com.web.controller;

import com.model.Product;
import com.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products/edit")
public class EditController {
    private final ProductService productService;

    public EditController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") String id, Model model) {
        Product product = productService.select(Integer.parseInt(id)).get();
        model.addAttribute("product", product);
        return "redirect:/edit";
    }

    @PostMapping
    private String edit(@ModelAttribute("product")Product product) {
        productService.update(product);
        return "redirect:/products";
    }

}
