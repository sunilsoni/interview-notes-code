package com.interview.notes.code.year.y2024.march24.test6;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//WOKRING
class HealthCheckupMedicalRecord {
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static int healthCheckup(int lowerlimit, int upperlimit) {
        int fitCount = 0;
        int pageNumber = 1;
        int totalPages = Integer.MAX_VALUE; // Initialize with max value to start the loop

        while (pageNumber <= totalPages) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonmock.hackerrank.com/api/medical_records?page=" + pageNumber))
                    .build();

            CompletableFuture<HttpResponse<InputStream>> responseFuture =
                    httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofInputStream());

            try {
                HttpResponse<InputStream> response = responseFuture.get(); // blocking call
                if (response.statusCode() == 200) {
                    JSONObject obj = new JSONObject(new String(response.body().readAllBytes()));
                    totalPages = obj.getInt("total_pages");

                    var dataArray = obj.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        var record = dataArray.getJSONObject(i);
                        var vitals = record.getJSONObject("vitals");
                        int diastole = vitals.getInt("bloodPressureDiastole");
                        if (diastole >= lowerlimit && diastole <= upperlimit) {
                            fitCount++;
                        }
                    }
                } else {
                    throw new RuntimeException("HttpResponseCode: " + response.statusCode());
                }
                pageNumber++;
            } catch (InterruptedException | ExecutionException | IOException e) {
                e.printStackTrace();
            }
        }
        return fitCount;
    }

    public static void main(String[] args) {
        int lowerlimit = 120;  // This should be replaced with the actual input
        int upperlimit = 160;  // This should be replaced with the actual input
        int result = healthCheckup(lowerlimit, upperlimit);
        System.out.println(result);
    }
}
