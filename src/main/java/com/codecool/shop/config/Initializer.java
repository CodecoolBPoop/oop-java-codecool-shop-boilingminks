package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        supplierDataStore.add(lenovo);
        Supplier libri = new Supplier("Libri", "Books");
        supplierDataStore.add(libri);
        Supplier mondelez = new Supplier("Mondelez", "Confectionery");
        supplierDataStore.add(mondelez);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory book = new ProductCategory("Book", "Paper", "Papermade information handling device. Pretty oldschool.");
        ProductCategory snack = new ProductCategory("Snack", "Food", "Stuff the developer needs to stay alive.");
        productCategoryDataStore.add(tablet);
        productCategoryDataStore.add(book);
        productCategoryDataStore.add(snack);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("A Thousand Splendid Suns", 12, "USD", "A Thousand Splendid Suns is a breathtaking story set against the volatile events of Afghanistan's last thirty years—from the Soviet invasion to the reign of the Taliban to post-Taliban rebuilding—that puts the violence, fear, hope, and faith of this country in intimate, human terms.", book, libri));
        productDataStore.add(new Product("American Gods", 13, "USD", "Scary, gripping and deeply unsettling, American Gods takes a long, hard look into the soul of America.", book, libri));
        productDataStore.add(new Product("Little Fires Everywhere", 14, "USD", "Little Fires Everywhere explores the weight of long-held secrets and the ferocious pull of motherhood-and the danger of believing that planning and following the rules can avert disaster, or heartbreak.", book, libri));
        productDataStore.add(new Product("Cadbury", 3, "USD", "Cadbury Dairy Milk will launch new mint and peanut butter-flavoured Oreo bars this month, in a bid to recruit young adults to the tablets category.", snack, mondelez));
        productDataStore.add(new Product("Peanut Puffs", 2, "USD", "LOW Fat, LOW Sugar, HIGH Protein, HIGH Fiber - Made with all natural ingredients", snack, mondelez));
        productDataStore.add(new Product("Snickers Mini Cubes", 4, "USD", "Yumm yumm!!", snack, mondelez));
    }
}
