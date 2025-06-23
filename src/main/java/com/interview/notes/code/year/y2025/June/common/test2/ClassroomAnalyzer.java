package com.interview.notes.code.year.y2025.June.common.test2;

public class ClassroomAnalyzer {

    // Main method to demonstrate and test the solution
    public static void main(String[] args) {
        // Test Case 1: Normal case with some overlapping students
        String[] classroom1 = {"arun", "bala", "charles", "dennis"};
        String[] classroom2 = {"alex", "bala", "clement", "dennis"};

        // Call the method and print results
        String[] uniqueStudents = findUniqueStudents(classroom1, classroom2);
        System.out.println("Test Case 1 - Students in only one class:");
        printArray(uniqueStudents);

        // Test Case 2: Empty arrays
        testEmptyArrays();

        // Test Case 3: No common students
        testNoCommonStudents();

        // Test Case 4: All common students
        testAllCommonStudents();

        // Test Case 5: Large data set
        testLargeDataSet();
    }

    // Method to find students present in only one class
    public static String[] findUniqueStudents(String[] class1, String[] class2) {
        // Validate input arrays
        if (class1 == null || class2 == null) {
            return new String[0];
        }

        // Create a temporary array to store unique students
        String[] temp = new String[class1.length + class2.length];
        int uniqueCount = 0;

        // Check students from class1
        for (String student : class1) {
            if (!isPresent(student, class2)) {
                temp[uniqueCount++] = student;
            }
        }

        // Check students from class2
        for (String student : class2) {
            if (!isPresent(student, class1)) {
                temp[uniqueCount++] = student;
            }
        }

        // Create final array with exact size
        String[] result = new String[uniqueCount];
        System.arraycopy(temp, 0, result, 0, uniqueCount);

        return result;
    }

    // Helper method to check if a student is present in an array
    private static boolean isPresent(String student, String[] classroom) {
        for (String s : classroom) {
            if (s.equals(student)) {
                return true;
            }
        }
        return false;
    }

    // Helper method to print array
    private static void printArray(String[] arr) {
        for (String s : arr) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    // Test method for empty arrays
    private static void testEmptyArrays() {
        System.out.println("\nTest Case 2 - Empty Arrays:");
        String[] result = findUniqueStudents(new String[0], new String[0]);
        System.out.println("Result length: " + result.length);
    }

    // Test method for no common students
    private static void testNoCommonStudents() {
        System.out.println("\nTest Case 3 - No Common Students:");
        String[] class1 = {"a", "b"};
        String[] class2 = {"c", "d"};
        printArray(findUniqueStudents(class1, class2));
    }

    // Test method for all common students
    private static void testAllCommonStudents() {
        System.out.println("\nTest Case 4 - All Common Students:");
        String[] class1 = {"a", "b"};
        String[] class2 = {"a", "b"};
        printArray(findUniqueStudents(class1, class2));
    }

    // Test method for large data set
    private static void testLargeDataSet() {
        System.out.println("\nTest Case 5 - Large Data Set:");
        String[] class1 = new String[1000];
        String[] class2 = new String[1000];
        for (int i = 0; i < 1000; i++) {
            class1[i] = "student" + i;
            class2[i] = "student" + (i + 500); // Creates overlap
        }
        String[] result = findUniqueStudents(class1, class2);
        System.out.println("Number of unique students: " + result.length);
    }
}
