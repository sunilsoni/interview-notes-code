package com.interview.notes.code.year.y2025.may.amazon.test10;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileFinderDemo {

    /**
     * Usage (example):
     *
     *   > javac SearchCriteria.java SizeGreaterThanCriteria.java ExtensionCriteria.java FileFinder.java FileFinderDemo.java
     *   > java FileFinderDemo /path/to/your/root_directory
     *
     * Replace "/path/to/your/root_directory" with the directory you want to search.
     */
    public static void main(String[] args) {
        // 1) Check command-line arguments:
        if (args.length < 1) {
            System.err.println("ERROR: Please provide the root directory to search as the first argument.");
            System.err.println("Usage: java FileFinderDemo <root_directory_path>");
            System.exit(1);
        }

        // 2) Interpret the first argument as a Path:
        Path rootDir = Paths.get(args[0]);

        // 3) === Use Case #1: Find all files > 5 MB ===
        //    We create a SizeGreaterThanCriteria with threshold = 5 * 1024 * 1024 bytes (i.e. 5 MB).
        long fiveMB = 5L * 1024L * 1024L;
        SizeGreaterThanCriteria sizeCriterion = new SizeGreaterThanCriteria(fiveMB);

        System.out.println(">>>> Searching for files larger than 5 MB under: " + rootDir);
        List<Path> largeFiles;
        try {
            largeFiles = FileFinder.find(rootDir, sizeCriterion);
            if (largeFiles.isEmpty()) {
                System.out.println("    (No files found that are > 5 MB.)");
            } else {
                for (Path p : largeFiles) {
                    System.out.println("    " + p + "  (size > 5 MB)");
                }
            }
        } catch (Exception e) {
            // Could be IllegalArgumentException (invalid directory) or UncheckedIOException (I/O error).
            System.err.println("    [ERROR during size‐based search] " + e.getMessage());
        }

        System.out.println();

        // 4) === Use Case #2: Find all .xml files ===
        //    We create an ExtensionCriteria for ".xml" (case-insensitive).
        ExtensionCriteria xmlCriterion = new ExtensionCriteria(".xml");

        System.out.println(">>>> Searching for all .xml files under: " + rootDir);
        List<Path> xmlFiles;
        try {
            xmlFiles = FileFinder.find(rootDir, xmlCriterion);
            if (xmlFiles.isEmpty()) {
                System.out.println("    (No .xml files found.)");
            } else {
                for (Path p : xmlFiles) {
                    System.out.println("    " + p + "  (.xml file)");
                }
            }
        } catch (Exception e) {
            System.err.println("    [ERROR during extension‐based search] " + e.getMessage());
        }

        System.out.println();

        // 5) === (Optional) Combining Criteria ===
        // You can also “AND” multiple criteria. For example, files larger than 5 MB AND ending in “.xml”:
        System.out.println(">>>> Searching for files larger than 5 MB AND ending with .xml:");
        try {
            List<Path> bigXmlFiles = FileFinder.find(rootDir, sizeCriterion, xmlCriterion);
            if (bigXmlFiles.isEmpty()) {
                System.out.println("    (No files found matching BOTH criteria.)");
            } else {
                for (Path p : bigXmlFiles) {
                    System.out.println("    " + p + "  (>5 MB and .xml)");
                }
            }
        } catch (Exception e) {
            System.err.println("    [ERROR during combined criteria search] " + e.getMessage());
        }

        System.out.println();
        System.out.println("Demo complete.");
    }
}