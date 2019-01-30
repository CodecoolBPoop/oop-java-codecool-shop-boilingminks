package com.codecool.shop.dao.implementation;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.*;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoJDBCTest {

    private static SupplierDaoJDBC testInstance;

    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = SupplierDaoJDBC.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
        testInstance = new SupplierDaoJDBC();
    }

    @BeforeAll
    public static void setUp() {

    }

    @AfterAll
    public static void tearDown() {

    }

    @Test
    public void testIsWorking() {

    }
}