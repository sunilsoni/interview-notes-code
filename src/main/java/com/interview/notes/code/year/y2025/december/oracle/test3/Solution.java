package com.interview.notes.code.year.y2025.december.oracle.test3;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {

    // Gson instance reused to avoid repeated allocations and improve performance
    private static final Gson GSON = new Gson();

    // API base URL given by the problem
    private static final String API = "https://jsonmock.hackerrank.com/api/medical_records?page=";

    // DOB format in API: "DD-MM-YYYY"
    private static final DateTimeFormatter DOB_FMT = DateTimeFormatter.ofPattern("dd-MM-uuuu");

    // --------- DTOs (only fields we need) ---------

    public static List<Integer> getRecordsByAgeGroup(int ageStart, int ageEnd, int bpDiff) {
        List<Integer> matched = new ArrayList<>();    // stores ids that pass both filters

        int page = 1;                                 // start with page 1
        int totalPages = 1;                           // updated after first call

        while (page <= totalPages) {                  // keep fetching until last page
            ApiResponse resp = fetchPage(page);        // call API + parse JSON

            if (resp == null) break;                  // if API failed, stop safely

            totalPages = resp.totalPages;             // set total pages from response

            if (resp.data != null) {                  // avoid null pointer if data missing
                for (Record r : resp.data) {          // iterate records on this page
                    if (r == null) continue;          // skip null record entries

                    Integer age = ageAt(r.userDob, r.timestamp); // compute age at record time
                    if (age == null) continue;        // skip if DOB/timestamp invalid

                    int diff = bpDifference(r.vitals); // compute diastole - systole
                    if (diff == Integer.MIN_VALUE) continue; // skip if vitals missing

                    boolean inAgeRange = age >= ageStart && age <= ageEnd; // inclusive check
                    boolean bpOk = diff > bpDiff;      // must be strictly greater (per sample)

                    if (inAgeRange && bpOk) {          // both conditions must match
                        matched.add(r.id);             // keep record id
                    }
                }
            }

            page++;                                   // move to next page
        }

        if (matched.isEmpty()) {                      // if nothing matched
            return Collections.singletonList(-1);      // return [-1] as required
        }

        Collections.sort(matched);                     // output must be sorted
        return matched;                                // return final ids
    }

    private static ApiResponse fetchPage(int page) {
        HttpURLConnection conn = null;                // connection handle
        try {
            URL url = new URL(API + page);            // build page URL
            conn = (HttpURLConnection) url.openConnection(); // open connection
            conn.setRequestMethod("GET");             // HTTP GET
            conn.setConnectTimeout(8000);             // avoid hanging forever
            conn.setReadTimeout(8000);                // avoid hanging forever

            int code = conn.getResponseCode();        // HTTP status code
            if (code != 200) return null;             // non-OK -> treat as failure

            try (BufferedReader br = new BufferedReader( // buffered text reader
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {

                StringBuilder sb = new StringBuilder();   // builds full JSON string
                String line;                              // holds each read line

                while ((line = br.readLine()) != null) {  // read until EOF
                    sb.append(line);                       // append JSON chunk
                }

                return GSON.fromJson(sb.toString(), ApiResponse.class); // parse JSON
            }
        } catch (IOException e) {                      // network / IO issues
            return null;                               // fail safely
        } finally {
            if (conn != null) conn.disconnect();       // release network resources
        }
    }

    private static Integer ageAt(String dobStr, long timestampMillis) {
        if (dobStr == null) return null;               // DOB required
        try {
            LocalDate dob = LocalDate.parse(dobStr, DOB_FMT); // parse DOB

            LocalDate recordDate = Instant.ofEpochMilli(timestampMillis) // timestamp -> Instant
                    .atZone(ZoneOffset.UTC)            // force UTC as statement says
                    .toLocalDate();                    // take date part only

            if (recordDate.isBefore(dob)) return null; // invalid if record before birth

            long years = ChronoUnit.YEARS.between(dob, recordDate); // full years elapsed
            return (int) years;                        // safe cast for realistic ages
        } catch (DateTimeParseException ex) {          // DOB parsing failed
            return null;                               // skip record
        } catch (Exception ex) {                       // any other unexpected issue
            return null;                               // skip record safely
        }
    }

    // --------- Required function ---------

    private static int bpDifference(Vitals v) {
        if (v == null) return Integer.MIN_VALUE;       // missing vitals -> mark invalid
        return v.bloodPressureDiastole - v.bloodPressureSystole; // required difference
    }

    // --------- Helpers ---------

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);           // reads stdin

        if (sc.hasNextInt()) {                         // HackerRank path (inputs exist)
            int ageStart = sc.nextInt();               // read ageStart
            int ageEnd = sc.nextInt();                 // read ageEnd
            int bpDiff = sc.nextInt();                 // read bpDiff

            List<Integer> ans = getRecordsByAgeGroup(ageStart, ageEnd, bpDiff); // compute
            System.out.print(ans.stream()              // stream ids
                    .map(String::valueOf)              // convert to strings
                    .collect(Collectors.joining("\n"))); // print each on new line
            return;                                    // end program
        }

        // Local test mode when no stdin is provided
        runTests();                                    // run simple PASS/FAIL checks
    }

    private static void runTests() {
        // Test 1: sample case (from screenshot)
        List<Integer> out1 = getRecordsByAgeGroup(28, 30, 63); // should include id 31
        assertEquals("Sample(28,30,63)", list(31), out1);      // PASS/FAIL

        // Test 2: very tight filter that likely matches none (expected [-1])
        List<Integer> out2 = getRecordsByAgeGroup(0, 1, 100);  // almost impossible match
        assertEquals("NoMatch(0,1,100)", list(-1), out2);      // PASS/FAIL
    }

    private static List<Integer> list(int... a) {
        List<Integer> r = new ArrayList<>();           // build list manually
        for (int x : a) r.add(x);                      // add each element
        return r;                                      // return list
    }

    // --------- Main: supports HackerRank + local PASS/FAIL ---------

    private static void assertEquals(String name, List<Integer> exp, List<Integer> act) {
        boolean ok = exp.equals(act);                  // exact match check
        System.out.println((ok ? "PASS: " : "FAIL: ") + name); // print status
        if (!ok) {                                     // if failed
            System.out.println("Expected: " + exp);    // show expected
            System.out.println("Actual  : " + act);    // show actual
        }
    }

    static class ApiResponse {                        // Represents each page response
        int page;                                     // current page
        @SerializedName("total_pages")
        int totalPages;                               // total pages available
        List<Record> data;                            // records on this page
    }

    static class Record {                             // Represents a medical record
        int id;                                       // record id
        long timestamp;                               // record generation time (UTC millis)
        String userDob;                               // DOB "DD-MM-YYYY"
        Vitals vitals;                                // vitals object (contains BP)
    }

    static class Vitals {                             // Vitals fields we need
        int bloodPressureDiastole;                    // diastolic
        int bloodPressureSystole;                     // systolic
    }
}
