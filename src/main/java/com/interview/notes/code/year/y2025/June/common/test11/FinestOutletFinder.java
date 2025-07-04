package com.interview.notes.code.year.y2025.June.common.test11;/*  ---------- allowed imports only ---------- */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*  ---------- solution ---------- */
public class FinestOutletFinder {

    public static String finestFoodOutlet(String city, int minVotes) {
        int page = 1;
        int totalPages = 1;          // will be overwritten after first call
        String bestName = "";
        double bestRating = -1.0;
        int bestVotes = -1;

        while (page <= totalPages) {
            JSONObject pageJson = fetchPage(city, page++);
            totalPages = ((Long) pageJson.get("total_pages")).intValue();

            JSONArray data = (JSONArray) pageJson.get("data");
            for (Object obj : data) {
                JSONObject outlet = (JSONObject) obj;
                JSONObject rating = (JSONObject) outlet.get("user_rating");
                int votes = ((Long) rating.get("votes")).intValue();
                if (votes < minVotes) continue;

                double avgRating = Double.parseDouble(rating.get("average_rating").toString());
                if (avgRating > bestRating ||
                        (avgRating == bestRating && votes > bestVotes)) {
                    bestRating = avgRating;
                    bestVotes = votes;
                    bestName = (String) outlet.get("name");
                }
            }
        }
        return bestName;           // empty string if nothing matched
    }


    /* small helper – GET one API page and parse with json-simple */
    private static JSONObject fetchPage(String city, int pageNo) {
        String urlStr = null;
        try {
            urlStr = String.format(
                    "https://jsonmock.hackerrank.com/api/food_outlets?city=%s&page=%d",
                    URLEncoder.encode(city, "UTF-8"), pageNo);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        try {
            HttpURLConnection con = (HttpURLConnection) new URL(urlStr).openConnection();
            con.setRequestMethod("GET");

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "UTF-8"))) {

                String response = br.lines().collect(Collectors.joining());
                return (JSONObject) new JSONParser().parse(response);
            }
        } catch (IOException | ParseException ex) {
            throw new RuntimeException("API call failed", ex);
        }
    }

    public static void main(String[] args) {
        List<Case> tests = Arrays.asList(
                new Case("Seattle", 500, "Cafe Juanita"),
                new Case("Miami", 1000, "Pirates of Grill"),
                new Case("Chicago", 99999, "")
        );

        int n = 1;
        for (Case t : tests) {
            String got = finestFoodOutlet(t.city, t.votes);
            System.out.printf("Test %02d : %s (expected \"%s\", got \"%s\")%n",
                    n++, got.equals(t.expect) ? "PASS" : "FAIL", t.expect, got);
        }
    }

    /* ------------ tiny PASS/FAIL harness (no JUnit) ------------ */
    private static final class Case {
        final String city;
        final int votes;
        final String expect;

        Case(String c, int v, String e) {
            city = c;
            votes = v;
            expect = e;
        }
    }
}