package com.interview.notes.code.year.y2024.march24.test6;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class Result4 {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static int healthCheckup(int lowerlimit, int upperlimit) {
        int fitCount = 0;
        int pageNumber = 1;
        boolean hasMorePages = true;

        while (hasMorePages) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonmock.hackerrank.com/api/medical_records?page=" + pageNumber))
                    .GET()
                    .build();

            CompletableFuture<HttpResponse<String>> responseFuture =
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

            try {
                HttpResponse<String> response = responseFuture.get(); // blocking call
                if (response.statusCode() == 200) {
                    String responseBody = response.body();
                    Map<?, ?> parsedBody = new Gson().fromJson(responseBody, Map.class);
                    List<Map<?, ?>> data = (List<Map<?, ?>>) parsedBody.get("data");

                    for (Map<?, ?> record : data) {
                        Map<?, ?> vitals = (Map<?, ?>) record.get("vitals");
                        double diastole = (double) vitals.get("bloodPressureDiastole");
                        if (diastole >= lowerlimit && diastole <= upperlimit) {
                            fitCount++;
                        }
                    }

                    int total_pages = ((Double) Double.parseDouble(parsedBody.get("total_pages").toString())).intValue();
                    hasMorePages = pageNumber < total_pages;
                    pageNumber++;
                } else {
                    throw new RuntimeException("HttpResponseCode: " + response.statusCode());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return fitCount;
    }

    public static void main(String[] args) {
        int lowerlimit = 120;  // should be replaced with actual input
        int upperlimit = 160;  // should be replaced with actual input
        int result = healthCheckup(lowerlimit, upperlimit);
        System.out.println(result);
    }
}
