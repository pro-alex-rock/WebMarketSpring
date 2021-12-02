package com.service;


import com.model.Basket;
import com.model.Product;
import org.springframework.stereotype.Service;

@Service
public class BasketService {
    private Basket basket = new Basket();

    public void addProduct(Product product) {
        basket.addProduct(product);
    }
}
