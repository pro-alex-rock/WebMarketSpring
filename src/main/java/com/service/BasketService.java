package com.service;


import com.dao.BasketRepository;
import com.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketService {
    private BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public void addProduct(Product product) {
        basketRepository.addProduct(product);
    }

    public List<Product> getBasket() {
        return basketRepository.getAll();
    }
    public void delete(int id) {
        basketRepository.delete(id);
    }

    public void clear() {
        basketRepository.clear();
    }
}
