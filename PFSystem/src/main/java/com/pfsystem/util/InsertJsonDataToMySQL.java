package com.pfsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InsertJsonDataToMySQL {
    public static void mains(String[] args) {
        String jsonFilePath = "/home/chiragagarwals/Pseudo Fulfillment System/Psuedo-fulfillment-system/PFSystem/src/main/resources/DB for MCC,MNC/mcc-mnc-list.json";
        String jdbcUrl = "jdbc:mysql://localhost:3306/PFSystem";
        String username = "chiragagarwals";
        String password = "chiragagarwals";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "INSERT INTO network_operator (type, country_name, country_code, mcc, mnc, brand, operator, status, bands, notes) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(jsonFilePath));

            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                preparedStatement.setString(1, getStringOrNull(jsonObj, "type"));
                preparedStatement.setString(2, getStringOrNull(jsonObj, "countryName"));
                preparedStatement.setString(3, getStringOrNull(jsonObj, "countryCode"));
                preparedStatement.setString(4, getStringOrNull(jsonObj, "mcc"));
                preparedStatement.setString(5, getStringOrNull(jsonObj, "mnc"));
                preparedStatement.setString(6, getStringOrNull(jsonObj, "brand"));
                preparedStatement.setString(7, getStringOrNull(jsonObj, "operator"));
                preparedStatement.setString(8, getStringOrNull(jsonObj, "status"));
                preparedStatement.setString(9, getStringOrNull(jsonObj, "bands"));
                preparedStatement.setString(10, getStringOrNull(jsonObj, "notes"));
                preparedStatement.executeUpdate();
            }

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getStringOrNull(JSONObject jsonObject, String key) {
        Object value = jsonObject.get(key);
        return (value != null) ? value.toString() : null;
    }
}
