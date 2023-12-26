package com.pfsystem.service;

import java.io.FileWriter;
import java.io.IOException;
import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfsystem.dto.RechargePlanDTO;

@Service
public class RechargeSimService {

    public RechargePlanDTO getOperatorLookup() throws IOException {

        String url = "https://wpr.infomattic.com/api-fetch-plans.php";

        // Define the data
        String accountID = "aid_2309050439321"; // Account ID
        String secretKey = "sk_3b41f9e6eded20fcff12d9c2e97f2ef3c9e0102667375"; // Secret Key
        String operator = "vodafoneidea"; // Operator Code
        String operatorCircle = "west-bengal"; // Circle Code

        // Create a JSON request body
        String json = "{\"plan_fetch\": {" +
                "\"accountID\": \"" + accountID + "\"," +
                "\"secret_key\": \"" + secretKey + "\"," +
                "\"operator\": \"" + operator + "\"," +
                "\"operator_circle\": \"" + operatorCircle + "\"" +
                "}}";

        // Set the content type for the request body
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // Create an OkHttpClient instance
        OkHttpClient client = new OkHttpClient();

        // Create a request with the JSON payload
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(json, JSON))
                .build();

        try {
            // Send the request and get the response
            Response response = client.newCall(request).execute();

            // Check the response
            if (response.isSuccessful()) {
                String responseBody = response.body().string();

                // Deserialize the JSON response into RechargePlanDTO
                ObjectMapper objectMapper = new ObjectMapper();
                RechargePlanDTO rechargePlanDTO = objectMapper.readValue(responseBody, RechargePlanDTO.class);

                // String filePath = operator + "_" + operatorCircle + ".json"; // Specify the
                // desired file path
                // saveJSONToFile(responseBody, filePath);

                return rechargePlanDTO;
            } else {
                System.out.println("Request failed: " + response.code() + " " + response.message());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null in case of an error
    }

    public static void saveJSONToFile(String json, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(json);
            System.out.println("JSON response has been saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}