package com.interview.notes.code.months.nov24.amazon.test9;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Utility class for searching files based on various criteria.
 */
public class FileSearchUtility {

    /**
     * Encapsulates search criteria for file searching.
     */
    public static class FileSearchCriteria {
        private String fileName;
        private Long minSize; // in bytes
        private Long maxSize; // in bytes
        private Date modifiedAfter;
        private Date modifiedBefore;

        // Constructors, getters, and setters

        public FileSearchCriteria(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }

        public Long getMinSize() {
            return minSize;
        }

        public void setMinSize(Long minSize) {
            this.minSize = minSize;
        }

        public Long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(Long maxSize) {
            this.maxSize = maxSize;
        }

        public Date getModifiedAfter() {
            return modifiedAfter;
        }

        public void setModifiedAfter(Date modifiedAfter) {
            this.modifiedAfter = modifiedAfter;
        }

        public Date getModifiedBefore() {
            return modifiedBefore;
        }

        public void setModifiedBefore(Date modifiedBefore) {
            this.modifiedBefore = modifiedBefore;
        }
    }

    /**
     * Searches for files matching the given criteria within the specified directory and its subdirectories.
     *
     * @param directory The starting directory for the search.
     * @param criteria  The search criteria.
     * @return A list of full file paths that match the criteria.
     */
    public List<String> searchFiles(File directory, FileSearchCriteria criteria) {
        List<String> result = new ArrayList<>();
        if (directory == null || !directory.exists()) {
            return result;
        }
        searchFilesRecursive(directory, criteria, result);
        return result;
    }

    /**
     * Recursive helper method to perform the file search.
     *
     * @param directory The current directory being searched.
     * @param criteria  The search criteria.
     * @param result    The list to accumulate matching file paths.
     */
    private void searchFilesRecursive(File directory, FileSearchCriteria criteria, List<String> result) {
        File[] filesAndDirs = directory.listFiles();
        if (filesAndDirs == null) {
            return;
        }

        for (File entry : filesAndDirs) {
            if (entry.isDirectory()) {
                searchFilesRecursive(entry, criteria, result);
            } else {
                if (matchesCriteria(entry, criteria)) {
                    result.add(entry.getAbsolutePath());
                }
            }
        }
    }

    /**
     * Checks if a file matches the given search criteria.
     *
     * @param file     The file to check.
     * @param criteria The search criteria.
     * @return True if the file matches all criteria, false otherwise.
     */
    private boolean matchesCriteria(File file, FileSearchCriteria criteria) {
        // Check file name
        if (!file.getName().equals(criteria.getFileName())) {
            return false;
        }

        // Check file size
        if (criteria.getMinSize() != null && file.length() < criteria.getMinSize()) {
            return false;
        }
        if (criteria.getMaxSize() != null && file.length() > criteria.getMaxSize()) {
            return false;
        }

        // Check modification date
        if (criteria.getModifiedAfter() != null && new Date(file.lastModified()).before(criteria.getModifiedAfter())) {
            return false;
        }
        if (criteria.getModifiedBefore() != null && new Date(file.lastModified()).after(criteria.getModifiedBefore())) {
            return false;
        }

        return true;
    }

    /**
     * Main method to execute test cases.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        TestFileSearchUtility tester = new TestFileSearchUtility();
        tester.runTests();
    }
}

/**
 * Class to perform testing of FileSearchUtility without using JUnit.
 */
class TestFileSearchUtility {

    /**
     * Runs all test cases and outputs pass/fail results.
     */
    public void runTests() {
        FileSearchUtility utility = new FileSearchUtility();

        // Define test directories and files (Adjust paths as necessary)
        String testDirPath = "test_directory"; // Ensure this directory exists with test files
        File testDir = new File(testDirPath);

        // Create test search criteria
        FileSearchUtility.FileSearchCriteria criteria1 = new FileSearchUtility.FileSearchCriteria("example.txt");
        FileSearchUtility.FileSearchCriteria criteria2 = new FileSearchUtility.FileSearchCriteria("nonexistent.txt");
        FileSearchUtility.FileSearchCriteria criteria3 = new FileSearchUtility.FileSearchCriteria("largefile.dat");
        FileSearchUtility.FileSearchCriteria criteria4 = new FileSearchUtility.FileSearchCriteria("recentfile.txt");

        // Set additional criteria
        criteria3.setMinSize(1024L * 1024L); // Files larger than 1MB
        criteria4.setModifiedAfter(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000)); // Modified in the last day

        // List to hold test cases
        List<TestCase> testCases = new ArrayList<>();

        // Define test cases
        testCases.add(new TestCase("Test Case 1: Existing file without additional filters",
                utility.searchFiles(testDir, criteria1).size() > 0, true));

        testCases.add(new TestCase("Test Case 2: Non-existent file",
                utility.searchFiles(testDir, criteria2).isEmpty(), true));

        testCases.add(new TestCase("Test Case 3: Existing large file",
                utility.searchFiles(testDir, criteria3).size() > 0, true));

        testCases.add(new TestCase("Test Case 4: Recently modified file",
                utility.searchFiles(testDir, criteria4).size() > 0, true));

        testCases.add(new TestCase("Test Case 5: Empty directory",
                utility.searchFiles(new File("empty_directory"), criteria1).isEmpty(), true));

        testCases.add(new TestCase("Test Case 6: Large data input",
                performLargeDataTest(utility, testDir), true));

        // Execute test cases
        for (TestCase testCase : testCases) {
            if (testCase.expected == testCase.actual) {
                System.out.println(testCase.name + " - PASS");
            } else {
                System.out.println(testCase.name + " - FAIL");
            }
        }
    }

    /**
     * Performs a large data input test to ensure performance and correctness.
     *
     * @param utility The FileSearchUtility instance.
     * @param testDir The directory to perform the search in.
     * @return True if the test passes, false otherwise.
     */
    private boolean performLargeDataTest(FileSearchUtility utility, File testDir) {
        // Create a search criteria that matches multiple files
        FileSearchUtility.FileSearchCriteria largeDataCriteria = new FileSearchUtility.FileSearchCriteria("file_");

        // This example assumes that there are multiple files starting with "file_" in the test directory
        // Adjust as necessary based on your test environment
        List<String> results = utility.searchFiles(testDir, largeDataCriteria);
        return results.size() > 1000; // Expecting more than 1000 matches
    }

    /**
     * Inner class to represent a test case.
     */
    class TestCase {
        String name;
        boolean actual;
        boolean expected;

        TestCase(String name, boolean actual, boolean expected) {
            this.name = name;
            this.actual = actual;
            this.expected = expected;
        }
    }
}
