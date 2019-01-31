package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoMemTest {

    private SupplierDao supplierDao = SupplierDaoMem.getInstance();

    @BeforeEach
    void setup(){
        for (int i = 1; i < 20; i++) {
            supplierDao.remove(i);
        }

        for (int i = 0; i < 10; i++) {
            Supplier testSupplier = new Supplier("testSupp", "test");
            supplierDao.add(testSupplier);
        }
    }

    @Test
    void testAdd() {
    }

    @Test
    void testFind() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void testGetAll() {
    }
}