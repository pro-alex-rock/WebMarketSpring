package com.dao;

import com.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product select(int id) {
        if (id <= 0) {
            logger.info("Inserted incorrect id.");
            throw new RuntimeException("Inserted incorrect id.");
        }
        return jdbcTemplate.queryForObject("SELECT name, price, description FROM products WHERE id=?"
                , new BeanPropertyRowMapper<>(Product.class), id);
    }

    public Product getByName(String name) {
        if (name == null) {
            logger.info("Inserted incorrect name.");
            throw new RuntimeException("Inserted incorrect name.");
        }
        return jdbcTemplate.queryForObject("SELECT name, price, description FROM products WHERE name=?"
                , new BeanPropertyRowMapper<>(Product.class), name);
    }


    public List<Product> selectAll() {
        return jdbcTemplate.query("SELECT id, name, price, description FROM products"
                , new BeanPropertyRowMapper<>(Product.class));
    }

    public void create(Product product) {
        jdbcTemplate.update("INSERT INTO products (name, price, description) VALUES (?, ?, ?)",
                product.getName(), product.getPrice(), product.getDescription());
        logger.info("The product {} created.", product);
    }

    public void updateOne(Product product) {
        jdbcTemplate.update("UPDATE products SET name = ?, price = ?, description = ? WHERE name = ?",
                product.getName(), product.getPrice(), product.getDescription(), product.getName());
        logger.info("The product: {} updated", product);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
        logger.info("The product with id: {} deleted", id);
    }
}
