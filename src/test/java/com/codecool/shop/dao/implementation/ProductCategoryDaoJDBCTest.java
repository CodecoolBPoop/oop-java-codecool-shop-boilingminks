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
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        productCategories.add(new ProductCategory(1, "Duck Food", "Animal Food", "the most nutritious duck food on the market"));
        productCategories.add(new ProductCategory(2, "Cat Food", "Animal Food", "the most nutritious cat food on the market"));
        testInstance.addAll(productCategories);
        testInstance.clear();
        ArrayList<HashMap<String, String>> dbData = connInst.queryAllFromTable("product_category");
        assertEquals(0, dbData.size());
    }

    @Test
    void testIsCanAddEntireList() {
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        productCategories.add(new ProductCategory(1, "Duck Food", "Animal Food", "the most nutritious duck food on the market"));
        productCategories.add(new ProductCategory(2, "Cat Food", "Animal Food", "the most nutritious cat food on the market"));
        productCategories.add(new ProductCategory(3, "Horse Food", "Animal Food", "the most nutritious horse food on the market"));
        productCategories.add(new ProductCategory(4, "Kangaroo Food", "Animal Food", "the most nutritious kangaroo food on the market"));
        testInstance.addAll(productCategories);
        ArrayList<HashMap<String, String>> dbData = connInst.queryAllFromTable("product_category");
        assertEquals(4, dbData.size());
        final int[] definedProductCounter = {0};
        dbData.forEach((dbProductCat) -> {
            assertEquals(dbProductCat.get("name"), productCategories.get(definedProductCounter[0]).getName());
            assertEquals(dbProductCat.get("department"), productCategories.get(definedProductCounter[0]).getDepartment());
            assertEquals(dbProductCat.get("description"), productCategories.get(definedProductCounter[0]++).getDescription());
        });
    }
}