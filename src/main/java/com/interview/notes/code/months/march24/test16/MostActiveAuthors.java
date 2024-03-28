package com.interview.notes.code.months.march24.test16;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Java8
 * <p>
 * 2. REST API: Most Active Authors
 * In this challenge, the REST API contains information about a collection of users and articles they created. Given the threshold value, the goal is to use the API to get the list of the most active authors. Specifically, the list of usernames of users with a submission count that is strictly greater than the given threshold. The list of usernames must be returned in the order the users appear in the results.
 * To access the collection of users, make an HTTP GET request to:
 * https://jsonmock.hackerrank.com/api/article_users?page=<pageNumber>
 * where <pageNumber> is an integer denoting the page of the results to return.
 * For example, GET request to https://jsonmock.hackerrank.com/api/article_users/search?page=2 will return the second page of the collection of users. Pages are numbered from 1. In order to access the first page, request page number 1.
 * The response to a request is a JSON with 5 fields:
 * • page: The current page of the results
 * • per_page: The maximum number of users returned per page.
 * • total: The total number of users on all pages of the result.
 * • total _pages: The total number of pages with results.
 * • data: An array of objects containing users returned on the requested page
 * Each user record has the following schema:
 * • id: unique ID of the user
 * • username: the username of the user
 * • about: the about information of the user
 * • submitted: total number of articles submitted by the user
 * • updated_at: the date and time of the last update to this record
 * • submission_count: the number of submitted articles that are approved
 * • comment_count: the total number of comments the user made
 * • created_at: the date and time when the record was created
 * <p>
 * Function Description
 * Complete the function getUsernames in the editor below.
 * getUsernames has the following parameter(s):
 * threshold: integer denoting the threshold value for the number of submission count
 * The function must return an array of strings denoting the usernames of the users whose submission count is strictly greater than the given threshold. The usernames in the array must be ordered in the order they appear in the API response.
 * Note: Please review the header in the code stub to see available libraries for API requests in the selected language. Required libraries can be imported in order to solve the question. Check our full list of supported libraries at https://www.hackerrank.com/environment.
 * • Input Format For Custom Testing
 * In the first line, there is an integer threshold.
 * • Sample Case 0
 * Sample Input For Custom Testing
 * 10
 * Sample Output
 * epaga panny olalonde
 * WisNorCan
 * dmmalam
 * replicatorblog vladikoff mpweiher
 * coloneltch guelo
 * Explanation
 * The threshold value is 10, so the result must contain usernames of users with the submission count value greater than 10. There are 10 such users and their usernames in the order they first appear in the API response are as listed in Sample Output.
 * <p>
 * <p>
 * import java.io.*;.
 * class Result i
 * Complete the 'getUsernames' function below.
 * <p>
 * The function is expected to return a STRING_ARRAY.
 * The function accepts INTEGER threshold as parameter.
 * API URL: https://jsonmock.hackerrank.com/api/article_users?page=<pageNumber>
 * /
 * public static List<String> getusernames(int threshold) i
 * ｝
 */
class MostActiveAuthors {
    public static void main(String[] args) {
        int threshold = 10; // Example threshold
        List<String> usernames = MostActiveAuthors.getUsernames(threshold);
        usernames.forEach(System.out::println);
    }

    public static List<String> getUsernames(int threshold) {
        List<String> usernames = new ArrayList<>();
        try {
            int pageNumber = 1;
            while (true) {
                String response = sendGetRequest(pageNumber);
                JSONObject jsonResponse = (JSONObject) new JSONParser().parse(response);
                JSONArray data = (JSONArray) jsonResponse.get("data");
                if (data.isEmpty()) {
                    break;
                }
                for (Object userObj : data) {
                    JSONObject user = (JSONObject) userObj;
                    long submissionCount = (long) user.get("submission_count");
                    if (submissionCount > threshold) {
                        usernames.add((String) user.get("username"));
                    }
                }
                long totalPages = (long) jsonResponse.get("total_pages");
                if (pageNumber >= totalPages) {
                    break;
                }
                pageNumber++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usernames;
    }

    private static String sendGetRequest(int pageNumber) throws MalformedURLException, IOException {
        String url = "https://jsonmock.hackerrank.com/api/article_users?page=" + pageNumber;

        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("GET");
        httpClient.setRequestProperty("Content-Type", "application/json");
        httpClient.setRequestProperty("Accept", "application/json");

        int responseCode = httpClient.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } else {
            return "";
        }
    }
}
