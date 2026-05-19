package com.interview.notes.code.year.y2026.may.common.test9;

import java.io.IOException; // Required for handling file system read/write errors.
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes; // Needed to read the 'mtime' (modification time) and file type.
import java.nio.file.attribute.FileTime; // Used to mock timestamps for our test cases.
import java.util.ArrayDeque; // A highly efficient queue implementation for our custom BFS traversal.
import java.util.ArrayList; // Used to store the final list of matching files.
import java.util.List; // Interface for our return type.

public class CustomFileScanner { // Main class containing our custom algorithm and tests.

    // Custom traversal method matching the interview constraints (no Files.walk).
    public static List<Path> getModifiedFiles(Path root, long lastBuildTime) throws IOException { // Accepts root path and timestamp.
        var results = new ArrayList<Path>(); // Initializes the list to hold files that pass our time check.
        var queue = new ArrayDeque<Path>(); // Initializes a Queue to manage nested folders without recursion.
        
        queue.add(root); // Adds the starting root directory to the queue to kick off the loop.

        while (!queue.isEmpty()) { // Continues looping as long as there are folders left to process.
            var currentDir = queue.poll(); // Removes and retrieves the next folder from the front of the queue.

            // DirectoryStream loads files lazily one-by-one, handling "Large number of files" without crashing memory.
            try (var stream = Files.newDirectoryStream(currentDir)) { // Opens the folder to read its contents.
                for (var entry : stream) { // Loops through every single item (file or folder) inside currentDir.
                    
                    var attrs = Files.readAttributes(entry, BasicFileAttributes.class); // Fetches OS-level details like type and mtime.
                    
                    if (attrs.isDirectory()) { // Checks if the current item is a nested folder.
                        queue.add(entry); // Adds the nested folder to the queue so we search inside it later.
                    } 
                    else if (attrs.isRegularFile()) { // Ensures we ONLY process files, ignoring directories entirely.
                        if (attrs.lastModifiedTime().toMillis() > lastBuildTime) { // Compares file's mtime against our last build time.
                            results.add(entry); // If the file is newer, add it to our final result list.
                        } // Ends the time check condition.
                    } // Ends the file type check condition.
                } // Ends the loop for the current directory's contents.
            } catch (AccessDeniedException e) { // Catches permissions issues gracefully so the program doesn't crash.
                // Silently skip folders the OS won't let us read, continuing the search elsewhere. // Logic explanation.
            } // Ends the try-catch block for the directory stream.
        } // Ends the while loop when all nested folders are fully processed.
        
        return results; // Returns the completely populated list of modified files.
    } // Ends the custom traversal method.

    public static void main(String[] args) throws Exception { // Entry point for testing the logic.
        System.out.println("Starting Custom BFS Traversal Tests..."); // Console marker for test start.
        testNestedAndModifiedLogic(); // Executes the test case handling deep nesting and mtime checks.
    } // Ends the main method.

    private static void testNestedAndModifiedLogic() throws Exception { // Simulates the disk environment described in the transcript.
        var tempRoot = Files.createTempDirectory("projectRoot"); // Creates a fake project root directory.
        var nestedDir = Files.createDirectory(tempRoot.resolve("src")); // Creates a nested folder to prove deep traversal works.
        
        var oldFile = Files.createTempFile(tempRoot, "old", ".java"); // Creates a file in the root intended to be old.
        var newNestedFile = Files.createTempFile(nestedDir, "new", ".java"); // Creates a nested file intended to be new.
        
        var buildTime = System.currentTimeMillis() - 5000; // Simulates a build time that happened 5 seconds ago.
        
        // Manually artificially age the 'old' file so its mtime is BEFORE the build time.
        Files.setLastModifiedTime(oldFile, FileTime.fromMillis(buildTime - 10000)); // Logic explanation.
        // 'newNestedFile' keeps its current timestamp, meaning its mtime is AFTER the build time. // Logic explanation.

        var modifiedFiles = getModifiedFiles(tempRoot, buildTime); // Runs our custom algorithm against the simulated disk.

        // We expect exactly 1 file (the new one inside the nested folder), and it must NOT include the 'src' directory itself.
        if (modifiedFiles.size() == 1 && modifiedFiles.contains(newNestedFile)) { // Validates the output matches expectations.
            System.out.println("Test Nested/Modified Logic: PASS"); // Prints success if exactly the correct file is found.
        } else { // Fallback if the algorithm grabbed the wrong files or included directories.
            System.out.println("Test Nested/Modified Logic: FAIL"); // Prints failure marker.
        } // Ends validation block.
    } // Ends the test method.
} // Ends the class.