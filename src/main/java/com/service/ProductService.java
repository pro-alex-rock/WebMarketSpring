package com.service;

import com.dao.DaoResource;
import com.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService implements DefaultService<Product> {
    private final DaoResource<Product> productDao;

    @Autowired
    public ProductService(DaoResource<Product> productDao){
        this.productDao = productDao;
    }
    //private final DaoResource productDao = ProductDaoFactory.getInstance(new PostgresSource());

    @Override
    public Product selectOne(int id) {
        return (Product) productDao.selectOne(id);
    }

    @Override
    public Optional<Product> getByName(String name) {
        return productDao.getByName(name);
    }

    @Override
    public List<Product> selectAll() {
        return productDao.selectAll();
    }

    @Override
    public void create(Product product) {
        productDao.create(product);
    }

    @Override
    public void updateOne(Product product) {
        productDao.updateOne(product);
    }

    @Override
    public void delete(int id) {
        productDao.delete(id);
    }
}
