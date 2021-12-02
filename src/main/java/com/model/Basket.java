package com.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class Basket {
    private List<Product> products = new ArrayList<>();
/*
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }*/

    public void addProduct(Product product) {
        products.add(product);
    }
}
