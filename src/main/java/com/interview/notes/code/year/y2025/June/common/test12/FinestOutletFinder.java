package com.interview.notes.code.year.y2025.June.common.test12;/* ─── allowed-only imports ────────────────────────────── */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/* ─── solution ────────────────────────────────────────── */
public class FinestOutletFinder {

    public static String finestFoodOutlet(String city, int minVotes) {

        /* wrappers for best choice so far */
        double bestRating = -1;
        int bestVotes = -1;
        String bestName = "";

        var client = HttpClient.newHttpClient();
        var parser = new JSONParser();

        /* ------------- exception-safe loop ------------- */
        try {
            for (int page = 1, pages = 1; page <= pages; page++) {

                /* build URL (need to encode city) */
                String url = String.format(
                        "https://jsonmock.hackerrank.com/api/food_outlets?city=%s&page=%d",
                        URLEncoder.encode(city, "UTF-8"), page);

                /* one-line GET request */
                String body = client.send(
                                HttpRequest.newBuilder(URI.create(url)).GET().build(),
                                HttpResponse.BodyHandlers.ofString())
                        .body();

                /* parse JSON page */
                JSONObject json = (JSONObject) parser.parse(body);
                pages = ((Long) json.get("total_pages")).intValue();

                /* scan outlets */
                for (Object o : (JSONArray) json.get("data")) {
                    JSONObject outlet = (JSONObject) o;
                    JSONObject rating = (JSONObject) outlet.get("user_rating");

                    int votes = ((Long) rating.get("votes")).intValue();
                    double avg = Double.parseDouble(rating.get("average_rating").toString());

                    if (votes >= minVotes &&
                            (avg > bestRating || (avg == bestRating && votes > bestVotes))) {
                        bestRating = avg;
                        bestVotes = votes;
                        bestName = (String) outlet.get("name");
                    }
                }
            }
        } catch (IOException | InterruptedException | ParseException e) {
            /* wrap in unchecked so method keeps simple signature */
            throw new RuntimeException("API/JSON failure", e);
        }

        return bestName;      // "" if nothing matched
    }

    /* very small sanity check */
    public static void main(String[] args) {
        System.out.println(finestFoodOutlet("Seattle", 500));   // → Cafe Juanita
        System.out.println(finestFoodOutlet("Miami", 1000));  // → Pirates of Grill
    }
}