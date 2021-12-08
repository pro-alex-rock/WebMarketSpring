package com.controller;

import com.model.Product;
import com.service.ProductService;
import com.service.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = {"/", "/products"})
public class ProductController {

    private final ProductService productService;
    private final SecurityService securityService;

    public ProductController(ProductService productService, SecurityService securityService) {
        this.productService = productService;
        this.securityService = securityService;
    }

    @GetMapping
    public String showAll(HttpServletRequest request, Model model) {
        boolean isAuth = securityService.validateUser(request.getCookies());
        if (isAuth) {
            List<Product> products = productService.selectAll();
            model.addAttribute("products", products);
            return "products";
        } else {
            return "redirect:/login";
        }
    }
}
