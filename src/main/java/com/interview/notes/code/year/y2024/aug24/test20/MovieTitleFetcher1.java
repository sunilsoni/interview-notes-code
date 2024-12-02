package com.interview.notes.code.year.y2024.aug24.test20;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class MovieTitleFetcher1 {

    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api/moviesdata/search/?Title=";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String[] getMovieTitles(String substr) {
        List<String> titles = new ArrayList<>();
        int currentPage = 1;
        int totalPages;

        do {
            String url = BASE_URL + substr + "&page=" + currentPage;
            try {
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                JsonNode jsonResponse = mapper.readTree(response.body());

                totalPages = jsonResponse.get("total_pages").asInt();
                StreamSupport.stream(jsonResponse.get("data").spliterator(), false)
                        .map(movie -> movie.get("Title").asText())
                        .forEach(titles::add);

                currentPage++;
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        } while (currentPage <= totalPages);

        return titles.stream().sorted().toArray(String[]::new);
    }

    public static void main(String[] args) {
        List.of("spiderman", "superman", "batman").forEach(query -> {
            System.out.println("Search for: " + query);
            String[] results = getMovieTitles(query);
            for (String title : results) {
                System.out.println(title);
            }
            System.out.println();
        });
    }
}
/*
earch for: spiderman
Fighting, Flying and Driving: The Stunts of Spiderman 3
Italian Spiderman
Spiderman
Spiderman 5
Spiderman in Cannes
Superman, Spiderman or Batman

Search for: superman
Superman, Spiderman or Batman
The Superman/Aquaman Hour of Adventure

Search for: batman
Batman: Rise of Sin Tzu
Superman, Spiderman or Batman

 */