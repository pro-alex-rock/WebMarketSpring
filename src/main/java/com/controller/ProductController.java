package com.controller;

import com.model.Product;
import com.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = {"/", "/products"})
public class ProductController {

    private final ProductService productService;

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


    @GetMapping("/cart")
    public RedirectView toBasket() {
        return new RedirectView("basket");
    }

    @GetMapping("/cart/${id}")
    public RedirectView addToBasket(@PathVariable("id") String id) {
        return new RedirectView(("/basket/" + id));
    }

    @GetMapping("/cart/delete/{id}")
    public RedirectView deleteFromBasket(@PathVariable("id") String id) {
        return new RedirectView("/cart/delete/" + id);
    }

    @GetMapping("/cart/clear")
    public RedirectView clearBasket() {
        return new RedirectView("/cart/clear");
    }

    @PostMapping("/edit/{id}")
    public RedirectView editProduct(@PathVariable("id") String id) {
        return new RedirectView("edit/" + id);
    }

    @PostMapping("/delete/{id}")
    public RedirectView deleteProduct(@PathVariable("id") String id) {
        return new RedirectView("delete/" + id);
    }
}
