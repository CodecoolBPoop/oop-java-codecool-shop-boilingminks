package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> data = new ArrayList<>();
    private static SupplierDaoMem instance = null;
    private static SupplierDaoMem testInstance = null;

    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    public static SupplierDaoMem getTestInstance() {
        if (testInstance == null) {
            testInstance = new SupplierDaoMem();
        }
        return testInstance;
    }

    @Override
    public void add(Supplier supplier) {
        supplier.setId(data.size() + 1);
        data.add(supplier);
    }

    @Override
    public Supplier find(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Integer must be more than 0!");
        } else {
            return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        }
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Supplier> getAll() {
        return data;
    }
}
