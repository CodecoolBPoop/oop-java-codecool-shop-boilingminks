package com.codecool.shop.dao.implementation;

public class UtilsSQL {
    private static UtilsSQL instance;

    private UtilsSQL() {
    }

    public static UtilsSQL getInstance() {
        if (instance == null) {
            instance = new UtilsSQL();
        }
        return instance;
    }

}
