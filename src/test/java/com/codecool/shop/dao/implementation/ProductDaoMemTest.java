package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoMemTest {

    private ProductDao productDao = ProductDaoMem.getTestInstance();
    private Supplier testSupplier;
    private ProductCategory testCategory;

    @BeforeEach
    private void setupDao() {
        testSupplier = new Supplier("testSupp", "test");
        testCategory = new ProductCategory("testCat", "Hardware", "test");

        for (int i = 1; i < 20; i++) {
            productDao.remove(i);
        }

        for (int i = 0; i < 10; i++) {
            Product product = new Product("testProd" + i, 49.9f, "USD", "test.", testCategory, testSupplier, "product_1.jpg");
            productDao.add(product);
        }
    }

    @Test
    void testAdd() {

        List<Product> products = productDao.getAll();

        for (int i = 0; i < 10; i++) {
            Product product = products.get(i);
            assertEquals(product.getName(), "testProd" + i);
        }
    }

    @Test
    void testFind() {

        for (int i = 0; i < 20; i++) {
            if (i == 0) {
                assertThrows(IllegalArgumentException.class, () -> {
                    productDao.find(0);
                });
            } else if (i <= 10) {
                assertNotNull(productDao.find(i));
            } else {
                assertNull(productDao.find(i));
            }
        }
    }

    @Test
    void testRemove() {

        for (int i = 1; i <= 10; i++) {
            productDao.remove(i);
            assertNull(productDao.find(i));
        }
    }

    @Test
    void testGetAll() {
        List<Product> actuals = productDao.getAll();
        for (int i = 0; i < 10; i++) {
            Product actual = actuals.get(i);

            Product expected = productDao.find(i + 1);
            assertEquals(expected, actual);
        }
    }

    @Test
    void testGetBySupplier() {
        Supplier wrongSupplier = new Supplier("not a supplier", "definitely");

        assertNotNull(productDao.getBy(testSupplier));

        List<Product> actual = productDao.getBy(wrongSupplier);
        List<Product> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

    @Test
    void testGetByProductCategory() {
        ProductCategory wrongCategory = new ProductCategory("not a category", "nope", "really not");

        assertNotNull(productDao.getBy(testCategory));

        List<Product> actual = productDao.getBy(wrongCategory);
        List<Product> expected = new ArrayList<>();

        assertEquals(expected, actual);
    }

}