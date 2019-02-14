package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // Fill up the DB with basic products.
//        BuildUpDataBase.buildUpDB();


        //Download the data from DB and put it to each data stores
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();


        ArrayList<HashMap<String, String>> suppliers = JdbcConnectivity.getInstance().queryAllFromTable("supplier");
        for (HashMap<String, String> entry : suppliers) {
            Supplier temp = new Supplier(entry.get("name"), entry.get("description"));
            supplierDataStore.add(temp);
        }

        ArrayList<HashMap<String, String>> categories = JdbcConnectivity.getInstance().queryAllFromTable("product_category");
        for (HashMap<String, String> entry : categories) {
            ProductCategory temp = new ProductCategory(entry.get("name"), entry.get("department"), entry.get("description"));
            productCategoryDataStore.add(temp);
        }

    }
}
