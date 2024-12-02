package com.interview.notes.code.year.y2024.aug24.test19;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class CinematicTitleFetcher {
    private static final String API_BASE_URL = "https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=";
    private static final HttpClient webClient = HttpClient.newHttpClient();
    private static final JSONParser jsonDataParser = new JSONParser();

    static String[] retrieveCinematicTitles(String searchTerm) {
        List<CinematicWork> cinematicWorks = new ArrayList<>();
        int currentPageIndex = 1;
        long totalPageCount;

        do {
            String fullUrl = API_BASE_URL + searchTerm + "&page=" + currentPageIndex;
            try {
                HttpRequest webRequest = HttpRequest.newBuilder().uri(URI.create(fullUrl)).build();
                HttpResponse<String> webResponse = webClient.send(webRequest, HttpResponse.BodyHandlers.ofString());

                JSONObject parsedResponse = (JSONObject) jsonDataParser.parse(webResponse.body());
                totalPageCount = (Long) parsedResponse.get("total_pages");

                JSONArray cinematicData = (JSONArray) parsedResponse.get("data");
                for (Object workObj : cinematicData) {
                    JSONObject work = (JSONObject) workObj;
                    String title = (String) work.get("Title");
                    Long year = (Long) work.get("Year");
                    String imdbID = (String) work.get("imdbID");
                    if (title != null) {
                        cinematicWorks.add(new CinematicWork(title, year, imdbID));
                    }
                }

                currentPageIndex++;
            } catch (IOException | InterruptedException | ParseException e) {
                System.err.println("Error occurred while fetching cinematic data: " + e.getMessage());
                e.printStackTrace();
                break;
            }
        } while (currentPageIndex <= totalPageCount);

        return cinematicWorks.stream()
                .map(work -> work.title)
                .filter(Objects::nonNull)
                .sorted(Comparator.nullsLast(String::compareTo))
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        String[] searchQueries = {"spiderman", "superman", "batman"};

        for (String query : searchQueries) {
            System.out.println("Searching for cinematic works containing: " + query);
            String[] queryResults = retrieveCinematicTitles(query);
            System.out.println("Discovered " + queryResults.length + " cinematic works:");
            Arrays.stream(queryResults).forEach(title -> System.out.println("- " + title));
            System.out.println();
        }
    }

    private static class CinematicWork {
        String title;
        Long year;
        String imdbID;

        CinematicWork(String title, Long year, String imdbID) {
            this.title = title;
            this.year = year;
            this.imdbID = imdbID;
        }
    }
}
