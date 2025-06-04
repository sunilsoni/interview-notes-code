package com.interview.notes.code.year.y2025.may.hackerank.test1;

import java.io.*;
import java.util.*;

public class SocialNetworkTest {
    public static void main(String[] args) {
        Platform platform = new Platform();
        
        // Store expected output
        List<String> expectedOutput = Arrays.asList(
            "Alexander added successfully.",
            "Isabella added successfully.",
            "Emma added successfully.",
            "Isabella is now following Alexander.",
            "Alexander posted: \"Hiking in the mountains.\".",
            "Isabella received notification: Alexander posted: \"Hiking in the mountains.\".",
            "Emma is now following Alexander.",
            "Alexander posted: \"Enjoying a beautiful day!\".",
            "Isabella received notification: Alexander posted: \"Enjoying a beautiful day!\".",
            "Emma received notification: Alexander posted: \"Enjoying a beautiful day!\".",
            "Isabella has unfollowed Alexander."
        );

        // Redirect System.out to capture output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        // Run test operations
        platform.addUser(0, "Alexander");
        platform.addUser(1, "Isabella");
        platform.addUser(2, "Emma");

        platform.follow(1, 0); // Isabella follows Alexander
        platform.post(0, "Hiking in the mountains.");
        platform.follow(2, 0); // Emma follows Alexander
        platform.post(0, "Enjoying a beautiful day!");
        platform.unfollow(1, 0); // Isabella unfollows Alexander

        // Restore System.out
        System.out.flush();
        System.setOut(originalOut);

        // Get actual output lines
        List<String> actualOutput = Arrays.asList(baos.toString().split("\\R"));

        // Compare expected vs actual
        System.out.println("=== Test Result ===");
        boolean allPass = true;
        for (int i = 0; i < expectedOutput.size(); i++) {
            String expected = expectedOutput.get(i);
            String actual = i < actualOutput.size() ? actualOutput.get(i).trim() : "<missing>";
            boolean pass = expected.equals(actual);
            System.out.printf("%s | Expected: %-70s | Actual: %-70s\n", 
                pass ? "PASS" : "FAIL", expected, actual);
            if (!pass) allPass = false;
        }

        if (allPass) {
            System.out.println("\n✅ All test cases passed!");
        } else {
            System.out.println("\n❌ Some test cases failed.");
        }
    }
}