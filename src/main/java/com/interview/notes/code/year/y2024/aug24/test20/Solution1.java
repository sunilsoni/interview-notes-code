package com.interview.notes.code.year.y2024.aug24.test20;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution1 {

    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=";

    public static String[] getMovieTitles(String substr) {
        List<String> titles = new ArrayList<>();
        int currentPage = 1;
        int totalPages = 1;

        try {
            do {
                String urlString = BASE_URL + substr + "&page=" + currentPage;
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONObject jsonResponse = new JSONObject(response.toString());
                totalPages = jsonResponse.getInt("total_pages");
                JSONArray data = jsonResponse.getJSONArray("data");

                for (int i = 0; i < data.length(); i++) {
                    JSONObject movie = data.getJSONObject(i);
                    titles.add(movie.getString("Title"));
                }

                currentPage++;
            } while (currentPage <= totalPages);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(titles);
        return titles.toArray(new String[0]);
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {"spiderman", "superman", "batman"};

        for (String testCase : testCases) {
            System.out.println("Search for: " + testCase);
            String[] results = getMovieTitles(testCase);
            for (String title : results) {
                System.out.println(title);
            }
            System.out.println();
        }
    }
}
