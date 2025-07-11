package com.interview.notes.code.year.y2025.july.common.test1;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    // --- FETCH FROM API ---
    public static List<Post> fetchPosts() throws IOException {
        URL url = new URL("https://coderbyte.com/api/challenges/json/all-posts");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (Reader reader = new InputStreamReader(conn.getInputStream())) {
            Post[] arr = new Gson().fromJson(reader, Post[].class);
            return Arrays.asList(arr);
        }
    }

    public static List<Comment> fetchComments() throws IOException {
        URL url = new URL("https://coderbyte.com/api/challenges/json/all-comments");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (Reader reader = new InputStreamReader(conn.getInputStream())) {
            Comment[] arr = new Gson().fromJson(reader, Comment[].class);
            return Arrays.asList(arr);
        }
    }

    // --- AGGREGATION LOGIC ---
    public static List<Map<String, Integer>> aggregateComments(
            List<Post> posts,
            List<Comment> comments
    ) {
        // Count comments per postId
        Map<Integer, Long> counts = comments.stream()
                .collect(Collectors.groupingBy(
                        Comment::getPostId,
                        Collectors.counting()
                ));

        // For each post, build a map; missing => 0
        return posts.stream()
                .map(p -> {
                    Map<String, Integer> m = new HashMap<>();
                    m.put("postId", p.getId());
                    m.put("numberOfComments",
                            counts.getOrDefault(p.getId(), 0L).intValue());
                    return m;
                })
                // sort: first by comment count desc, then postId desc
                .sorted(Comparator
                        .comparing((Map<String, Integer> m) -> m.get("numberOfComments"))
                        .reversed()
                        .thenComparing(m -> m.get("postId"), Comparator.reverseOrder())
                )
                .collect(Collectors.toList());
    }

    // helper to build expected maps in tests
    private static Map<String, Integer> mk(int postId, int c) {
        Map<String, Integer> m = new HashMap<>();
        m.put("postId", postId);
        m.put("numberOfComments", c);
        return m;
    }

    // --- SIMPLE MAIN METHOD WITH TESTS + LIVE RUN ---
    public static void main(String[] args) {
        runTests();
        System.out.println("\n--- Fetching real data from API ---");
        try {
            List<Post> posts = fetchPosts();
            List<Comment> comments = fetchComments();
            List<Map<String, Integer>> agg = aggregateComments(posts, comments);
            agg.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error fetching API data: " + e.getMessage());
        }
    }

    private static void runTests() {
        System.out.println("Running unit tests...");
        int passed = 0, total = 0;

        // Test 1: no comments => all zero
        total++;
        List<Post> p1 = Arrays.asList(new Post(1), new Post(2));
        List<Comment> c1 = Collections.emptyList();
        List<Map<String, Integer>> r1 = aggregateComments(p1, c1);
        List<Map<String, Integer>> e1 = Arrays.asList(mk(1, 0), mk(2, 0));
        if (r1.equals(e1)) {
            System.out.println("Test1: PASS");
            passed++;
        } else {
            System.out.println("Test1: FAIL → " + r1);
        }

        // Test 2: simple counts
        total++;
        List<Post> p2 = Arrays.asList(new Post(1), new Post(2));
        List<Comment> c2 = Arrays.asList(new Comment(1), new Comment(1), new Comment(2));
        List<Map<String, Integer>> r2 = aggregateComments(p2, c2);
        List<Map<String, Integer>> e2 = Arrays.asList(mk(1, 2), mk(2, 1));
        if (r2.equals(e2)) {
            System.out.println("Test2: PASS");
            passed++;
        } else {
            System.out.println("Test2: FAIL → " + r2);
        }

        // Test 3: tie → postId desc
        total++;
        List<Post> p3 = Arrays.asList(new Post(1), new Post(2), new Post(3));
        List<Comment> c3 = Arrays.asList(
                new Comment(1), new Comment(2), new Comment(3)
        );
        // counts all =1 → order 3,2,1
        List<Map<String, Integer>> r3 = aggregateComments(p3, c3);
        List<Map<String, Integer>> e3 = Arrays.asList(mk(3, 1), mk(2, 1), mk(1, 1));
        if (r3.equals(e3)) {
            System.out.println("Test3: PASS");
            passed++;
        } else {
            System.out.println("Test3: FAIL → " + r3);
        }

        // Test 4: large data
        total++;
        int N = 5_000;   // number of posts
        int M = 50_000;  // number of comments
        List<Post> LP = IntStream.rangeClosed(1, N)
                .mapToObj(Post::new).collect(Collectors.toList());
        Random rnd = new Random(0);
        List<Comment> LC = IntStream.range(0, M)
                .mapToObj(i -> new Comment(rnd.nextInt(N) + 1))
                .collect(Collectors.toList());
        List<Map<String, Integer>> LR = aggregateComments(LP, LC);
        long sum = LR.stream()
                .mapToInt(m -> m.get("numberOfComments"))
                .sum();
        if (sum == M) {
            System.out.println("Test4: PASS");
            passed++;
        } else {
            System.out.println("Test4: FAIL → counted " + sum);
        }

        System.out.printf("Passed %d/%d tests.%n", passed, total);
    }

    // --- DATA CLASSES ---
    public static class Post {
        private int id;

        public Post() {
        }                    // Gson needs no-arg constructor

        public Post(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    public static class Comment {
        private int postId;

        public Comment() {
        }

        public Comment(int postId) {
            this.postId = postId;
        }

        public int getPostId() {
            return postId;
        }
    }
}