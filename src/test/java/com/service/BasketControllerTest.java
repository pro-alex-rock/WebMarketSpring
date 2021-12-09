package com.service;

import com.dao.BasketRepository;
import com.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasketControllerTest {
    private final BasketService basketService = new BasketService(new BasketRepository());

    @BeforeEach
    public void setUp() {
        basketService.clear();
    }

    @Test
    public void shouldAddProductSuccess() {
        Product expectedProduct = new Product();
        expectedProduct.setName("Name");
        expectedProduct.setPrice(new BigDecimal(1));
        expectedProduct.setDescription("Description");
        basketService.addProduct(expectedProduct);
        Product actualProduct = basketService.getBasket().get(0);
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    public void shouldAddSuccess() {
        Product product1 = new Product();
        product1.setName("Name1");
        product1.setPrice(new BigDecimal(1));
        product1.setDescription("Description1");
        Product product2 = new Product();
        product2.setName("Name2");
        product2.setPrice(new BigDecimal(2));
        product2.setDescription("Description2");
        List<Product> expectedList = List.of(product1, product2);
        basketService.addProduct(product1);
        basketService.addProduct(product2);
        List<Product> actualList = basketService.getBasket();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void shouldCompareFail() {
        Product product1 = new Product();
        product1.setName("Name1");
        product1.setPrice(new BigDecimal(1));
        product1.setDescription("Description1");
        Product product2 = new Product();
        product2.setName("Name2");
        product2.setPrice(new BigDecimal(2));
        product2.setDescription("Description2");
        List<Product> expectedList = List.of(product1, product1);
        basketService.addProduct(product1);
        basketService.addProduct(product2);
        List<Product> actualList = basketService.getBasket();
        assertNotEquals(expectedList, actualList);
    }

    @Test
    public void shouldClearBasketSuccess() {
        Product product1 = new Product();
        product1.setName("Name1");
        product1.setPrice(new BigDecimal(1));
        product1.setDescription("Description1");
        Product product2 = new Product();
        product2.setName("Name2");
        product2.setPrice(new BigDecimal(2));
        product2.setDescription("Description2");
        basketService.clear();
        List<Product> actualList = basketService.getBasket();
        assertTrue(actualList.size() == 0);
    }

    @Test
    public void shouldDeleteSuccess() {
        Product product = new Product();
        product.setName("Name1");
        product.setPrice(new BigDecimal(1));
        product.setDescription("Description1");
        basketService.addProduct(product);
        Product actualProduct = basketService.getBasket().get(0);
        List<Product> actualList = basketService.getBasket();
        assertEquals(product, actualProduct);
        assertTrue(actualList.size() == 1);

        basketService.delete(0);
        assertTrue(basketService.getBasket().size() == 0);
    }

}