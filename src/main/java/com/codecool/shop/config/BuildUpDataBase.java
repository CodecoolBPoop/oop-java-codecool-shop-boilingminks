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


public class BuildUpDataBase {

    public static void buildUpDB() {
        JdbcConnectivity.getInstance().executeUpdateFromFile("sql_schema/schema.sql");

        ArrayList<Product> tempProductDS = new ArrayList<>();
        ArrayList<ProductCategory> tempProductCatDS = new ArrayList<>();
        ArrayList<Supplier> tempSupplierDS = new ArrayList<>();

        Supplier amazon = new Supplier(1,"Amazon", "Digital content and services");
        tempSupplierDS.add(amazon);
        Supplier lenovo = new Supplier(2,"Lenovo", "Computers");
        tempSupplierDS.add(lenovo);
        Supplier libri = new Supplier(3,"Libri", "Books");
        tempSupplierDS.add(libri);
        Supplier mondelez = new Supplier(4,"Mondelez", "Confectionery");
        tempSupplierDS.add(mondelez);


        ProductCategory tablet = new ProductCategory(1,"Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory book = new ProductCategory(2,"Book", "Paper", "Papermade information handling device. Pretty oldschool.");
        ProductCategory snack = new ProductCategory(3,"Snack", "Food", "Stuff the developer needs to stay alive.");
        tempProductCatDS.add(tablet);
        tempProductCatDS.add(book);
        tempProductCatDS.add(snack);


        tempProductDS.add(new Product(1,"Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon, "product_1.jpg"));
        tempProductDS.add(new Product(2,"Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo, "product_2.jpg"));
        tempProductDS.add(new Product(3,"Amazon Fire HD 8", 89, "USD", "Amazon''s latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon, "product_3.jpg"));
        tempProductDS.add(new Product(4,"A Thousand Splendid Suns", 12, "USD", "A Thousand Splendid Suns is a breathtaking story set against the volatile events of Afghanistan''s last thirty years—from the Soviet invasion to the reign of the Taliban to post-Taliban rebuilding—that puts the violence, fear, hope, and faith of this country in intimate, human terms.", book, libri, "product_4.jpg"));
        tempProductDS.add(new Product(5,"American Gods", 13, "USD", "Scary, gripping and deeply unsettling, American Gods takes a long, hard look into the soul of America.", book, libri, "product_5.jpg"));
        tempProductDS.add(new Product(6,"Little Fires Everywhere", 14, "USD", "Little Fires Everywhere explores the weight of long-held secrets and the ferocious pull of motherhood-and the danger of believing that planning and following the rules can avert disaster, or heartbreak.", book, libri, "product_6.jpg"));
        tempProductDS.add(new Product(7,"Cadbury", 3, "USD", "Cadbury Dairy Milk will launch new mint and peanut butter-flavoured Oreo bars this month, in a bid to recruit young adults to the tablets category.", snack, mondelez, "product_7.jpg"));
        tempProductDS.add(new Product(8,"Peanut Puffs", 2, "USD", "LOW Fat, LOW Sugar, HIGH Protein, HIGH Fiber - Made with all natural ingredients", snack, mondelez, "product_8.jpg"));
        tempProductDS.add(new Product(9,"Snickers Mini Cubes", 4, "USD", "Yumm yumm!!", snack, mondelez, "product_9.jpg"));
        tempProductDS.add(new Product(10,"8.5 Ultra LCD Writing Tablet Pen Writing Drawing Memo Message Boogie Board YT", 7, "USD", "Mild color, no harm to your eyes, no radiation." +
                "Pressure sensitive, easy to use and play." +
                "Clear screen with just one button." +
                "Ultra thin and lightweight design, convenient to carry." +
                "Suitable for writing and drawing, home message, etc.", tablet, mondelez, "product_10.jpg"));


        for (Product p : tempProductDS) {
            System.out.println(p.toString());
        }


        SupplierDaoJDBC.getInstance().loadAll(tempSupplierDS);

        ProductCategoryDaoJDBC productCategoryDaoJDBC = ProductCategoryDaoJDBC.getInstance();
        productCategoryDaoJDBC.clear();
        productCategoryDaoJDBC.addAll(tempProductCatDS);

        ProductDaoJDBC productDaoJDBC = ProductDaoJDBC.getInstance();
        productDaoJDBC.clear();
        productDaoJDBC.addAll(tempProductDS);
    }
}
