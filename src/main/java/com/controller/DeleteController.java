package com.controller;

import com.model.Product;
import com.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete")
public class DeleteController {

    private final DefaultService<Product> productService;

    @Autowired
    public DeleteController(DefaultService<Product> productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        productService.delete(Integer.parseInt(id));
        return "redirect:/products";
    }
}
