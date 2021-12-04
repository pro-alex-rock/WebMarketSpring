package com.controller;

import com.model.Product;
import com.service.BasketService;
import com.service.DefaultService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/cart")
public class BasketController {
    private final BasketService basketService;
    private final DefaultService<Product> productService;

    public BasketController(BasketService basketService, DefaultService<Product> productService) {
        this.basketService = basketService;
        this.productService = productService;
    }

    @GetMapping
    public String showBasket(Model model) {
        List<Product> products = basketService.getBasket();
        model.addAttribute("products", products);
        return "basket";
    }

    @GetMapping("/clear")
    public String clearBasket() {
        basketService.clear();
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String addToBasket(@PathVariable("id") String id) {
        Product product = productService.selectOne(Integer.parseInt(id)).get();
        basketService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteFromBasket(@PathVariable("id") String id) {
        basketService.delete(Integer.parseInt(id));
        return "redirect:/basket";
    }
}
