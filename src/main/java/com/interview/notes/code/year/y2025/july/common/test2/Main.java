package com.interview.notes.code.year.y2025.july.common.test2;

import java.net.http.*;
import java.net.URI;
import java.util.*;
import java.util.stream.*;
import com.google.gson.*;

public class Main {
  record Post(int id) {}
  record Comment(int postId) {}

  static List<Post> fetchPosts() throws Exception {
    var body = HttpClient.newHttpClient()
      .send(HttpRequest.newBuilder(URI.create(
        "https://coderbyte.com/api/challenges/json/all-posts"))
        .GET().build(),
        HttpResponse.BodyHandlers.ofString())
      .body();
    return Stream.of(new Gson().fromJson(body, Post[].class)).toList();
  }

  static List<Comment> fetchComments() throws Exception {
    var body = HttpClient.newHttpClient()
      .send(HttpRequest.newBuilder(URI.create(
        "https://coderbyte.com/api/challenges/json/all-comments"))
        .GET().build(),
        HttpResponse.BodyHandlers.ofString())
      .body();
    return Stream.of(new Gson().fromJson(body, Comment[].class)).toList();
  }

  static List<Map<String,Integer>> aggregateComments(
    List<Post> posts,
    List<Comment> comments
  ) {
    var cnt = comments.stream()
      .collect(Collectors.groupingBy(
        Comment::postId, Collectors.counting()));

    return posts.stream()
      .map(p -> Map.of(
        "postId", p.id(),
        "numberOfComments", cnt.getOrDefault(p.id(), 0L).intValue()
      ))
      .sorted(Comparator
        // highest count first
        .comparing((Map<String,Integer> m) -> m.get("numberOfComments"))
        .reversed()
        // for ties: if count>0, sort postId desc; if count==0, keep input order
        .thenComparing(m -> m.get("numberOfComments") == 0
          ? m.get("postId")
          : -m.get("postId")
        )
      )
      .toList();
  }

  public static void main(String[] args) throws Exception {
    // --- quick tests ---
    var t1 = aggregateComments(
      List.of(new Post(1), new Post(2)),
      List.of()
    );
    System.out.println(
      (t1.equals(List.of(
         Map.of("postId", 1, "numberOfComments", 0),
         Map.of("postId", 2, "numberOfComments", 0)
       )) ? "PASS" : "FAIL")
      + " → " + t1
    );

    var p2 = List.of(new Post(1), new Post(2));
    var c2 = List.of(new Comment(1), new Comment(1), new Comment(2));
    System.out.println(
      (aggregateComments(p2, c2).equals(List.of(
         Map.of("postId", 1, "numberOfComments", 2),
         Map.of("postId", 2, "numberOfComments", 1)
       )) ? "PASS" : "FAIL")
    );

    var p3 = List.of(new Post(1), new Post(2), new Post(3));
    var c3 = List.of(new Comment(1), new Comment(2), new Comment(3));
    System.out.println(
      (aggregateComments(p3, c3).equals(List.of(
         Map.of("postId", 3, "numberOfComments", 1),
         Map.of("postId", 2, "numberOfComments", 1),
         Map.of("postId", 1, "numberOfComments", 1)
       )) ? "PASS" : "FAIL")
    );

    // large-data sanity check
    int N = 5_000, M = 50_000;
    var LP = IntStream.rangeClosed(1, N).mapToObj(Post::new).toList();
    var rnd = new Random(0);
    var LC = IntStream.range(0, M)
      .mapToObj(i -> new Comment(rnd.nextInt(N) + 1))
      .toList();
    var LR = aggregateComments(LP, LC);
    long sum = LR.stream()
      .mapToInt(m -> m.get("numberOfComments"))
      .sum();
    System.out.println((sum == M ? "PASS" : "FAIL"));

    // --- real fetch & print ---
    // aggregateComments(fetchPosts(), fetchComments()).forEach(System.out::println);
  }
}