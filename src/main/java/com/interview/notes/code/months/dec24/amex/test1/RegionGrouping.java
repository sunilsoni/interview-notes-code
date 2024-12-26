package com.interview.notes.code.months.dec24.amex.test1;

import java.util.*;

public class RegionGrouping {

    // Simple container for row data
    static class Record {
        String uniqueName;
        String region;
        String env;
        Map<String, String> attributes;
        
        Record(String uniqueName, String region, String env, Map<String, String> attributes) {
            this.uniqueName = uniqueName;
            this.region = region;
            this.env = env;
            this.attributes = attributes;
        }
    }

    // Groups data into desired structure
    public static Map<String, Map<String, Map<String, String>>> groupByRegionEnv(List<Record> records) {
        Map<String, Map<String, Map<String, String>>> result = new HashMap<>();
        for (Record r : records) {
            result
               .computeIfAbsent(r.region, k -> new HashMap<>())
               .computeIfAbsent(r.env, k -> new HashMap<>())
               .putAll(r.attributes);
        }
        return result;
    }

    public static void main(String[] args) {
        // Test data
        List<Record> testData = new ArrayList<>();
        testData.add(new Record("abce1","USA","e1",Map.of("attribute1","value","attribute2","value","attribute3","value")));
        testData.add(new Record("abce2","USA","e2",Map.of("attribute1","value","attribute2","value","attribute3","value")));
        testData.add(new Record("abcinde1","IND","e1",Map.of("attribute1","value","attribute2","value","attribute3","value")));
        
        // Expected: "USA"→"e1/e2", "IND"→"e1"
        Map<String, Map<String, Map<String, String>>> actual = groupByRegionEnv(testData);

        // Simple pass/fail check
        if (actual.containsKey("USA") 
            && actual.get("USA").containsKey("e1") 
            && actual.containsKey("IND")) {
            System.out.println("Test 1: PASS");
        } else {
            System.out.println("Test 1: FAIL");
        }
        
        // Add more tests or large data checks as needed
    }
}
