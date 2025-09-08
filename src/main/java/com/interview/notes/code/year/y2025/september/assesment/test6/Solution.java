package com.interview.notes.code.year.y2025.september.assesment.test6;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.*;

import com.google.gson.Gson;

/**
 * Business context and requirements overview:
 *
 * Goal: Given an organizer name and a music genre, find the ID of the event with the
 * longest duration organized by that organizer that includes the given genre.
 *
 * Data source: https://jsonmock.hackerrank.com/api/events
 * - Query parameter organized_by is used to filter by organizer on the server side.
 * - The API is paginated (fields: page, per_page, total, total_pages) and returns an array of events in data.
 * - Each Event may contain: id, name, genres (array of strings), duration (minutes), organized_by (string).
 *
 * Business rules and edge cases:
 * 1) Only events whose organized_by exactly matches the given organizer (case-insensitive) are eligible.
 * 2) The event must include the given genre (case-insensitive) within its genres array.
 * 3) Among eligible events, choose the one with the highest duration (null duration is treated as 0).
 * 4) Tie-breaker: if multiple events share the same (max) duration, choose the event with lexicographically smaller id
 *    (achieved by sorting by duration desc, then by id asc, and taking the first).
 * 5) If no eligible event exists, or on any error (network/JSON), return "-1".
 *
 * Performance considerations:
 * - Fetch all pages for the given organizer from the endpoint using the provided total_pages value.
 * - Stream operations filter and sort in-memory; for very large datasets a streaming comparator or partial reduction could
 *   be used, but API pagination keeps it practical here. A warm-up load (500 iterations) is present in test() to exercise
 *   the method for basic performance.
 *
 * Inputs/Outputs:
 * - Input: organizer (String), genre (String)
 * - Output: event id (String) of the best match, or "-1" if none.
 *
 * Examples:
 * - organizer = "empower integrated markets", genre = "Reggae" -> returns a concrete event id if present.
 * - organizer = "no such org", genre = "Jazz" -> returns "-1".
 */
public class Solution {

    static class Event {
        String id;
        String name;
        String[] genres;
        Integer duration;
        String organized_by;
    }

    static class Api {
        int page;
        int per_page;
        int total;
        int total_pages;
        Event[] data;
    }

    static final HttpClient HTTP = HttpClient.newHttpClient();
    static final Gson GSON = new Gson();

    /**
     * Find the ID of the longest-duration event meeting the criteria.
     *
     * Business contract:
     * - Returns "-1" if no event matches or if any exception occurs (network/parse/etc.).
     * - Case-insensitive comparison for organizer and genre.
     * - Null-safe: events with null fields are ignored; null duration treated as 0 for ranking.
     * - Tie-breaker by event id ascending when durations are equal.
     *
     * @param organizer Organizer name to match (case-insensitive). Must not be null; empty is allowed but likely yields no matches.
     * @param genre     Genre name to match within event genres (case-insensitive).
     * @return Event ID string of the best matching event, or "-1" if none or on error.
     */
    public static String longestDuration(String organizer, String genre) {
        try {
            // URL-encode organizer for safe query string usage
            String org = URLEncoder.encode(organizer, StandardCharsets.UTF_8);
            int page = 1, totalPages = 1;
            List<Event> all = new ArrayList<>();
            while (page <= totalPages) {
                // Build request for the current page and invoke API
                String url = "https://jsonmock.hackerrank.com/api/events?organized_by=" + org + "&page=" + page;
                HttpResponse<String> res = HTTP.send(HttpRequest.newBuilder(URI.create(url)).GET().build(), HttpResponse.BodyHandlers.ofString());
                Api api = GSON.fromJson(res.body(), Api.class);
                if (api != null && api.data != null) Collections.addAll(all, api.data);
                totalPages = api != null ? api.total_pages : 0;
                page++;
            }
            // Prepare comparator: higher duration first (null -> 0), tie-break by id ascending after reversal
            Comparator<Event> cmp = Comparator.comparingInt(e -> Optional.ofNullable(e.duration).orElse(0));
            // Filter by organizer and genre with case-insensitivity, sort and pick best match
            return all.stream()
                    .filter(Objects::nonNull)
                    .filter(e -> e.organized_by != null && e.genres != null)
                    .filter(e -> e.organized_by.equalsIgnoreCase(organizer))
                    .filter(e -> Arrays.stream(e.genres).anyMatch(g -> g.equalsIgnoreCase(genre)))
                    .sorted(cmp.reversed().thenComparing(e -> e.id))
                    .map(e -> e.id)
                    .filter(Objects::nonNull)
                    .findFirst().orElse("-1");
        } catch (Exception e) {
            // Defensive: on any exception return "-1" as per business contract
            return "-1";
        }
    }

    /**
     * Lightweight test case holder for manual verification of business rules.
     */
    static class Test {
        final String organizer;
        final String genre;
        final String expected;
        Test(String o, String g, String ex) { this.organizer = o; this.genre = g; this.expected = ex; }
    }

    /**
     * Manual smoke tests demonstrating expected behavior:
     * - Positive match with known organizer/genre pair returning a real ID.
     * - No-match cases returning "-1".
     * - A simple load loop to exercise the method repeatedly.
     */
    static void test() {
        List<Test> tests = List.of(
                new Test("empower integrated markets", "Reggae", "cf52291f-dbcf-4f88-9fd9-1eb0e8ab3c4a"),
                new Test("seize out-of-the-box e-commerce", "Blues", "-1"),
                new Test("no such org", "Jazz", "-1")
        );
        tests.forEach(t -> {
            String got = longestDuration(t.organizer, t.genre);
            System.out.println((Objects.equals(got, t.expected) ? "PASS" : "FAIL") + " | " + t.organizer + " | " + t.genre + " | expected=" + t.expected + " | got=" + got);
        });
        IntStream.range(0, 500).forEach(i -> longestDuration("empower integrated markets", i % 2 == 0 ? "Reggae" : "Electronic"));
        System.out.println("LARGE | PASS");
    }

    /**
     * Entry point for running the manual tests.
     */
    public static void main(String[] args) {
        test();
    }
}