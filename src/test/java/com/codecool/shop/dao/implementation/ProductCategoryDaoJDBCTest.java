package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.JdbcConnectivity;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoJDBCTest {

    private static ProductCategoryDaoJDBC testInstance = ProductCategoryDaoJDBC.getInstance();
    private static JdbcConnectivity connInst = JdbcConnectivity.getInstance();

    @BeforeEach
    void setUp() {
        JdbcConnectivity.getInstance().executeUpdateFromFile("sql_schema/schema.sql");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testIsCanAddSingleItem() {
        ProductCategory duckFood = new ProductCategory(1, "Duck Food", "Animal Food", "the most nutritious duck food on the market");
        testInstance.add(duckFood);
        ArrayList<HashMap<String, String>> dbData = connInst.queryAllFromTable("product_category");
        assertEquals(1, dbData.size());
        assertEquals(dbData.get(0).get("name"), duckFood.getName());
        assertEquals(dbData.get(0).get("department"), duckFood.getDepartment());
        assertEquals(dbData.get(0).get("description"), duckFood.getDescription());
    }

    @Test
    void testIsCanClearTable() {
        ProductCategory duckFood = new ProductCategory(1, "Duck Food", "Animal Food", "the most nutritious duck food on the market");
        ProductCategory catFood = new ProductCategory(2, "Cat Food", "Animal Food", "the most nutritious cat food on the market");
        testInstance.clear();
        ArrayList<HashMap<String, String>> dbData = connInst.queryAllFromTable("product_category");
        assertEquals(0, dbData.size());
    }

    @Test
    void testIsCanAddEntireList() {
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        productCategories.add(new ProductCategory(1, "Duck Food", "Animal Food", "the most nutritious duck food on the market"));
        productCategories.add(new ProductCategory(2, "Cat Food", "Animal Food", "the most nutritious cat food on the market"));
        productCategories.add(new ProductCategory(2, "Horse Food", "Animal Food", "the most nutritious horse food on the market"));
        productCategories.add(new ProductCategory(2, "Kangaroo Food", "Animal Food", "the most nutritious kangaroo food on the market"));
        testInstance.addAll(productCategories);
        ArrayList<HashMap<String, String>> dbData = connInst.queryAllFromTable("product_category");
        assertEquals(4, dbData.size());
        dbData.forEach((dbProductCat) -> {
            productCategories.forEach((definedProductCat) -> {
                assertEquals(dbProductCat.get("name"), definedProductCat.getName());
                assertEquals(dbData.get(0).get("department"), new ProductCategory(1, "Duck Food", "Animal Food", "the most nutritious duck food on the market").getDepartment());
                assertEquals(dbData.get(0).get("description"), new ProductCategory(1, "Duck Food", "Animal Food", "the most nutritious duck food on the market").getDescription());
            });
        });

    }
}