package com.interview.notes.code.year.y2025.november.Amazon.test2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * DuplicateFileContentFinder:
 * Detects duplicate files by content (not by name or path) using Java 8 Streams.
 * Strategy: group by size -> group by SHA-256 -> for each group keep the oldest file and mark others as duplicates.
 */
public final class DuplicateFileContentFinder {       // Define a final class to prevent subclassing; it only holds utilities

    private static final int BUFFER_SIZE = 1 << 20;   // Define a 1MB buffer for hashing; balances memory and I/O performance

    private DuplicateFileContentFinder() {            // Private constructor to prevent instantiation (utility class)
        // no-op
    }

    // ---- Public API ----

    /**
     * Walk a root path and collect all regular, readable files under it.
     */
    public static List<Path> collectAllFiles(Path root) {  // Public method to list all files under a directory tree
        try (Stream<Path> s = Files.walk(root)) {          // Walk the tree lazily; try-with-resources closes the stream
            return s.filter(Files::isRegularFile)          // Keep only regular files (skip dirs, symlinks if not regular)
                    .filter(Files::isReadable)             // Keep only readable files to avoid permission issues
                    .collect(Collectors.toList());         // Collect to a list for further processing
        } catch (IOException e) {                          // Handle walking errors (e.g., permission denied)
            throw new RuntimeException("Failed to walk: " + root, e); // Convert to unchecked to simplify callers
        }
    }

    /**
     * Find duplicate groups by content. The map key is SHA-256 hex; value is list of paths sharing the same content.
     * Only groups with more than one file are returned.
     */
    public static Map<String, List<Path>> findDuplicateContentGroups(List<Path> files) {  // Core method to build content groups
        // First group by file size to avoid hashing unique sizes
        Map<Long, List<Path>> bySize = files.stream()                    // Stream the input file list
                .collect(Collectors.groupingBy(DuplicateFileContentFinder::safeFileSize)); // Group by size (with safe wrapper)

        // From size groups, keep only those with more than one file; flatten to a stream of candidates
        List<Path> candidates = bySize.entrySet().stream()               // Stream entries of size -> files
                .filter(e -> e.getKey() >= 0 && e.getValue().size() > 1) // Ignore unknown size (-1) and groups of size 1
                .flatMap(e -> e.getValue().stream())                     // Flatten file lists into a single stream
                .collect(Collectors.toList());                           // Collect to a list for the hashing phase

        // Group candidates by SHA-256; only duplicate groups (count > 1) are returned
        Map<String, List<Path>> byHash = candidates.stream()             // Stream files that share size with at least one other file
                .collect(Collectors.groupingBy(DuplicateFileContentFinder::sha256Hex)); // Group by content hash

        // Filter to keep only true duplicates (two or more files per hash)
        return byHash.entrySet().stream()                                // Stream hash -> files
                .filter(e -> e.getValue().size() > 1)                    // Keep groups with duplicates
                .collect(Collectors.toMap(                               
                        Map.Entry::getKey,                                // Key stays the hash
                        Map.Entry::getValue,                              // Value stays the list of files
                        (a, b) -> a,                                      // Merge function (not used)
                        LinkedHashMap::new));                             // Preserve iteration order (handy for stable output)
    }

    /**
     * For each duplicate group, keep one "original" (oldest by last-modified time) and mark the rest as duplicates to delete.
     */
    public static List<Path> chooseFilesToDelete(Map<String, List<Path>> duplicateGroups) { // Decide which files are extra copies
        List<Path> toDelete = new ArrayList<>();                        // Prepare a list to collect files that should be removed
        for (List<Path> group : duplicateGroups.values()) {             // Loop through each content-equal group
            group.sort(Comparator                                     // Sort files so the first one becomes the keeper
                    .comparing(DuplicateFileContentFinder::safeLastModified) // Use last-modified time (oldest first)
                    .thenComparing(Path::toString));                    // Tiebreaker by path string for determinism
            for (int i = 1; i < group.size(); i++) {                    // From index 1 onward, everything is a duplicate
                toDelete.add(group.get(i));                             // Add duplicates to the delete list
            }
        }
        return toDelete;                                                // Return the collected duplicates
    }

    /**
     * For each duplicate group, pick a single representative (the first after sorting by time/path).
     * This supports the alternative requirement: "Output: f2, f3" (one per duplicated content).
     */
    public static List<Path> chooseOneRepresentativePerGroup(Map<String, List<Path>> duplicateGroups) { // Alternative output
        return duplicateGroups.values().stream()                        // Stream each group of same-content files
                .map(list -> list.stream()                              // Stream within the group
                        .sorted(Comparator                              
                                .comparing(DuplicateFileContentFinder::safeLastModified) // Sort by oldest first
                                .thenComparing(Path::toString))          // Then by path for stability
                        .findFirst().get())                              // Take the first as representative
                .collect(Collectors.toList());                           // Collect all chosen representatives
    }

    // ---- Internal helpers ----

    /**
     * Safe file size getter that returns -1 on error.
     */
    private static long safeFileSize(Path p) {                           // Wrapper to handle IO in size fetching
        try {                                                            // Try block to catch IO exceptions
            return Files.size(p);                                        // Get file size in bytes
        } catch (IOException e) {                                        // If size cannot be read
            return -1L;                                                  // Return sentinel value to skip later
        }
    }

    /**
     * Safe last-modified time; uses epoch 0 on error so such files sort first (conservative keep).
     */
    private static long safeLastModified(Path p) {                       // Wrapper for last modified time
        try {                                                            // Try to read file attributes
            BasicFileAttributes a = Files.readAttributes(p, BasicFileAttributes.class); // Fetch basic attributes
            return a.lastModifiedTime().toMillis();                      // Return last modified time in millis
        } catch (IOException e) {                                        // If attributes cannot be read
            return 0L;                                                   // Default to 0 to bias towards keeping this file
        }
    }

    /**
     * Compute SHA-256 hex of a file. On error, use a unique per-path fallback to avoid false grouping.
     */
    private static String sha256Hex(Path p) {                            // Method to compute content hash
        try {                                                            // Try hashing
            MessageDigest md = MessageDigest.getInstance("SHA-256");     // Create SHA-256 digest
            byte[] buffer = new byte[BUFFER_SIZE];                       // Allocate buffer once
            try (InputStream in = Files.newInputStream(p)) {             // Open a stream to the file
                int read;                                                // Variable to hold bytes read
                while ((read = in.read(buffer)) != -1) {                 // Read until EOF
                    md.update(buffer, 0, read);                          // Feed read bytes into the digest
                }
            }
            byte[] digest = md.digest();                                 // Finalize the digest
            return toHex(digest);                                        // Convert bytes to hex string
        } catch (NoSuchAlgorithmException e) {                           // If SHA-256 is somehow unavailable
            throw new RuntimeException("SHA-256 unsupported", e);        // Fail hard; this should never happen in practice
        } catch (IOException e) {                                        // If file cannot be read
            // Fallback: include path + random to avoid grouping unreadable files together
            return "IOERR:" + p + ":" + UUID.randomUUID();   // Ensure unreadable files never collide as duplicates
        }
    }

    /**
     * Utility: convert byte[] to lowercase hex string.
     */
    private static String toHex(byte[] bytes) {                          // Helper to format hash bytes as hex
        StringBuilder sb = new StringBuilder(bytes.length * 2);          // Pre-size string builder
        for (byte b : bytes) {                                           // For each byte
            sb.append(String.format("%02x", b));                         // Append two hex digits
        }
        return sb.toString();                                            // Return the full hex string
    }

    // ---- Simple test harness with PASS/FAIL ----

    public static void main(String[] args) throws Exception {            // Entry point for manual testing
        // Create a temp root so tests do not touch real files
        Path root = Files.createTempDirectory("dup-finder-demo");        // Make a temp directory for all test files
        // Ensure cleanup happens on exit (best-effort)
        root.toFile().deleteOnExit();                                    // Ask JVM to delete the temp root later

        // 1) Simple sample like the prompt: f1, f2, f3, f4(copy of f2), f5(copy of f3)
        Path case1 = Files.createDirectory(root.resolve("case1"));       // Make a folder for case 1
        // Originals
        Path f1 = write(case1.resolve("f1.txt"), "alpha");               // Create f1 with content "alpha"
        Path f2 = write(case1.resolve("f2.txt"), "bravo");               // Create f2 with content "bravo"
        Path f3 = write(case1.resolve("f3.txt"), "charlie");             // Create f3 with content "charlie"
        // Copies placed elsewhere with different names
        Path nested = Files.createDirectories(case1.resolve("nested"));  // Create a nested directory
        Path f4 = write(nested.resolve("renamed_x.txt"), "bravo");       // f4 is a copy of f2 by content
        Path f5 = write(case1.resolve("moved_again.bin"), "charlie");    // f5 is a copy of f3 by content

        // Run detection
        List<Path> files1 = collectAllFiles(case1);                      // Collect all files under case1
        Map<String, List<Path>> groups1 = findDuplicateContentGroups(files1); // Build duplicate groups
        List<Path> toDelete1 = normalize(chooseFilesToDelete(groups1));  // Normalize list for stable comparison
        // Expect copies to delete are f4 and f5 (since f2, f3 are older than their copies)
        Set<String> expect1 = setOf(f4, f5);                             // Expected set as strings

        printResult("Case 1 (copies to delete)", expect1, setOf(toDelete1)); // Print PASS/FAIL for case 1

        // Also test the alternative output: one representative per duplicated group (e.g., f2, f3)
        List<Path> reps1 = normalize(chooseOneRepresentativePerGroup(groups1)); // Representatives list
        Set<String> expectReps1 = setOf(f2, f3);                         // We expect the originals here
        printResult("Case 1 (representatives)", expectReps1, setOf(reps1)); // PASS/FAIL for representatives

        // 2) Edge case: no duplicates at all
        Path case2 = Files.createDirectory(root.resolve("case2"));       // Folder for case 2
        write(case2.resolve("a.txt"), "one");                             // Unique content
        write(case2.resolve("b.txt"), "two");                             // Unique content
        write(case2.resolve("c.txt"), "three");                           // Unique content
        List<Path> files2 = collectAllFiles(case2);                      // Gather files
        Map<String, List<Path>> groups2 = findDuplicateContentGroups(files2); // Find groups
        List<Path> toDelete2 = chooseFilesToDelete(groups2);             // Choose duplicates
        printResult("Case 2 (no duplicates)", Collections.emptySet(), setOf(toDelete2)); // Expect empty set

        // 3) Large-data case: thousands of small files with controlled duplicates
        Path case3 = Files.createDirectory(root.resolve("case3"));       // Folder for case 3
        int uniqueSeeds = 500;                                           // Number of unique base contents
        int copiesPerSeed = 5;                                           // How many copies per base (total files = seeds * copies)
        // Build big set quickly
        List<Path> created = new ArrayList<>();                          // Store created paths for optional checks
        for (int s = 0; s < uniqueSeeds; s++) {                          // Loop over seed contents
            String content = randomAscii(64) + "|" + s;                  // Stable-ish random payload per seed
            // Create one "original"
            created.add(write(case3.resolve("seed_" + s + "_orig.txt"), content)); // Write original file
            // Create additional copies with different names/places
            for (int c = 1; c < copiesPerSeed; c++) {                    // For each extra copy
                Path dir = Files.createDirectories(case3.resolve("bucket_" + (s % 10))); // Spread files across directories
                created.add(write(dir.resolve("seed_" + s + "_copy_" + c + ".dat"), content)); // Write copy
            }
        }
        // Detect duplicates at scale
        long t0 = System.currentTimeMillis();                            // Start timer
        List<Path> files3 = collectAllFiles(case3);                      // Collect all generated files
        Map<String, List<Path>> groups3 = findDuplicateContentGroups(files3); // Build duplicate groups
        List<Path> toDelete3 = chooseFilesToDelete(groups3);             // Choose duplicates
        long t1 = System.currentTimeMillis();                            // Stop timer

        // In each group of N=copiesPerSeed, we expect N-1 files to be deletable
        long expectedDeleteCount = (long) uniqueSeeds * (copiesPerSeed - 1); // Compute expected number of duplicates
        boolean passLarge = toDelete3.size() == expectedDeleteCount;     // Check size only (content correctness implied by grouping)
        System.out.println(label("Case 3 (large data)") + (passLarge ? "PASS" : "FAIL") +
                " | duplicates=" + toDelete3.size() +
                " expected=" + expectedDeleteCount +
                " timeMs=" + (t1 - t0));                                 // Print summary with time

        // Done. You can browse the temp directory if needed for inspection.
        System.out.println("Temp root: " + root.toAbsolutePath());       // Show where files were created
    }

    // ---- Small utilities used only by the test harness ----

    private static Path write(Path p, String content) {                  // Helper to write a small text file
        try {                                                            // Try writing
            Files.write(p, content.getBytes(StandardCharsets.UTF_8));                   // Write bytes in UTF-8
            p.toFile().deleteOnExit();                                   // Best-effort cleanup on JVM exit
            return p;                                                    // Return the path for chaining
        } catch (IOException e) {                                        // If write fails
            throw new RuntimeException("Write failed: " + p, e);         // Bubble up as unchecked
        }
    }

    private static String randomAscii(int len) {                         // Generate simple ASCII string for test data
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Allowed characters
        StringBuilder sb = new StringBuilder(len);                       // Pre-size builder
        ThreadLocalRandom r = ThreadLocalRandom.current();               // Fast RNG
        for (int i = 0; i < len; i++) {                                  // Build character by character
            sb.append(chars.charAt(r.nextInt(chars.length())));          // Append random character
        }
        return sb.toString();                                            // Return the random string
    }

    private static List<Path> normalize(List<Path> list) {               // Normalize paths to a stable order for comparison
        return list.stream()                                             // Stream the list
                .sorted(Comparator.comparing(Path::toString))            // Sort by path string
                .collect(Collectors.toList());                           // Return as list
    }

    private static Set<String> setOf(Path... paths) {                    // Build a set of String paths from varargs
        return Arrays.stream(paths)                                      // Stream over arguments
                .map(p -> p.toAbsolutePath().toString())                 // Convert to absolute strings
                .collect(Collectors.toCollection(LinkedHashSet::new));   // Collect into a linked set (stable order)
    }

    private static Set<String> setOf(List<Path> paths) {                 // Build a set of String paths from list
        return paths.stream()                                            // Stream list
                .map(p -> p.toAbsolutePath().toString())                 // Convert to absolute strings
                .collect(Collectors.toCollection(LinkedHashSet::new));   // Collect into a linked set
    }

    private static void printResult(String name, Set<String> expected, Set<String> actual) { // Print PASS/FAIL with details
        boolean pass = expected.equals(actual);                          // Compare sets for equality
        System.out.println(label(name) + (pass ? "PASS" : "FAIL"));      // Print status
        if (!pass) {                                                     // If failed, show diffs
            Set<String> missing = new LinkedHashSet<>(expected);         // Start with expected
            missing.removeAll(actual);                                   // Remove what we got
            Set<String> extra = new LinkedHashSet<>(actual);             // Start with actual
            extra.removeAll(expected);                                   // Remove what we expected
            System.out.println("  missing: " + missing);                 // Show missing
            System.out.println("  extra  : " + extra);                   // Show extra
        }
    }

    private static String label(String name) {                           // Format a case label for readability
        return String.format("[%-28s] ", name);                          // Left-pad the name to align output columns
    }
}
