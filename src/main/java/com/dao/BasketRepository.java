package com.dao;

import com.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class BasketRepository {
    private final List<Product> products = Collections.synchronizedList(new ArrayList<>());


    public synchronized void addProduct(Product product) {
        products.add(product);
    }

    public synchronized List<Product> getAll() {
        return products;
    }

    public synchronized void delete(int id) {
        products.removeIf(x -> x.getId() == id);
    }

    public synchronized void clear() {
        products.clear();
    }
}
