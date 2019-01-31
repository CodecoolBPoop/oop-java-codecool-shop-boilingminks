package com.codecool.shop.config;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JdbcConnectivity {

    private static final String DATABASE = System.getenv("url");
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

    public ArrayList<HashMap<String, String>> queryAllFromTable(String tablename) {
        String query = "SELECT * FROM " + tablename + ";";
        ArrayList<HashMap<String, String>> resultList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
        ) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                HashMap<String, String> row = new HashMap<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
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

    public void executeUpdate(String query) throws SQLException {
        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLTimeoutException e) {
            System.err.println("ERROR: SQL Timeout");
        }
    }

    public void executeUpdateFromFile(String filePath) {
        String query = "";
        try {
            query = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            executeUpdate(query);
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
