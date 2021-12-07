package com.service;

import com.dao.ProductDao;
import com.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    private final ProductDao productDao;

    public ProductService(ProductDao productDao){
        this.productDao = productDao;
    }

    public Optional<Product> select(int id) {
        return productDao.selectOne(id);
    }

    public Optional<Product> getByName(String name) {
        return productDao.getByName(name);
    }

    public List<Product> selectAll() {
        return productDao.selectAll();
    }

    public void create(Product product) {
        productDao.create(product);
    }

    public void update(Product product) {
        productDao.updateOne(product);
    }

    public void delete(int id) {
        productDao.delete(id);
    }
}
