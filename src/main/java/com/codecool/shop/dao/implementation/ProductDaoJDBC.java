package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.JdbcConnectivity;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class ProductDaoJDBC implements ProductDao {

    private JdbcConnectivity connection = JdbcConnectivity.getInstance();

    @Override
    public void add(Product product) {
//        connection.executeQuery("INSERT INTO product (id, name, description, price, currency, supplier_id, category_id, image_name)" +
//                                "VALUES ('" + product.getId() + "', '" + product.getName() + "', '" + product.getDescription() + "', '" + product.getPrice() + "', '" + product.getDefaultCurrency() + "', '" + product.getSupplier().getId() + "', '" + product.getProductCategory().getId() + "', '" + product.getImageid() + "');");
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
