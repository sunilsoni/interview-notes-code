package com.interview.notes.code.year.y2025.June.amazon.test4;

public class VersionComparer {
    public static int compareSemVer(String version1, String version2) {
        // Basic input validation
        if (version1 == null || version2 == null) {
            throw new IllegalArgumentException("Version strings cannot be null");
        }
        
        // Split versions into components
        String[] v1Parts = version1.split("\\.");
        String[] v2Parts = version2.split("\\.");
        
        // Find the length of the longer version
        int maxLength = Math.max(v1Parts.length, v2Parts.length);
        
        // Compare each component
        for (int i = 0; i < maxLength; i++) {
            // Get component values, defaulting to 0 if component doesn't exist
            int v1 = (i < v1Parts.length) ? parseComponent(v1Parts[i]) : 0;
            int v2 = (i < v2Parts.length) ? parseComponent(v2Parts[i]) : 0;
            
            if (v1 < v2) return -1;
            if (v1 > v2) return 1;
        }
        
        return 0;
    }
    
    private static int parseComponent(String component) {
        try {
            // Validate component format
            if (!component.matches("\\d+")) {
                throw new IllegalArgumentException("Invalid version component: " + component);
            }
            // Check for leading zeros
            if (component.length() > 1 && component.startsWith("0")) {
                throw new IllegalArgumentException("Version components cannot have leading zeros");
            }
            return Integer.parseInt(component);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid version component: " + component);
        }
    }

    public static void main(String[] args) {
        // Test cases
        Object[][] testCases = {
            // Variable length comparisons
            {"1.6", "1.2", 1},
            {"1.6", "1.6.0", 0},
            {"1.6", "1.6.1", -1},
            {"1.2.6.4", "1.2.6", 1},
            {"1.2.6.4", "1.2.6.4", 0},
            {"1.2.6.4", "1.2.6.5", -1},
            {"1", "1.0", 0},
            {"1", "1.1", -1},
            {"1.0.0.0", "1", 0},
            
            // Edge cases
            {"1", "2", -1},
            {"2", "1", 1},
            {"1.0", "1", 0},
            {"1.2.3.4.5", "1.2.3.4.5", 0}
        };

        for (Object[] test : testCases) {
            String v1 = (String)test[0];
            String v2 = (String)test[1];
            int expected = (Integer)test[2];
            
            try {
                int result = compareSemVer(v1, v2);
                System.out.printf("Comparing %s vs %s: %d (Expected: %d) %s\n", 
                    v1, v2, result, expected, 
                    result == expected ? "✅" : "❌");
            } catch (Exception e) {
                System.out.printf("Error comparing %s vs %s: %s\n", 
                    v1, v2, e.getMessage());
            }
        }
    }
}
