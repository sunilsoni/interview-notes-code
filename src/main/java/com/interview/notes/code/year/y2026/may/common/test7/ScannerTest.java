package com.interview.notes.code.year.y2026.may.common.test7;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScannerTest { // Test class

    static List<String> findChangedFiles(FolderNode root, Instant buildTime) { // Method under test
        List<String> result = new ArrayList<>(); // Changed files list
        Queue<QueueItem> queue = new ArrayDeque<>(); // BFS queue
        queue.add(new QueueItem(root, root.name())); // Add root

        while (!queue.isEmpty()) { // Process until queue empty
            QueueItem item = queue.poll(); // Get next item
            Node node = item.node(); // Current node
            String path = item.path(); // Current path

            if (node instanceof FileNode file) { // If file
                if (file.modifiedTime().isAfter(buildTime)) result.add(path); // Add if changed
            } else { // If folder
                FolderNode folder = (FolderNode) node; // Cast folder
                folder.children().forEach(child -> // Loop children
                        queue.add(new QueueItem(child, path + "/" + child.name())) // Add child
                );
            }
        }

        return result; // Return changed files
    }

    static void check(String name, List<String> actual, List<String> expected) { // PASS/FAIL helper
        var a = actual.stream().sorted().toList(); // Sort actual
        var e = expected.stream().sorted().toList(); // Sort expected
        System.out.println((a.equals(e) ? "PASS: " : "FAIL: ") + name); // Print result
        if (!a.equals(e)) { // Print only if failed
            System.out.println("Expected: " + e); // Expected
            System.out.println("Actual  : " + a); // Actual
        }
    }

    public static void main(String[] args) { // Start tests

        Instant t = Instant.parse("2026-05-17T12:00:00Z"); // Build time

        check( // Test 1
                "before, after, exact timestamp",
                findChangedFiles(new FolderNode("p", List.of(
                        new FileNode("old.txt", t.minusSeconds(1)),
                        new FileNode("new.txt", t.plusSeconds(1)),
                        new FileNode("same.txt", t)
                )), t),
                List.of("p/new.txt")
        );

        check( // Test 2
                "deep nested folder",
                findChangedFiles(new FolderNode("p", List.of(
                        new FolderNode("a", List.of(
                                new FolderNode("b", List.of(
                                        new FolderNode("c", List.of(
                                                new FileNode("deep.txt", t.plusSeconds(5))
                                        ))
                                ))
                        ))
                )), t),
                List.of("p/a/b/c/deep.txt")
        );

        check( // Test 3
                "empty folder",
                findChangedFiles(new FolderNode("empty", List.of()), t),
                List.of()
        );

        check( // Test 4
                "only folders no files",
                findChangedFiles(new FolderNode("p", List.of(
                        new FolderNode("src", List.of()),
                        new FolderNode("docs", List.of())
                )), t),
                List.of()
        );

        check( // Test 5
                "only old files",
                findChangedFiles(new FolderNode("p", List.of(
                        new FileNode("a.txt", t.minusSeconds(10)),
                        new FolderNode("src", List.of(
                                new FileNode("b.txt", t.minusSeconds(20))
                        ))
                )), t),
                List.of()
        );

        List<Node> files = IntStream.range(0, 10_000) // Create many files
                .mapToObj(i -> new FileNode(
                        "f" + i + ".txt",
                        i % 2 == 0 ? t.plusSeconds(1) : t.minusSeconds(1)
                ))
                .collect(Collectors.toList());

        List<String> expected = IntStream.range(0, 10_000) // Expected changed files
                .filter(i -> i % 2 == 0)
                .mapToObj(i -> "big/f" + i + ".txt")
                .toList();

        check( // Test 6
                "large folder 10000 files",
                findChangedFiles(new FolderNode("big", files), t),
                expected
        );
    }

    sealed interface Node permits FileNode, FolderNode { // Common type
        String name(); // Common name method
    }

    record FileNode(String name, Instant modifiedTime) implements Node { // File node
    }

    record FolderNode(String name, List<Node> children) implements Node { // Folder node
    }

    record QueueItem(Node node, String path) { // Queue data
    }
}