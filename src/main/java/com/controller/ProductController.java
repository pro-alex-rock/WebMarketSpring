package com.controller;

import com.model.Product;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping({"/", "/products"})
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

@GetMapping
    public String showAll(HttpServletRequest request, Model model) {
        boolean isAuth = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    isAuth = true;
                }
            }
        }
        if (isAuth) {
            List<Product> products = productService.selectAll();
            model.addAttribute("products", products);
            return "products";
        } else {
            return "redirect:/login";
        }
    }
}
