package com.dao;

import com.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class JdbcProductDao implements DaoResource<Product> {
    private static final Logger logger = LoggerFactory.getLogger(JdbcProductDao.class);
    private final JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Product> selectOne(int id) {
        if (id <= 0) {
            logger.info("Inserted incorrect id.");
            throw new RuntimeException("Inserted incorrect id.");
        }
        return jdbcTemplate.query("SELECT name, price, description FROM products WHERE id=?"
                , new BeanPropertyRowMapper<>(Product.class), new Object[]{id}).stream().findAny();
    }

    @Override
    public Optional<Product> getByName(String name) {
        if (name == null) {
            logger.info("Inserted incorrect name.");
            throw new RuntimeException("Inserted incorrect name.");
        }
        return jdbcTemplate.query("SELECT name, price, description FROM products WHERE name=?"
                , new BeanPropertyRowMapper<>(Product.class), new Object[]{name}).stream().findAny();
    }


    @Override
    public List<Product> selectAll() {
        return jdbcTemplate.query("SELECT id, name, price, description FROM products", new BeanPropertyRowMapper<>(Product.class));
    }

    @Override
    public void create(Product product) {
        jdbcTemplate.update("INSERT INTO products (name, price, description) VALUES (?, ?, ?)",
                product.getName(), product.getPrice(), product.getDescription());
        logger.info("The product {} created.", product);
    }

    @Override
    public void updateOne(Product product) {
        jdbcTemplate.update("UPDATE products SET name = ?, price = ?, description = ? WHERE name = ?",
                product.getName(), product.getPrice(), product.getDescription(), product.getName());
        logger.info("The product: {} updated", product);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
        logger.info("The product with id: {} deleted", id);
    }
}
