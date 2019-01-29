package com.codecool.shop.config;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JdbcConnectivity {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    private static final String DB_USER = System.getenv("username");
    private static final String DB_PASSWORD = System.getenv("PW");

    private static JdbcConnectivity SINGLE_INSTANCE = null;

    private JdbcConnectivity() {
    }

    public static JdbcConnectivity getInstance() {

        if (SINGLE_INSTANCE == null) {

            SINGLE_INSTANCE = new JdbcConnectivity();

        }

        return SINGLE_INSTANCE;

    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public void executeQuery(String query) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.execute(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<HashMap<String, String>> executeQuerySelect(String query) {

        List<HashMap<String, String>> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                HashMap<String, String> row = new HashMap<>();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnName(i);
                    row.put(columnName, resultSet.getString(columnName));
                }
                resultList.add(row);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }


}
