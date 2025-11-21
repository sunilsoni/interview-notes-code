package com.interview.notes.code.year.y2024.jan24.test14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiscountedPriceFetcher3 {

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

            // Extract data using regex
            Pattern pattern = Pattern.compile("\"data\":\\[(.*?)\\]");

            Matcher matcher = pattern.matcher(content.toString());
            if (matcher.find()) {
                String data = matcher.group(1).trim();
                if (data.equals("")) {
                    return -1;
                }

                int price = extractValue(data, "price");
                int discount = extractValue(data, "discount");

                int discountedPrice = price - (discount * price / 100);
                return Math.round(discountedPrice);
            } else {
                return -1;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static int extractValue(String data, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\":(\\d+),?");
        Matcher matcher = pattern.matcher(data);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }

    public static void main(String[] args) {
        int barcode = 74002314; // Replace with actual barcode to test
        System.out.println(getDiscountedPrice(barcode));

        // Add more test cases if necessary
        int barcode1 = 74001777; // Replace with actual barcode to test
        System.out.println(getDiscountedPrice(barcode1));
        // https://jsonmock.hackerrank.com/api/inventory?barcode=74002314
        //content: {"page":1,"per_page":500,"total":1,"total_pages":1,"data":[{"barcode":"74002314","item":"Nightgown","category":"Underwear","price":3705,"discount":20,"available":1}]}


    }
}
