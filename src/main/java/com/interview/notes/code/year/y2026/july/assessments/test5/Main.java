package com.interview.notes.code.year.y2026.july.assessments.test5;

import com.google.gson.Gson; // Imports Gson from the available packages to parse the JSON data

import java.net.URI; // Imports the URI class to safely construct the web address
import java.net.http.HttpClient; // Imports the modern HttpClient to handle the network connection
import java.net.http.HttpRequest; // Imports HttpRequest to configure our specific GET request
import java.net.http.HttpResponse; // Imports HttpResponse to receive and process the server's reply
import java.util.List; // Imports the List interface to hold our parsed JSON array

public class Main { // Declares the Main class which houses our program

    public static void main(String[] args) { // The main method serves as the entry point for execution and our custom testing framework
        System.out.println("--- Starting Tests ---"); // Prints a clear separator to indicate tests are beginning
        
        // TEST CASE 1: Live API Fetch
        try { // Starts a try block to catch any network or parsing errors during the live test
            int count = fetchAndCountPosts(); // Calls the required method and stores the returned integer count
            System.out.println("Number of posts: " + count); // Prints the required exact output format: "Number of posts: X"
            if (count > 0) { // Evaluates if the API successfully returned a positive number of posts
                System.out.println("Test Case 1 (Live API): PASS"); // Prints PASS if the data was fetched and counted successfully
            } else { // Handles the scenario where the count is 0 or negative (unexpected)
                System.out.println("Test Case 1 (Live API): FAIL - No posts found"); // Prints FAIL if the validation condition is not met
            } // Closes the if-else block
        } catch (Exception e) { // Catches any exceptions thrown by the HTTP client or JSON parser
            System.out.println("Test Case 1 (Live API): FAIL - Exception: " + e.getMessage()); // Prints FAIL along with the error message for debugging
        } // Closes the try-catch block

        // TEST CASE 2: Large Data Input Simulation
        try { // Starts a second try block to test our parsing logic against simulated large data
            StringBuilder largeJsonBuilder = new StringBuilder("["); // Initializes a StringBuilder to construct a massive JSON array in memory
            for (int i = 0; i < 100000; i++) { // Loops one hundred thousand times to generate a large dataset
                largeJsonBuilder.append("{\"id\":").append(i).append("}"); // Appends a dummy JSON object representing a single post
                if (i < 99999) largeJsonBuilder.append(","); // Appends a comma between objects, ensuring the last object doesn't have one
            } // Closes the data generation loop
            largeJsonBuilder.append("]"); // Closes the JSON array string
            
            var gson = new Gson(); // Instantiates Gson to parse our artificially created large data
            var parsedLargeData = gson.fromJson(largeJsonBuilder.toString(), List.class); // Parses the massive JSON string into a List
            
            if (parsedLargeData.size() == 100000) { // Checks if the parser successfully counted all 100,000 generated items
                System.out.println("Test Case 2 (Large Data): PASS"); // Prints PASS if the large data was handled without memory exhaustion or truncation
            } else { // Handles the scenario where the count is incorrect
                System.out.println("Test Case 2 (Large Data): FAIL - Incorrect count"); // Prints FAIL if the parsed size doesn't match our loop count
            } // Closes the if-else block
        } catch (Exception e) { // Catches OutOfMemoryErrors or parsing exceptions
            System.out.println("Test Case 2 (Large Data): FAIL - " + e.getMessage()); // Prints FAIL with the error details
        } // Closes the try-catch block
    } // Closes the main method

    public static int fetchAndCountPosts() throws Exception { // Defines the requested method, declaring it might throw exceptions to the caller
        var client = HttpClient.newHttpClient(); // Creates a new, default HttpClient instance using Java's 'var' keyword for brevity
        
        var request = HttpRequest.newBuilder() // Begins building a new HTTP request configuration
                .uri(URI.create("https://coderbyte.com/api/challenges/json/all-posts")) // Sets the target endpoint URL exactly as specified in the prompt
                .GET() // Explicitly sets the HTTP method to GET (which is the default, but good for clarity)
                .build(); // Finalizes and builds the immutable HttpRequest object
        
        var response = client.send(request, HttpResponse.BodyHandlers.ofString()); // Sends the request synchronously and instructs the client to treat the response body as a String
        var responseBody = response.body(); // Extracts the raw JSON text from the HTTP response
        
        var gson = new Gson(); // Creates a new Gson instance to handle the conversion from JSON text to Java objects
        var postsList = gson.fromJson(responseBody, List.class); // Parses the JSON string. Since it's a JSON array, Gson maps it to a standard Java List
        
        return postsList != null ? postsList.size() : 0; // Uses a ternary operator to safely return the list size, or 0 if the API returned null/empty
    } // Closes the fetchAndCountPosts method
} // Closes the Main class