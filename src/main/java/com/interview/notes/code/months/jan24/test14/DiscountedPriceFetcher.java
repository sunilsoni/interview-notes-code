package com.interview.notes.code.months.jan24.test14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;

//WORKING
public class DiscountedPriceFetcher {

    public static int getDiscountedPrice(int barcode) {
        String apiEndpoint = "https://jsonmock.hackerrank.com/api/inventory?barcode=" + barcode;

        try {
            URL url = new URL(apiEndpoint);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            int status = con.getResponseCode();
            if (status != 200) {
                return -1;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            String dataSection = content.toString().split("\"data\":\\[")[1].split("\\]")[0].trim();
            if (dataSection.equals("")) {
                return -1; // No data found
            }

            String priceStr = dataSection.split("\"price\":")[1].split(",")[0].trim();
            String discountStr = dataSection.split("\"discount\":")[1].split(",")[0].trim();

            BigDecimal price = new BigDecimal(priceStr);
            BigDecimal discount = new BigDecimal(discountStr);
            BigDecimal hundred = new BigDecimal("100");

            BigDecimal discountedPrice = price.subtract(price.multiply(discount).divide(hundred));
            return discountedPrice.setScale(0, RoundingMode.HALF_UP).intValueExact(); // Correct rounding
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String[] args) {
        int barcode = 74001777; // Replace with actual barcode to test
        System.out.println(getDiscountedPrice(barcode));

        // Add more test cases if necessary
    }
}
