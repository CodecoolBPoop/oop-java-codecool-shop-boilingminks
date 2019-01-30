package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoMemTest{

    private ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();

    @BeforeEach
    private void setupDao(){

        for (int i = 1; i < 20; i++) {
            productCategoryDao.remove(i);
        }

        for (int i = 0; i < 10; i++) {
            productCategoryDao.add(new ProductCategory("test" + i, "dep", "desc"));
        }
    }

    @Test
    void add() {
        for (int i = 0; i < 10; i++) {
            ProductCategory category = productCategoryDao.getAll().get(i);
            assertEquals(category.getName(), "test" + i);
        }
    }

    @Test
    void find() {
        for (int i = 0; i < 20; i++) {
            if (i == 0){
                assertThrows(IllegalArgumentException.class, () -> {
                    productCategoryDao.find(0);
                });
            }
            else if (i <= 10){
                assertNotNull(productCategoryDao.find(i));
            }
            else {
                assertNull(productCategoryDao.find(i));
            }
        }
    }

    @Test
    void remove() {
    }

    @Test
    void getAll() {
    }
}