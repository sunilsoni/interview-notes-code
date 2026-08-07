package com.interview.notes.code.year.y2026.july.assessments.test6;

import com.google.gson.JsonParser; // Parses the JSON response received from the API.
import java.net.URI; // Converts the API URL string into a URI.
import java.net.http.HttpClient; // Sends the HTTP request using Java 21.
import java.net.http.HttpRequest; // Creates the GET request.
import java.net.http.HttpResponse; // Stores the response returned by the API.
import java.time.Duration; // Sets connection and request timeouts.
import java.util.stream.Collectors; // Joins generated JSON objects for the large test.
import java.util.stream.IntStream; // Generates a large number of test objects.
import java.util.stream.StreamSupport; // Creates a stream from the Gson JSON array.

class Main { // Coderbyte executes the program from this class.

    private static final String URL = // Stores the API endpoint in one reusable place.
            "https://coderbyte.com/api/challenges/json/all-posts"; // Defines the required posts URL.

    private static final boolean TEST_MODE = false; // Change to true to run PASS/FAIL tests.

    private static final HttpClient CLIENT = HttpClient.newBuilder() // Creates one reusable HTTP client.
            .connectTimeout(Duration.ofSeconds(10)) // Stops waiting if connection takes over 10 seconds.
            .build(); // Finishes building the HTTP client.

    static long fetchAndCountPosts() throws Exception { // Fetches posts and returns their total count.

        var request = HttpRequest.newBuilder() // Starts creating the HTTP request.
                .uri(URI.create(URL)) // Adds the API URL to the request.
                .timeout(Duration.ofSeconds(20)) // Stops waiting if the API takes over 20 seconds.
                .GET() // Sets the HTTP method to GET.
                .build(); // Finishes building the request.

        var response = CLIENT.send( // Sends the request to the API.
                request, // Supplies the GET request.
                HttpResponse.BodyHandlers.ofString() // Reads the response body as a string.
        ); // Stores the complete API response.

        if (response.statusCode() / 100 != 2) { // Checks whether the HTTP status is successful.
            throw new RuntimeException("HTTP " + response.statusCode()); // Reports the HTTP failure.
        } // Ends the HTTP status validation.

        return countPosts(response.body()); // Parses the response and returns the post count.
    } // Ends the API function.

    static long countPosts(String json) { // Counts posts from any supplied JSON string.

        var root = JsonParser.parseString(json); // Converts the JSON string into a Gson element.

        if (!root.isJsonArray()) { // Confirms that the response contains a top-level array.
            throw new IllegalArgumentException("Expected a JSON array"); // Rejects an unexpected response.
        } // Ends the JSON type validation.

        return StreamSupport.stream( // Creates a Java 8 stream from the JSON array.
                root.getAsJsonArray().spliterator(), // Supplies all array elements to the stream.
                false // Uses a simple sequential stream.
        ).count(); // Counts and returns the posts.
    } // Ends the counting function.

    static void test(String name, String json, long expected) { // Runs one normal PASS/FAIL test.

        try { // Handles unexpected test errors safely.

            long actual = countPosts(json); // Calculates the actual result.
            String result = actual == expected ? "PASS" : "FAIL"; // Compares expected and actual values.

            System.out.printf( // Prints the formatted test result.
                    "%s: %s | expected=%d, actual=%d%n", // Defines the test-output format.
                    name, result, expected, actual // Supplies values for the output.
            ); // Completes the test output.

        } catch (Exception e) { // Handles an unexpected exception.

            System.out.println(name + ": FAIL | " + e.getMessage()); // Prints the failure reason.

        } // Ends exception handling.
    } // Ends the normal test method.

    static void testFailure(String name, String json) { // Tests input that should be rejected.

        try { // Attempts to process the invalid input.

            countPosts(json); // Calls the method with invalid input.
            System.out.println(name + ": FAIL"); // Fails when no exception is produced.

        } catch (Exception e) { // Receives the expected exception.

            System.out.println(name + ": PASS"); // Passes because invalid input was rejected.

        } // Ends exception handling.
    } // Ends the failure-test method.

    static void testApi(long expected) { // Tests the real API response.

        try { // Handles API or network errors.

            long actual = fetchAndCountPosts(); // Calls the actual API.
            String result = actual == expected ? "PASS" : "FAIL"; // Checks the live result.

            System.out.printf( // Prints the live API test.
                    "Live API: %s | expected=%d, actual=%d%n", // Defines the output format.
                    result, expected, actual // Supplies the test values.
            ); // Completes the live test output.

        } catch (Exception e) { // Handles connection or API failure.

            System.out.println("Live API: FAIL | " + e.getMessage()); // Prints the failure reason.

        } // Ends exception handling.
    } // Ends the API test method.

    static void runTests() { // Runs all normal, edge and large-data tests.

        test("Empty array", "[]", 0); // Verifies an empty response.
        test("One post", "[{\"id\":1}]", 1); // Verifies one post.
        test("Multiple posts", "[{},{},{}]", 3); // Verifies multiple posts.

        String largeJson = IntStream.range(0, 100_000) // Generates 100,000 test positions.
                .mapToObj(number -> "{}") // Converts each position into one JSON object.
                .collect(Collectors.joining(",", "[", "]")); // Creates one large JSON array.

        test("Large data", largeJson, 100_000); // Verifies large-data handling.
        testFailure("Invalid JSON", "["); // Verifies malformed JSON handling.
        testFailure("Wrong JSON type", "{\"posts\":[]}"); // Verifies non-array handling.
        testApi(10); // Verifies the current API count.
    } // Ends the test runner.

    public static void main(String[] args) { // Starts the application.

        if (TEST_MODE) { // Checks whether tests should run.
            runTests(); // Runs every PASS/FAIL test.
            return; // Prevents the normal submission output.
        } // Ends the test-mode condition.

        try { // Handles API errors without crashing.

            System.out.println( // Prints the required final output.
                    "Number of posts: " + fetchAndCountPosts() // Fetches and displays the post count.
            ); // Completes the required output.

        } catch (Exception e) { // Handles HTTP, timeout or JSON errors.

            System.out.println("Error: " + e.getMessage()); // Prints a readable error message.

        } // Ends exception handling.
    } // Ends the main method.
} // Ends the Main class.