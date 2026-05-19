package com.interview.notes.code.year.y2026.may.common.test7;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BuildChangeScanner { // Main class

    static List<String> findChangedFiles(FolderNode root, Instant lastBuildTime) { // Main logic method
        List<String> result = new ArrayList<>(); // Stores changed file paths
        Queue<QueueItem> queue = new ArrayDeque<>(); // Queue for BFS scanning
        queue.add(new QueueItem(root, root.name())); // Start scan from root folder

        while (!queue.isEmpty()) { // Continue until all folders/files are checked
            QueueItem item = queue.poll(); // Take next item from queue
            Node node = item.node(); // Get current node
            String path = item.path(); // Get current full path

            if (node instanceof FileNode file) { // If current node is a file
                if (file.modifiedTime().isAfter(lastBuildTime)) { // Check if file changed after build
                    result.add(path); // Add changed file to result
                } // File time check ends
            } else { // Otherwise current node is a folder
                FolderNode folder = (FolderNode) node; // Convert node to folder
                folder.children().forEach(child -> // Read children one by one
                        queue.add(new QueueItem(child, path + "/" + child.name())) // Add child to queue
                ); // Child loop ends
            } // File/folder check ends
        } // BFS loop ends

        return result; // Return all changed files
    } // Method ends

    static void test(String testName, List<String> actual, List<String> expected) { // PASS/FAIL test helper
        var a = actual.stream().sorted().toList(); // Sort actual result
        var e = expected.stream().sorted().toList(); // Sort expected result
        System.out.println((a.equals(e) ? "PASS: " : "FAIL: ") + testName); // Print test result

        if (!a.equals(e)) { // Print details only if test fails
            System.out.println("Expected: " + e); // Show expected files
            System.out.println("Actual  : " + a); // Show actual files
        } // If ends
    } // Test method ends

    public static void main(String[] args) { // Program starts

        Instant buildTime = Instant.parse("2026-05-17T12:00:00Z"); // Last build timestamp

        FolderNode project = new FolderNode("project", List.of( // Root folder

                new FileNode("README.md", buildTime), // Same time, not changed

                new FolderNode("src", List.of( // First nested folder

                        new FileNode("Old.java", buildTime.minusSeconds(10)), // Old file, ignore

                        new FileNode("App.java", buildTime.plusSeconds(10)), // Changed file, include

                        new FolderNode("service", List.of( // Second nested folder

                                new FileNode("UserService.java", buildTime.plusSeconds(20)), // Changed nested file

                                new FileNode("RoleService.java", buildTime.minusSeconds(20)) // Old nested file

                        )) // service folder ends

                )), // src folder ends

                new FolderNode("docs", List.of( // Another folder

                        new FileNode("Design.md", buildTime.plusSeconds(30)) // Changed doc file

                )) // docs folder ends

        )); // project folder ends

        test( // Test normal nested folders
                "nested folders",
                findChangedFiles(project, buildTime),
                List.of(
                        "project/src/App.java",
                        "project/src/service/UserService.java",
                        "project/docs/Design.md"
                )
        ); // Test ends

        FolderNode emptyProject = new FolderNode("empty", List.of()); // Empty folder

        test( // Test empty folder
                "empty folder",
                findChangedFiles(emptyProject, buildTime),
                List.of()
        ); // Test ends

        FolderNode onlyOld = new FolderNode("oldProject", List.of( // Folder with only old files
                new FileNode("A.java", buildTime.minusSeconds(1)), // Old file
                new FolderNode("inner", List.of( // Nested folder
                        new FileNode("B.java", buildTime.minusSeconds(2)) // Old nested file
                )) // inner folder ends
        )); // oldProject ends

        test( // Test no changed files
                "only old files",
                findChangedFiles(onlyOld, buildTime),
                List.of()
        ); // Test ends

        List<Node> manyFiles = IntStream.range(0, 10_000) // Create 10,000 files
                .mapToObj(i -> new FileNode( // Convert each number to a file
                        "file" + i + ".txt", // File name
                        i % 2 == 0 ? buildTime.plusSeconds(1) : buildTime.minusSeconds(1) // Half changed
                )) // FileNode creation ends
                .collect(Collectors.toList()); // Collect as list

        FolderNode bigProject = new FolderNode("big", manyFiles); // Large folder

        List<String> expectedBig = IntStream.range(0, 10_000) // Create expected changed files
                .filter(i -> i % 2 == 0) // Even files are changed
                .mapToObj(i -> "big/file" + i + ".txt") // Build full path
                .toList(); // Convert to list

        test( // Large input test
                "large input 10000 files",
                findChangedFiles(bigProject, buildTime),
                expectedBig
        ); // Test ends
    } // Main ends

    sealed interface Node permits FileNode, FolderNode { // Common type for file and folder
        String name(); // Every node has a name
    } // Node ends

    record FileNode(String name, Instant modifiedTime) implements Node { // Custom file with modified time
    } // FileNode ends

    record FolderNode(String name, List<Node> children) implements Node { // Custom folder with children
    } // FolderNode ends

    record QueueItem(Node node, String path) { // Stores node with full path
    } // QueueItem ends
} // Class ends