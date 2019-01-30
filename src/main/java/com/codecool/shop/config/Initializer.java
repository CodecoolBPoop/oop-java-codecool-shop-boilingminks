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

//        BuildUpDataBase.buildUpDB();

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();


        ArrayList<HashMap<String, String>> suppliers = JdbcConnectivity.getInstance().queryAllFromTable("supplier");
        for(HashMap<String, String> entry : suppliers) {
            Supplier temp = new Supplier(entry.get("name"), entry.get("description"));
            supplierDataStore.add(temp);
        }

        ArrayList<HashMap<String, String>> categories = JdbcConnectivity.getInstance().queryAllFromTable("product_category");
        for(HashMap<String, String> entry : categories) {
            ProductCategory temp = new ProductCategory(entry.get("name"),entry.get("department"), entry.get("description"));
            productCategoryDataStore.add(temp);
        }

        ArrayList<HashMap<String, String>> products = JdbcConnectivity.getInstance().queryAllFromTable("product");
        for(HashMap<String, String> entry : products) {
            Product temp = new Product(entry.get("name"),Float.parseFloat(entry.get("price")),entry.get("currency"), entry.get("description"),productCategoryDataStore.find(Integer.parseInt(entry.get("category_id"))), supplierDataStore.find(Integer.parseInt(entry.get("supplier_id"))), entry.get("image_name"));
            productDataStore.add(temp);
        }

//        //setting up products and printing it
//
//        ProductDaoJDBC productDaoJDBC = ProductDaoJDBC.getInstance();
//        productDaoJDBC.clear();
//
//        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon, "product_1.jpg"));
//        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo, "product_2.jpg"));
//        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon''s latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon, "product_3.jpg"));
//        productDataStore.add(new Product("A Thousand Splendid Suns", 12, "USD", "A Thousand Splendid Suns is a breathtaking story set against the volatile events of Afghanistan''s last thirty years—from the Soviet invasion to the reign of the Taliban to post-Taliban rebuilding—that puts the violence, fear, hope, and faith of this country in intimate, human terms.", book, libri, "product_4.jpg"));
//        productDataStore.add(new Product("American Gods", 13, "USD", "Scary, gripping and deeply unsettling, American Gods takes a long, hard look into the soul of America.", book, libri, "product_5.jpg"));
//        productDataStore.add(new Product("Little Fires Everywhere", 14, "USD", "Little Fires Everywhere explores the weight of long-held secrets and the ferocious pull of motherhood-and the danger of believing that planning and following the rules can avert disaster, or heartbreak.", book, libri, "product_6.jpg"));
//        productDataStore.add(new Product("Cadbury", 3, "USD", "Cadbury Dairy Milk will launch new mint and peanut butter-flavoured Oreo bars this month, in a bid to recruit young adults to the tablets category.", snack, mondelez, "product_7.jpg"));
//        productDataStore.add(new Product("Peanut Puffs", 2, "USD", "LOW Fat, LOW Sugar, HIGH Protein, HIGH Fiber - Made with all natural ingredients", snack, mondelez, "product_8.jpg"));
//        productDataStore.add(new Product("Snickers Mini Cubes", 4, "USD", "Yumm yumm!!", snack, mondelez, "product_9.jpg"));
//        productDataStore.add(new Product("8.5 Ultra LCD Writing Tablet Pen Writing Drawing Memo Message Boogie Board YT", 7, "USD", "Mild color, no harm to your eyes, no radiation." +
//                "Pressure sensitive, easy to use and play." +
//                "Clear screen with just one button." +
//                "Ultra thin and lightweight design, convenient to carry." +
//                "Suitable for writing and drawing, home message, etc.", tablet, mondelez, "product_10.jpg"));
//
//        productDaoJDBC.addAll(productDataStore);
    }
}
