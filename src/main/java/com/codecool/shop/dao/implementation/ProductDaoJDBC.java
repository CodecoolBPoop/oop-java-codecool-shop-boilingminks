package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.JdbcConnectivity;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {
    private static ProductDaoJDBC instance = null;

    private JdbcConnectivity JDBCInstance = JdbcConnectivity.getInstance();

    public static ProductDaoJDBC getInstance() {
        if (instance == null) {
            instance = new ProductDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Product product) {
        JDBCInstance.executeQuery("INSERT INTO product (name, description, price, currency, supplier_id, category_id, image_name)" +
                                "VALUES ('" + product.getName() + "', '" + product.getDescription() + "', '" + product.getDefaultPrice() + "', '" + product.getDefaultCurrency() + "', '" + product.getSupplier().getId() + "', '" + product.getProductCategory().getId() + "', '" + product.getImageName() + "');");
    }

    @Override
    public Product find(int id) {
        List<Product> productList = new ArrayList<>();
        List<HashMap<String, String>> allProducts = JDBCInstance.executeQuerySelect("SELECT * FROM product WHERE id= '"+ Integer.toString(id) +"' ;");
        productListFromHasMap(productList, allProducts);
        return productList.get(0);
    }

    @Override
    public void remove(int id) {
        JDBCInstance.executeQuery("REMOVE * FROM product WHERE id='"+ Integer.toString(id) +"' ;");
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        ArrayList<HashMap<String, String>> allProducts = JDBCInstance.queryAllFromTable("product");
        productListFromHasMap(products, allProducts);
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        int suplierId = supplier.getId();
        List<Product> resultList = new ArrayList<>();
        List<HashMap<String, String>> filteredProducts;
        filteredProducts = JDBCInstance.executeQuerySelect(
                " SELECT * FROM product WHERE supplier_id ='" +  Integer.toString(suplierId) +"';"
        );
        productListFromHasMap(resultList, filteredProducts);
        return resultList;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        int categoryId = productCategory.getId();
        List<Product> resultList = new ArrayList<>();
        List<HashMap<String, String>> filteredProducts;
        filteredProducts = JDBCInstance.executeQuerySelect(
                " SELECT * FROM product WHERE category_id ='" + Integer.toString(categoryId) + "';"
        );
        productListFromHasMap(resultList, filteredProducts);
        return resultList;
    }

    private void productListFromHasMap(List<Product> resultList, List<HashMap<String, String>> filteredProducts) {
        for (HashMap<String, String> product : filteredProducts) {
            Product tempProduct = new Product(Integer.parseInt(product.get("id")), product.get("name"),
                    Float.parseFloat(product.get("price")), "USD",
                    product.get("description"),
                    ProductCategoryDaoMem.getInstance().find(Integer.parseInt(product.get("category_id"))),
                    SupplierDaoMem.getInstance().find(Integer.parseInt(product.get("supplier_id"))),
                    product.get("image_name"));
            resultList.add(tempProduct);
        }
    }
    public void clear(){
        String query = "TRUNCATE TABLE product CASCADE ;";
        JDBCInstance.executeQuery(query);
    }

    public void addAll(ArrayList<Product> productDao){

        for (Product product:productDao) {
            this.add(product);
        }
    }
}
