package com.interview.notes.code.year.y2025.may.amazon.test10;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A utility class that walks a directory tree and returns all files matching
 * *all* the provided SearchCriteria (i.e. logical AND).
 */
public class FileFinder {

    /**
     * Walks the directory tree under 'startDir' and returns a list of all files
     * that match every one of the provided criteria.
     *
     * @param startDir the root directory from which to begin the search
     * @param criteria one or more SearchCriteria; a file must satisfy ALL of them
     * @return a List<Path> containing all matching files
     * @throws IllegalArgumentException if startDir is invalid or no criteria provided
     * @throws UncheckedIOException     if an I/O error occurs during traversal
     */
    public static List<Path> find(Path startDir, SearchCriteria... criteria) {
        // 1) Validate startDir parameter.
        if (startDir == null) {
            throw new IllegalArgumentException("startDir must not be null");
        }
        // 2) Must exist and be a directory.
        if (!Files.exists(startDir)) {
            throw new IllegalArgumentException("Directory does not exist: " + startDir);
        }
        if (!Files.isDirectory(startDir)) {
            throw new IllegalArgumentException("Not a directory: " + startDir);
        }
        // 3) Require at least one criterion to be supplied, or else we refuse to proceed.
        if (criteria == null || criteria.length == 0) {
            throw new IllegalArgumentException("At least one SearchCriteria must be provided");
        }

        // 4) Use Files.walk(...) to traverse all subdirectories.  We do this in a
        //    try-with-resources so the Stream is closed automatically.
        try (Stream<Path> stream = Files.walk(startDir)) {
            return stream
                // 4a) We only want regular files (not directories).  isRegularFile may return false
                //     if a path is a directory or if we lack permission.  That’s OK: filter it out.
                .filter(path -> Files.isRegularFile(path))
                // 4b) For each regular file, check if ALL criteria are satisfied.
                .filter(path -> matchesAll(path, criteria))
                // 4c) Collect the matching files into a List<Path>.
                .collect(Collectors.toList());
        } catch (IOException e) {
            // Wrap any checked IOException in a runtime exception so the caller knows.
            throw new UncheckedIOException("Error walking the file tree at: " + startDir, e);
        }
    }

    /**
     * Returns true if the given path satisfies every one of the criteria array.
     * Equivalent to: criteria[0].matches(path) && criteria[1].matches(path) && ...
     *
     * @param path     the file to test
     * @param criteria one or more SearchCriteria
     * @return true only if every criterion returns true
     */
    private static boolean matchesAll(Path path, SearchCriteria[] criteria) {
        for (SearchCriteria c : criteria) {
            // If any single criterion is not met, return false immediately.
            if (!c.matches(path)) {
                return false;
            }
        }
        // If we never returned false, then all criteria passed.
        return true;
    }
}