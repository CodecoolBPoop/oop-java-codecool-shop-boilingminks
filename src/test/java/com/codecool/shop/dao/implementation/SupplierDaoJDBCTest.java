package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.JdbcConnectivity;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SupplierDaoJDBCTest {

    private static SupplierDaoJDBC testInstance = SupplierDaoJDBC.getInstance();
    private static JdbcConnectivity connInst = JdbcConnectivity.getInstance();

    @BeforeAll
    static void setUp() {
        JdbcConnectivity.getInstance().executeUpdateFromFile("sql_schema/schema.sql");
    }

    @AfterAll
    static void tearDown() {

    }

    @Test
    void testIsLoadAllMethodIsWorkingCorrectly() {
        ArrayList<Supplier> sl = new ArrayList<>();
        Supplier supplierDuck = new Supplier(1,"Duck Supplier", "best duck supplier");
        Supplier supplierHorse = new Supplier(2, "Horse Supplier", "best horse supplier");
        sl.add(supplierDuck);
        sl.add(supplierHorse);
        testInstance.loadAll(sl);
        ArrayList<HashMap<String,String>> d = connInst.queryAllFromTable("supplier");
        d.forEach((e) -> sl.forEach((s) -> {
            if (s.getId() == Integer.parseInt(e.get("id"))) {
                assertEquals(s.getName(), e.get("name"));
                assertEquals(s.getDescription(), e.get("description"));
            }
        }));
    }
}