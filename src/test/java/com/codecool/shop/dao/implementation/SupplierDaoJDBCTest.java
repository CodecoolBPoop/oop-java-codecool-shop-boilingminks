package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.JdbcConnectivity;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

class SupplierDaoJDBCTest {

    private static SupplierDaoJDBC testInstance = SupplierDaoJDBC.getInstance();
    private static JdbcConnectivity connInst = JdbcConnectivity.getInstance();

    @BeforeAll
    public static void setUp() {
        JdbcConnectivity.getInstance().executeUpdateFromFile("sql_schema/schema.sql");
    }

    @AfterAll
    public static void tearDown() throws SQLException {

    }

    @Test
    public void testIsWorking() {
        ArrayList<Supplier> sl = new ArrayList<>();
        Supplier sASDF = new Supplier(1,"ASDF", "best supplier ASDF");
        Supplier sQWER = new Supplier(2, "QWER", "best supplier QWER");
        sl.add(sASDF);
        sl.add(sQWER);
        testInstance.loadAll(sl);
        ArrayList<HashMap<String,String>> d = connInst.queryAllFromTable("supplier");
        d.forEach((e) -> {
            sl.forEach((s) -> {
                if (s.getId() == Integer.parseInt(e.get("id"))) {
                    assertEquals(s.getName(), e.get("name"));
                    assertEquals(s.getDescription(), e.get("description"));
                }
            });
        });
    }
}