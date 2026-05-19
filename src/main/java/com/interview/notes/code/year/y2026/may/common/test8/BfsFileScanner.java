package com.interview.notes.code.year.y2026.may.common.test8;

import java.io.IOException; // Required to handle file system access errors gracefully.
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes; // Allows reading the 'mtime' and file type in one fast operation.
import java.nio.file.attribute.FileTime; // Used specifically to manipulate timestamps for our testing logic.
import java.util.ArrayDeque; // A fast, non-synchronized queue ideal for our custom BFS algorithm.
import java.util.ArrayList; // A dynamic array to store and return the final list of modified files.
import java.util.List; // The standard interface for our return collection.

public class BfsFileScanner { // The main class housing our custom algorithm and the test runner.

    public static List<Path> findModifiedFiles(Path root, long buildTime) throws IOException { // Core method taking the root folder and previous build timestamp.
        var results = new ArrayList<Path>(); // Initializes the list that will hold our final output of modified files.
        var queue = new ArrayDeque<Path>(); // Initializes the queue to manage folders, avoiding recursive StackOverflowErrors.
        
        queue.add(root); // Adds the initial root directory to the queue to start the BFS loop.

        while (!queue.isEmpty()) { // Loops continuously as long as there are unexplored folders in our queue.
            var currentDir = queue.poll(); // Removes and grabs the next folder from the front of the queue to process.

            try (var stream = Files.newDirectoryStream(currentDir)) { // Opens a lazy stream of the folder's contents, preventing OutOfMemoryErrors.
                for (var entry : stream) { // Iterates through each item (file or folder) strictly inside the current directory.
                    
                    var attrs = Files.readAttributes(entry, BasicFileAttributes.class); // Reads OS metadata (type and mtime) efficiently.
                    
                    if (attrs.isDirectory()) { // Checks if the current item is a nested folder.
                        queue.add(entry); // Adds the nested folder to the queue so we explore it later (BFS logic).
                    } else if (attrs.isRegularFile() && attrs.lastModifiedTime().toMillis() > buildTime) { // Checks if it's a file AND its mtime is newer than the build time.
                        results.add(entry); // Adds the modified file to our final results list.
                    } // Ends the file checking logic.
                    
                } // Ends the iteration over the current folder's contents.
            } catch (AccessDeniedException ignore) { // Catches permission errors if the OS blocks access to a specific folder.
                // We silently ignore locked folders to ensure the algorithm safely continues scanning the rest of the disk. // Explanation for the empty catch block.
            } // Ends the try-catch block for the directory stream.
        } // Ends the BFS while loop once all reachable folders are fully processed.

        return results; // Returns the final collected list of modified files.
    } // Ends the core algorithm method.

    public static void main(String[] args) throws IOException { // Entry point serving as our custom test engine.
        System.out.println("Starting Custom BFS Traversal Tests..."); // Indicates the beginning of test execution in the console.
        testBfsLogic(); // Triggers the standard functionality pass/fail test for nested folders.
        testLargeDataInput(); // Triggers the high-volume data performance test to verify memory safety.
    } // Ends the main method.

    private static void testBfsLogic() throws IOException { // Tests standard nested folder traversal and mtime detection.
        var tempRoot = Files.createTempDirectory("bfsRoot"); // Creates an isolated temporary directory for clean testing.
        var nestedDir = Files.createDirectory(tempRoot.resolve("src")); // Creates a nested folder to prove deep traversal works.
        var oldFile = Files.createTempFile(tempRoot, "old", ".java"); // Creates a test file intended to represent old work.
        var newFile = Files.createTempFile(nestedDir, "new", ".java"); // Creates a nested test file intended to represent new work.
        
        var buildTime = System.currentTimeMillis() - 5000; // Simulates a build time that happened 5 seconds ago.
        Files.setLastModifiedTime(oldFile, FileTime.fromMillis(buildTime - 10000)); // Artificially ages the old file so its mtime is BEFORE the build.
        // newFile keeps its current timestamp (created milliseconds ago), passing the build time check. // Logic explanation.

        var results = findModifiedFiles(tempRoot, buildTime); // Executes our BFS logic to fetch files newer than the build time.

        if (results.size() == 1 && results.contains(newFile)) { // Validates that exactly one file was found, and it's the correct nested one.
            System.out.println("Test BFS Logic (Nested & MTime): PASS"); // Prints success condition.
        } else { // Fallback if the logic caught the wrong files or included directories.
            System.out.println("Test BFS Logic: FAIL"); // Prints failure condition.
        } // Ends the validation block.
    } // Ends the basic test method.

    private static void testLargeDataInput() throws IOException { // Validates performance and accuracy against large inputs to ensure no OutOfMemoryError.
        var tempRoot = Files.createTempDirectory("largeBfsDir"); // Creates a fresh directory for bulk data simulation.
        var buildTime = System.currentTimeMillis() - 5000; // Simulates a build time that happened 5 seconds ago.
        var pastTime = FileTime.fromMillis(buildTime - 10000); // Pre-calculates an old timestamp to apply in the loop.

        for (int i = 0; i < 10000; i++) { // Loops 10,000 times to generate a dense directory tree.
            var f = Files.createTempFile(tempRoot, "bulk", ".txt"); // Creates a new physical file on the disk.
            if (i % 2 == 0) Files.setLastModifiedTime(f, pastTime); // Alters every even-numbered file to be older than the build time.
        } // Ends the mass file generation loop.

        var results = findModifiedFiles(tempRoot, buildTime); // Executes the search against the 10,000 files using DirectoryStream.

        if (results.size() == 5000) { // Validates exactly half the files (the unaltered odd-numbered ones) were successfully caught.
            System.out.println("Test Large Data Input (10,000 files via Stream): PASS"); // Prints success for the load test.
        } else { // Fallback if the count mismatch occurs.
            System.out.println("Test Large Data Input: FAIL (Found " + results.size() + ")"); // Prints failure with debugging context.
        } // Ends the validation block.
    } // Ends the large data test method.
} // Ends the Java class.