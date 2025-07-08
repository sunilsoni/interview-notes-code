package com.interview.notes.code.year.y2025.july.common.test3;

import java.net.http.*;
import java.net.URI;
import java.util.regex.*;
import com.google.gson.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 1) Fetch the JSON payload
        var client = HttpClient.newHttpClient();
        var req = HttpRequest.newBuilder(URI.create(
            "https://coderbyte.com/api/challenges/json/age-counting"))
          .GET()
          .build();
        String json = client
          .send(req, HttpResponse.BodyHandlers.ofString())
          .body();

        // 2) Pull out the "data" field
        String data = JsonParser
          .parseString(json)
          .getAsJsonObject()
          .get("data")
          .getAsString();

        // 3) Find all age=(number) and count how many ≥ 50
        Pattern p = Pattern.compile("age=(\\d+)");
        int count = 0;
        var m = p.matcher(data);
        while (m.find()) {
            if (Integer.parseInt(m.group(1)) >= 50) {
                count++;
            }
        }

        // 4) Print the result
        System.out.println(count);
    }
}