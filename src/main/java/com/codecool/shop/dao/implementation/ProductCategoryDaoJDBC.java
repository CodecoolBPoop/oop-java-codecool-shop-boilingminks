package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.JdbcConnectivity;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {
    private static ProductCategoryDaoJDBC instance = null;

    private JdbcConnectivity JDBCInstance = JdbcConnectivity.getInstance();

    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        JDBCInstance.executeQuery("INSERT INTO product_category (id, name, description, department)" +
                "VALUES ('" + category.getId() + "', '" + category.getName() + "', '" + category.getDescription() + "', '" + category.getDepartment() + "');");
    }

    @Override
    public ProductCategory find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }

    public void clear(){
        String query = "TRUNCATE TABLE product CASCADE;";
        JDBCInstance.executeQuery(query);
    }

    public void addAll(ProductCategoryDao categoryDao){

        for (ProductCategory category:categoryDao.getAll()) {
            this.add(category);
        }
    }
}
