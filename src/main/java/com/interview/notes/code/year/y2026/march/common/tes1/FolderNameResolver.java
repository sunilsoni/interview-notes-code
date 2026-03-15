package com.interview.notes.code.year.y2026.march.common.tes1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FolderNameResolver {

    public static String generateNewFolderName(List<String> existingFolders) {
        var set = new HashSet<>(existingFolders);
        if (!set.contains("New Folder")) {
            return "New Folder";
        }
        int n = 2;
        while (set.contains("New Folder (" + n + ")")) {
            n++;
        }
        return "New Folder (" + n + ")";
    }

    public static void main(String[] args) {
        test(List.of("New Folder", "New Folder (3)", "New Folder (4)"), "New Folder (2)");
        test(List.of(), "New Folder");
        test(List.of("New Folder"), "New Folder (2)");
        test(List.of("New Folder", "New Folder (2)"), "New Folder (3)");

        var largeData = new ArrayList<String>();
        largeData.add("New Folder");
        for (int i = 2; i <= 100000; i++) {
            if (i != 75000) {
                largeData.add("New Folder (" + i + ")");
            }
        }
        test(largeData, "New Folder (75000)");
    }

    private static void test(List<String> input, String expected) {
        long startTime = System.currentTimeMillis();
        String result = generateNewFolderName(input);
        long endTime = System.currentTimeMillis();
        
        if (result.equals(expected)) {
            System.out.println("PASS (" + (endTime - startTime) + "ms)");
        } else {
            System.out.println("FAIL - Expected: '" + expected + "', Got: '" + result + "'");
        }
    }
}