package com.interview.notes.code.months.dec24.ibm.test3;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/*
WORKING


## **Problem Statement**
You need to retrieve information from a **food outlets database** using an HTTP GET API. Given a city name and a minimum number of votes, the task is to **filter food outlets** by the following rules:
1. Outlets must be located in the specified city.
2. Outlets must have a **votes count greater than or equal to** the provided minimum.
3. Among filtered outlets:
   - Pick the one with the **highest average rating**.
   - If multiple outlets share the same rating, choose the one with the **maximum vote count**.

### API Details
- Base API URL:
  ```
  https://jsonmock.hackerrank.com/api/food_outlets?city=<city>
  ```
- Paginated results: Append `&page=<num>` to access additional pages.

---

## **Response Format**
The API returns a JSON object with the following fields:
```json
{
    "page": <current_page_number>,
    "per_page": <results_per_page>,
    "total": <total_number_of_results>,
    "total_pages": <total_pages_with_results>,
    "data": [<list_of_food_outlets>]
}
```

### Each outlet contains:
- `id`: Integer
- `name`: String
- `city`: String
- `estimated_cost`: Number
- `user_rating`:
   - `average_rating`: Number
   - `votes`: Number

---

## **Function Description**
Complete the function **`finestFoodOutlet`**.

### **Function Signature**
```java
public static String finestFoodOutlet(String city, int votes)
```

### **Parameters**
- `String city`: The name of the city.
- `int votes`: Minimum vote threshold.

### **Returns**
- `String`: The name of the **winning food outlet**.

---

## **Input Format**
1. The first line contains a string, `city`.
2. The second line contains an integer, `votes`.

---

## **Constraints**
- API queries are paginated; handle multiple pages.
- City names and votes are validated inputs.

---

## **Sample Cases**

### **Sample Case 0**
**Input:**
```
Seattle
500
```

**Output:**
```
Cafe Juanita
```

**Explanation:**
In Seattle, outlets with votes ≥ 500 are filtered. Among 4 such outlets, `Cafe Juanita` has the highest rating of 4.9 with 16203 votes.

---

### **Sample Case 1**
**Input:**
```
Miami
1000
```

**Output:**
```
Pirates of Grill
```

**Explanation:**
In Miami, outlets with votes ≥ 1000 are filtered. Among 5 such outlets, `Pirates of Grill` has the highest rating of 4.8 with 4879 votes.

---

## **Function Code Skeleton**
```java
/*
 * Complete the 'finestFoodOutlet' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts following parameters:
 *  1. STRING city
 *  2. INTEGER votes
 * API URL https://jsonmock.hackerrank.com/api/food_outlets?city={city}&page={page_no}

 */

public class FoodOutletFinder {
    private static final String BASE_URL = "https://jsonmock.hackerrank.com/api/food_outlets";
    private static final HttpClient client = HttpClient.newHttpClient();

    public static String finestFoodOutlet(String city, int votes) {
        String bestOutlet = null;
        double highestRating = -1;
        int maxVotes = -1;

        try {
            String encodedCity = URLEncoder.encode(city, "UTF-8");
            int currentPage = 1;
            int totalPages = 1;

            do {
                String apiUrl = String.format("%s?city=%s&page=%d", BASE_URL, encodedCity, currentPage);
                JSONObject response = makeApiCall(apiUrl);

                if (currentPage == 1) {
                    totalPages = ((Long) response.get("total_pages")).intValue();
                }

                JSONArray outlets = (JSONArray) response.get("data");
                for (Object obj : outlets) {
                    JSONObject outlet = (JSONObject) obj;
                    JSONObject userRating = (JSONObject) outlet.get("user_rating");
                    int voteCount = ((Long) userRating.get("votes")).intValue();
                    double rating = Double.parseDouble(userRating.get("average_rating").toString());

                    if (voteCount >= votes) {
                        if (rating > highestRating ||
                                (rating == highestRating && voteCount > maxVotes)) {
                            highestRating = rating;
                            maxVotes = voteCount;
                            bestOutlet = (String) outlet.get("name");
                        }
                    }
                }
                currentPage++;
            } while (currentPage <= totalPages);

        } catch (Exception e) {
            System.err.println("Error processing request: " + e.getMessage());
            return null;
        }

        return bestOutlet;
    }

    private static JSONObject makeApiCall(String apiUrl) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(response.body());
    }

    public static void main(String[] args) {
        // Test Suite
        Map<TestCase, String> testCases = new LinkedHashMap<>();

        // Sample test cases
        testCases.put(new TestCase("Seattle", 500), "Cafe Juanita");
        testCases.put(new TestCase("Miami", 1000), "Pirates of Grill");

        // Edge cases
        testCases.put(new TestCase("NonExistentCity", 100), null);
        testCases.put(new TestCase("Seattle", 100000), null);
        testCases.put(new TestCase("New York", 500), "Expected Restaurant");
        testCases.put(new TestCase("SEATTLE", 500), "Cafe Juanita");
        testCases.put(new TestCase("Seattle", 0), "Expected Restaurant");

        // Run all test cases
        runTestSuite(testCases);

        // Performance test
        runPerformanceTest("Los Angeles", 100);
    }

    private static void runTestSuite(Map<TestCase, String> testCases) {
        int testNumber = 1;
        for (Map.Entry<TestCase, String> entry : testCases.entrySet()) {
            TestCase testCase = entry.getKey();
            String expected = entry.getValue();

            System.out.printf("Test Case %d: ", testNumber++);
            try {
                long startTime = System.currentTimeMillis();
                String result = finestFoodOutlet(testCase.city, testCase.votes);
                long endTime = System.currentTimeMillis();

                boolean passed = (result == null && expected == null) ||
                        (result != null && result.equals(expected));

                System.out.printf("%s", passed ? "PASS" : "FAIL");
                System.out.printf(" (%.2f seconds)%n", (endTime - startTime) / 1000.0);

                if (!passed) {
                    System.out.printf("  Input: city='%s', votes=%d%n", testCase.city, testCase.votes);
                    System.out.printf("  Expected: %s%n  Got: %s%n", expected, result);
                }
            } catch (Exception e) {
                System.out.printf("FAIL (Exception: %s)%n", e.getMessage());
            }
        }
    }

    private static void runPerformanceTest(String city, int votes) {
        System.out.println("\nPerformance Test:");
        try {
            long startTime = System.currentTimeMillis();
            String result = finestFoodOutlet(city, votes);
            long endTime = System.currentTimeMillis();

            System.out.printf("Completed in %.2f seconds%n", (endTime - startTime) / 1000.0);
            System.out.printf("Result: %s%n", result);
        } catch (Exception e) {
            System.out.printf("Failed: %s%n", e.getMessage());
        }
    }

    // Helper class for test cases
    private static class TestCase {
        String city;
        int votes;

        TestCase(String city, int votes) {
            this.city = city;
            this.votes = votes;
        }
    }
}
