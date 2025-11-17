package com.interview.notes.code.year.y2025.november.ibm.test3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

class Result {
    
    public static List<String> maximumTransfer(String name, String city) {
        try {
            var client = HttpClient.newHttpClient();
            var parser = new JSONParser();
            var fmt = new DecimalFormat("$#,##0.00");
            
            var firstReq = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonmock.hackerrank.com/api/transactions?page=1"))
                .build();
            var firstResp = client.send(firstReq, HttpResponse.BodyHandlers.ofString());
            var firstJson = (JSONObject) parser.parse(firstResp.body());
            var totalPages = ((Long) firstJson.get("total_pages")).intValue();
            
            List<JSONObject> transactions = new ArrayList<>();
            
            for (int page = 1; page <= totalPages; page++) {
                var req = HttpRequest.newBuilder()
                    .uri(URI.create("https://jsonmock.hackerrank.com/api/transactions?page=" + page))
                    .build();
                var resp = client.send(req, HttpResponse.BodyHandlers.ofString());
                var json = (JSONObject) parser.parse(resp.body());
                var data = (JSONArray) json.get("data");
                
                for (Object obj : data) {
                    JSONObject txn = (JSONObject) obj;
                    String userName = (String) txn.get("userName");
                    JSONObject location = (JSONObject) txn.get("location");
                    
                    if (name.equals(userName) && location != null) {
                        String locCity = (String) location.get("city");
                        if (city.equals(locCity)) {
                            transactions.add(txn);
                        }
                    }
                }
            }
            
            double maxCredit = 0.0;
            double maxDebit = 0.0;
            
            for (JSONObject t : transactions) {
                String type = (String) t.get("txnType");
                String amount = (String) t.get("amount");
                double value = parseAmount(amount);
                
                if ("credit".equals(type)) {
                    maxCredit = Math.max(maxCredit, value);
                } else if ("debit".equals(type)) {
                    maxDebit = Math.max(maxDebit, value);
                }
            }
            
            return List.of(fmt.format(maxCredit), fmt.format(maxDebit));
            
        } catch (Exception e) {
            return List.of("$0.00", "$0.00");
        }
    }
    
    private static double parseAmount(String amt) {
        return amt == null ? 0.0 : Double.parseDouble(amt.replaceAll("[^0-9.]", ""));
    }
    
    public static void main(String[] args) {
        record TestCase(String name, String city, List<String> expected) {}
        
        var tests = List.of(
            new TestCase("Bob Martin", "Bourg", List.of("$3,717.84", "$3,568.55")),
            new TestCase("Helena Fernandez", "Ilchester", List.of("$3,288.97", "$3,807.28")),
            new TestCase("NonExistent", "Unknown", List.of("$0.00", "$0.00")),
            new TestCase("", "", List.of("$0.00", "$0.00"))
        );
        
        var results = tests.stream()
            .map(test -> {
                var result = maximumTransfer(test.name, test.city);
                var passed = result.equals(test.expected);
                System.out.printf("Test: %s in %s%n", test.name, test.city);
                System.out.printf("Expected: %s, Got: %s%n", test.expected, result);
                System.out.printf("Status: %s%n%n", passed ? "PASS" : "FAIL");
                return passed;
            })
            .toList();
        
        var passed = results.stream().filter(r -> r).count();
        System.out.printf("Summary: %d/%d tests passed%n", passed, tests.size());
    }
}