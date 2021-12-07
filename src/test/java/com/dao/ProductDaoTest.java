package com.dao;

import com.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductDaoTest {
    private final JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
    private ProductDao productDao = new ProductDao(jdbcTemplate);


    @Test
    public void shouldReturnProductByIdSuccess() {
        Product expected = new Product(1, "Name", new BigDecimal(10), "TestDesc");
        when(jdbcTemplate.query("SELECT name, price, description FROM products WHERE id=?"
                , new BeanPropertyRowMapper<>(Product.class), new Object[]{1}).stream().findAny())
                .thenReturn(Optional.of(new Product(1, "Name", new BigDecimal(10), "TestDesc")));
        Product actual = productDao.selectOne(1).get();
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
        Assertions.assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void shouldReturnProductByIdFail() {
        Product expected = new Product(1, "Name", new BigDecimal(10), "TestDesc");
        when(jdbcTemplate.query("SELECT name, price, description FROM products WHERE id=?"
                , new BeanPropertyRowMapper<>(Product.class), new Object[]{1}).stream().findAny())
                .thenReturn(Optional.of(new Product(2, "Name", new BigDecimal(20), "TestDesc")));
        Product actual = productDao.selectOne(1).get();
        Assertions.assertNotEquals(expected.getName(), actual.getName());
        Assertions.assertNotEquals(expected.getPrice(), actual.getPrice());
        Assertions.assertNotEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void shouldReturnProductByNameSuccess() {
        Product expected = new Product(1, "Name", new BigDecimal(10), "TestDesc");
        when(jdbcTemplate.query("SELECT name, price, description FROM products WHERE name='Name'"
                , new BeanPropertyRowMapper<>(Product.class), new Object[]{1}).stream().findAny())
                .thenReturn(Optional.of(new Product(1, "Name", new BigDecimal(10), "TestDesc")));
        Product actual = productDao.getByName("Name").get();
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getPrice(), actual.getPrice());
        Assertions.assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void shouldReturnProductByNameFail() {
        Product expected = new Product(1, "Name", new BigDecimal(10), "TestDesc");
        when(jdbcTemplate.query("SELECT name, price, description FROM products WHERE name='Name'"
                , new BeanPropertyRowMapper<>(Product.class), new Object[]{1}).stream().findAny())
                .thenReturn(Optional.of(new Product(1, "OtherName", new BigDecimal(10), "TestDesc")));
        Product actual = productDao.getByName("Name").get();
        Assertions.assertNotEquals(expected.getName(), actual.getName());
        Assertions.assertNotEquals(expected.getPrice(), actual.getPrice());
        Assertions.assertNotEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void shouldReturnListOfProductsSuccess() {
        List<Product> expected = List.of(
                new Product(1, "Name", new BigDecimal(10), "TestDesc")
                , new Product(1, "OtherName", new BigDecimal(10), "TestDesc"));
        when(jdbcTemplate.query("SELECT id, name, price, description FROM products", new BeanPropertyRowMapper<>(Product.class)))
                .thenReturn(List.of(
                        new Product(1, "Name", new BigDecimal(10), "TestDesc")
                        , new Product(1, "OtherName", new BigDecimal(10), "TestDesc")));
        List<Product> actual = productDao.selectAll();
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected.size(), actual.size());

    }
}