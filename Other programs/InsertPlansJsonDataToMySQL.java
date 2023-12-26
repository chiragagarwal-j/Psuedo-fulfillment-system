package com.pfsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InsertPlansJsonDataToMySQL {
    public static void mains(String[] args) {
        String jsonFilePath = "/home/chiragagarwals/Pseudo Fulfillment System/Psuedo-fulfillment-system/PFSystem/mtnl_delhi-ncr.json";
        String jdbcUrl = "jdbc:mysql://localhost:3306/PFSystem";
        String username = "chiragagarwals";
        String password = "chiragagarwals";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "INSERT INTO recharge_plans (categories, details, operator, operator_circle, price, validity) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(jsonFilePath));

            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                preparedStatement.setString(1, getStringOrNull(jsonObj, "category"));
                preparedStatement.setString(2, getStringOrNull(jsonObj, "details"));
                preparedStatement.setString(3, getStringOrNull(jsonObj, "operator"));
                preparedStatement.setString(4, getStringOrNull(jsonObj, "operator_circle"));
                preparedStatement.setString(5, getStringOrNull(jsonObj, "price"));
                preparedStatement.setString(6, getStringOrNull(jsonObj, "validity"));
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
