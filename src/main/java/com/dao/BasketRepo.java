package com.dao;

import com.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BasketRepo {
    private final List<Product> products = new ArrayList<>();


    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getAll() {
        return products;
    }

    public void delete(int id) {
        products.removeIf(x -> x.getId() == id);
    }

    public void clear() {
        products.clear();
    }
}
