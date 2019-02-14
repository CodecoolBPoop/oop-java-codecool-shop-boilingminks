package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoMemTest {

    private SupplierDao supplierDao = SupplierDaoMem.getTestInstance();

    @BeforeEach
    void setup() {
        for (int i = 1; i < 20; i++) {
            supplierDao.remove(i);
        }

        for (int i = 0; i < 10; i++) {
            Supplier testSupplier = new Supplier("testSupp" + i, "test");
            supplierDao.add(testSupplier);
        }
    }

    @Test
    void testAdd() {
        for (int i = 0; i < 10; i++) {
            Supplier supplier = supplierDao.getAll().get(i);
            assertEquals("testSupp" + i, supplier.getName());
        }
    }

    @Test
    void testFind() {
        for (int i = 0; i < 20; i++) {
            if (i == 0) {
                assertThrows(IllegalArgumentException.class, () -> {
                    supplierDao.find(0);
                });
            } else if (i <= 10) {
                assertNotNull(supplierDao.find(i));
            } else {
                assertNull(supplierDao.find(i));
            }
        }

    }

    @Test
    void testRemove() {
        for (int i = 1; i <= 10; i++) {
            supplierDao.remove(i);
            assertNull(supplierDao.find(i));
        }
    }

    @Test
    void testGetAll() {
        List<Supplier> actuals = supplierDao.getAll();
        for (int i = 0; i < 10; i++) {
            Supplier actual = actuals.get(i);

            Supplier expected = supplierDao.find(i + 1);
            assertEquals(expected, actual);
        }
    }
}