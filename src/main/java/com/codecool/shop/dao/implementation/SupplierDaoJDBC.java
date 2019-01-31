package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.JdbcConnectivity;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao {

    private JdbcConnectivity jdbcInstance = JdbcConnectivity.getInstance();

    private static SupplierDaoJDBC instance = null;

    private SupplierDaoJDBC() {
    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }

    public void loadAll(ArrayList<Supplier> supplierDataStore) {
        StringBuilder query = new StringBuilder("TRUNCATE TABLE supplier CASCADE ;");
        query.append("INSERT INTO supplier (name, description) VALUES");
        boolean notFirst = false;
        for (Supplier sup : supplierDataStore) {
            if (notFirst) query.append(",");
            notFirst = true;
            query.append("(\'").append(sup.getName()).append("\',\'").append(sup.getDescription()).append("\')");
        }
        query.append(";");
        jdbcInstance.executeQuery(query.toString());
    }
}
