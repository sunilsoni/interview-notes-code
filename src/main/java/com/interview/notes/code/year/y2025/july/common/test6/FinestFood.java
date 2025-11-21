package com.interview.notes.code.year.y2025.july.common.test6;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

// No other imports beyond the list you provided
/*
Here is the **combined and properly formatted version** of the question from the screenshots regarding **"REST API: Finest Food Outlets"**:

---

## 🌐 REST API Challenge: Finest Food Outlets

You are given a REST API that returns details of food outlets.
Your goal is to **find the food outlet in a given city** with:

1. A **minimum number of votes** (threshold),
2. The **highest average rating**,
3. In case of a **tie in rating**, the outlet with the **maximum vote count**.

---

### 🧪 Function Signature

```java
public static String finestFoodOutlet(String city, int votes)
```

---

### 📥 Input Parameters

| Parameter | Type   | Description                                 |
| --------- | ------ | ------------------------------------------- |
| `city`    | String | Name of the city to search in               |
| `votes`   | int    | Minimum number of votes required for outlet |

---

### 📤 Output

* Returns the **name of the outlet** that meets the above criteria.

---

### 🌐 API Endpoint

Use a paginated **HTTP GET** request:

```
https://jsonmock.hackerrank.com/api/food_outlets?city={city}&page={page_no}
```

---

### 🔧 API Response Schema

Response includes:

```json
{
  "page": 1,
  "per_page": 10,
  "total": 20,
  "total_pages": 2,
  "data": [
    {
      "id": 1,
      "name": "Cafe ABC",
      "city": "Seattle",
      "estimated_cost": 15,
      "user_rating": {
        "average_rating": 4.9,
        "votes": 16203
      }
    }
  ]
}
```

You must iterate over **all pages** using `total_pages`.

---

### ✅ Selection Criteria

1. Filter outlets in the given city with `votes >= given_votes`.
2. Among them, choose the one with the **highest `average_rating`**.
3. If multiple outlets have the same rating, return the one with **more votes**.
4. Return its **name**.

---

### 📘 Sample Cases

#### ✅ Case 1

**Input:**

```
city: Seattle
votes: 500
```

**Output:**

```
Cafe Juanita
```

**Explanation:**

* Filtered 4 outlets with rating 4.9
* "Cafe Juanita" has the highest votes (16203)

---

#### ✅ Case 2

**Input:**

```
city: Miami
votes: 1000
```

**Output:**

```
Pirates of Grill
```

**Explanation:**

* Filtered 5 outlets with rating 4.8
* "Pirates of Grill" has the highest vote count (4879)

---

Would you like the full **Java implementation with `HttpURLConnection`**, `BufferedReader`, and JSON parsing (for HackerRank-style environments)?

 */
public class FinestFood {

    private static final HttpClient HTTP = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static String finestFoodOutlet(String city, int minVotes) throws IOException, InterruptedException {
        String bestName = "";
        double bestRating = -1;
        int bestVotes = -1;

        // URL-encode city
        String cityEnc = URLEncoder.encode(city, StandardCharsets.UTF_8);
        int page = 1, totalPages;

        do {
            String uri = String.format(
                    "https://jsonmock.hackerrank.com/api/food_outlets?city=%s&page=%d",
                    cityEnc, page
            );

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .build();

            String body = HTTP.send(req, HttpResponse.BodyHandlers.ofString())
                    .body();

            ApiResponse resp = GSON.fromJson(body, ApiResponse.class);
            totalPages = resp.total_pages;

            // process this page
            for (Outlet o : resp.data) {
                int v = o.user_rating.votes;
                double r = o.user_rating.average_rating;
                if (v < minVotes) continue;

                if (r > bestRating || (r == bestRating && v > bestVotes)) {
                    bestRating = r;
                    bestVotes = v;
                    bestName = o.name;
                }
            }
        } while (++page <= totalPages);

        return bestName.isEmpty() ? "No outlet found" : bestName;
    }

    // --- simple main for pass/fail tests ---
    public static void main(String[] args) throws Exception {
        String[][] tests = {
                {"Seattle", "500", "Cafe Juanita"},
                {"Miami", "1000", "Pirates of Grill"},
                {"Gotham", "10", "No outlet found"}  // extra edge case
        };

        for (var t : tests) {
            String city = t[0], expect = t[2];
            int mv = Integer.parseInt(t[1]);
            String got = finestFoodOutlet(city, mv);
            System.out.printf(
                    "Test %s(minVotes=%d): got='%s' [%s]\n",
                    city, mv, got, got.equals(expect) ? "PASS" : "FAIL"
            );
        }
    }

    // --- JSON mapping classes for Gson ---
    static class ApiResponse {
        int total_pages;
        Outlet[] data;
    }

    static class Outlet {
        String name;
        Rating user_rating;
    }

    static class Rating {
        double average_rating;
        int votes;
    }
}