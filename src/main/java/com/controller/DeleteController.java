package com.controller;

import com.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products/delete")
public class DeleteController {

    private final ProductService productService;

    public DeleteController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        productService.delete(Integer.parseInt(id));
        return "redirect:/products";
    }
}
