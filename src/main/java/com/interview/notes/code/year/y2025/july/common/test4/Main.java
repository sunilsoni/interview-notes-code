package com.interview.notes.code.year.y2025.july.common.test4;

import java.util.*;
import java.io.*;
import java.net.*;
import java.util.regex.*;
import com.google.gson.*;

class Main {
    public static void main (String[] args) {
        System.setProperty("http.agent", "Chrome");
        try {
            URI uri = new URI("https://coderbyte.com/api/challenges/json/age-counting");
            URL url = uri.toURL();

            URLConnection connection = url.openConnection();

            // ——— READ RESPONSE INTO A STRING ———
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                     new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }

            // ——— PARSE JSON & EXTRACT data FIELD ———
            JsonObject root = JsonParser
                .parseString(sb.toString())
                .getAsJsonObject();
            String data = root.get("data").getAsString();

            // ——— REGEX-DRIVEN AGE COUNTING ———
            Pattern p = Pattern.compile("age=(\\d+)");
            Matcher m = p.matcher(data);
            int count = 0;
            while (m.find()) {
                if (Integer.parseInt(m.group(1)) >= 50) {
                    count++;
                }
            }

            // ——— PRINT THE FINAL COUNT ———
            System.out.println(count);

        } catch (MalformedURLException | URISyntaxException e) {
            System.err.println("URL error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }
    }
}