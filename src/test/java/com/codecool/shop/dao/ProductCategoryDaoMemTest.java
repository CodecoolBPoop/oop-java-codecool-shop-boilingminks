package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoMemTest {

    private ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getTestInstance();

    @BeforeEach
    private void setupDao() {

        for (int i = 1; i < 20; i++) {
            productCategoryDao.remove(i);
        }

        for (int i = 0; i < 10; i++) {
            productCategoryDao.add(new ProductCategory("test" + i, "dep", "desc"));
        }
    }

    @Test
    void testAdd() {
        for (int i = 0; i < 10; i++) {
            ProductCategory category = productCategoryDao.getAll().get(i);
            assertEquals(category.getName(), "test" + i);
        }
    }

    @Test
    void testFind() {
        for (int i = 0; i < 20; i++) {
            if (i == 0) {
                assertThrows(IllegalArgumentException.class, () -> {
                    productCategoryDao.find(0);
                });
            } else if (i <= 10) {
                assertNotNull(productCategoryDao.find(i));
            } else {
                assertNull(productCategoryDao.find(i));
            }
        }
    }

    @Test
    void testRemove() {
        for (int i = 1; i <= 10; i++) {
            productCategoryDao.remove(i);
            assertNull(productCategoryDao.find(i));
        }
    }

    @Test
    void testGetAll() {
        List<ProductCategory> actuals = productCategoryDao.getAll();
        for (int i = 0; i < 10; i++) {
            ProductCategory actual = actuals.get(i);

            ProductCategory expected = productCategoryDao.find(i + 1);
            assertEquals(expected, actual);
        }
    }
}