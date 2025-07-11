package com.interview.notes.code.year.y2025.july.common.test5;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static List<Post> fetchPosts() {
        try {
            var req = HttpRequest.newBuilder()
                    .uri(URI.create("https://coderbyte.com/api/challenges/json/all-posts"))
                    .GET()
                    .build();
            var resp = CLIENT.send(req, BodyHandlers.ofString());
            return Arrays.asList(GSON.fromJson(resp.body(), Post[].class));
        } catch (Exception e) {
            System.err.println("Fetch error: " + e.getMessage());
            return List.of();
        }
    }

    public static List<Summary> formatPosts(List<Post> posts) {
        // 1) count per user
        Map<Integer, Long> counts = posts.stream()
                .collect(Collectors.groupingBy(p -> p.userId, Collectors.counting()));

        // 2) build summaries and sort:
        return counts.entrySet().stream()
                .map(e -> new Summary(e.getKey(), e.getValue().intValue()))
                .sorted(
                        // primary: numberOfPosts desc
                        Comparator.comparingInt((Summary s) -> s.numberOfPosts)
                                .reversed()
                                // secondary: userId desc
                                .thenComparing(Comparator.comparingInt((Summary s) -> s.userId)
                                        .reversed())
                )
                .collect(Collectors.toList());
    }

    private static Post makePost(int u, int i) {
        var p = new Post();
        p.userId = u;
        p.id = i;
        return p;
    }

    public static void main(String[] args) {
        // real
        System.out.println("=== Live Data ===");
        System.out.println(formatPosts(fetchPosts()));

        // tests
        System.out.println("\n=== Tests ===");
        List<Post> t1 = List.of(
                makePost(1, 1), makePost(2, 1), makePost(1, 2)
        );
        List<Summary> e1 = List.of(
                new Summary(1, 2), new Summary(2, 1)
        );
        System.out.println("Test1: " + (formatPosts(t1).equals(e1) ? "PASS" : "FAIL"));

        List<Post> t2 = List.of(
                makePost(3, 1), makePost(2, 1),
                makePost(3, 2), makePost(2, 2)
        );
        List<Summary> e2 = List.of(
                new Summary(3, 2), new Summary(2, 2)
        );
        System.out.println("Test2: " + (formatPosts(t2).equals(e2) ? "PASS" : "FAIL"));
    }

    static class Post {
        int userId, id;
    }

    static class Summary {
        int userId, numberOfPosts;

        Summary(int u, int n) {
            userId = u;
            numberOfPosts = n;
        }

        @Override
        public String toString() {
            return "{userId:" + userId + ", numberOfPosts:" + numberOfPosts + "}";
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof Summary s
                    && s.userId == userId
                    && s.numberOfPosts == numberOfPosts;
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, numberOfPosts);
        }
    }
}