package com.service;


import com.dao.BasketRepo;
import com.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    private BasketRepo basketRepo;

    public BasketService(BasketRepo basketRepo) {
        this.basketRepo = basketRepo;
    }

    public void addProduct(Product product) {
        basketRepo.addProduct(product);
    }

    public List<Product> getBasket() {
        return basketRepo.getAll();
    }
    public void delete(int id) {
        basketRepo.delete(id);
    }

    public void clear() {
        basketRepo.clear();
    }
}
