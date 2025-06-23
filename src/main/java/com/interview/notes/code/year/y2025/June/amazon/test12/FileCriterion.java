package com.interview.notes.code.year.y2025.June.amazon.test12;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single file-selection criterion. New criteria can be plugged in easily.
 */
public interface FileCriterion {
    /**
     * @param path the file to test
     * @return true if this file matches the criterion
     */
    boolean test(Path path) throws IOException;
}

/**
 * Criterion for selecting files above a given size.
 */
class SizeCriterion implements FileCriterion {
    private final long threshold;

    /**
     * @param thresholdInBytes minimum bytes a file must exceed
     */
    public SizeCriterion(long thresholdInBytes) {
        this.threshold = thresholdInBytes;
    }

    @Override
    public boolean test(Path path) throws IOException {
        return Files.isRegularFile(path) && Files.size(path) > threshold;
    }
}

/**
 * Walks a directory tree and filters by one or more criteria.
 */
  class FileFinder {
    private final List<FileCriterion> criteria = new ArrayList<>();
    private boolean followSymlinks = false;

    /**
     * Add any number of criteria via builder-style.
     */
    public FileFinder addCriterion(FileCriterion criterion) {
        criteria.add(criterion);
        return this;
    }

    /**
     * Optional: configure whether to follow symlinks.
     */
    public FileFinder followSymlinks(boolean follow) {
        this.followSymlinks = follow;
        return this;
    }

    /**
     * @param root starting directory
     * @return all matching file paths
     */
    public List<Path> find(Path root) throws IOException {
        List<Path> result = new ArrayList<>();
        // Choose link options based on configuration
        LinkOption[] linkOpts = followSymlinks
            ? new LinkOption[0]
            : new LinkOption[] { LinkOption.NOFOLLOW_LINKS };

        // Walk file tree
        // Build a filter that allows directories (for recursion) and files matching all criteria
DirectoryStream.Filter<Path> filter = entry -> {
    // Directories are always included so we can recurse into them
    if (Files.isDirectory(entry, linkOpts)) return true;
    // For files, apply all criteria; skip on exception
    for (FileCriterion c : criteria) {
        try {
            if (!c.test(entry)) return false;
        } catch (IOException e) {
            return false;
        }
    }
    return true;
};
// Use custom filter when opening the directory
try (DirectoryStream<Path> ds = Files.newDirectoryStream(root, filter)) {
            for (Path entry : ds) {
                if (Files.isDirectory(entry, linkOpts)) {
                    result.addAll(find(entry));  // recurse
                } else {
                    boolean allMatch = true;
                    for (FileCriterion c : criteria) {
                        if (!c.test(entry)) {
                            allMatch = false;
                            break;
                        }
                    }
                    if (allMatch) {
                        result.add(entry);
                    }
                }
            }
        }
        return result;
    }
}

/**
 * Application entry point: parses args and invokes FileFinder.
 */
  class App {
    private static final long DEFAULT_THRESHOLD = 5L * 1024 * 1024;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java -jar filefinder.jar <dir> [thresholdBytes]");
            System.exit(1);
        }

        Path root = Paths.get(args[0]);
        long threshold = args.length > 1
            ? Long.parseLong(args[1])
            : DEFAULT_THRESHOLD;

        FileFinder finder = new FileFinder()
            .addCriterion(new SizeCriterion(threshold));

        try {
            List<Path> largeFiles = finder.find(root);
            largeFiles.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("Error during search: " + e.getMessage());
        }
    }
}
