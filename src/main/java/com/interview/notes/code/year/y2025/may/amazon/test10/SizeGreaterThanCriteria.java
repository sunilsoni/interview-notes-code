package com.interview.notes.code.year.y2025.may.amazon.test10;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A criterion that checks if a file’s size is strictly greater than a given threshold.
 */
public class SizeGreaterThanCriteria implements SearchCriteria {
    private final long thresholdBytes; 
    // Holds the byte‐size threshold (e.g. 5 * 1024 * 1024 for 5 MB).

    /**
     * Constructs the criterion with the given size threshold (in bytes).
     *
     * @param thresholdBytes files larger than this number of bytes will match
     */
    public SizeGreaterThanCriteria(long thresholdBytes) {
        this.thresholdBytes = thresholdBytes;
    }

    @Override
    public boolean matches(Path path) {
        try {
            // First, ensure it’s a regular file (not a directory, not a symlink to a directory).
            // If it’s not a regular file, we immediately return false.
            if (!Files.isRegularFile(path)) {
                return false;
            }
            // Next, ask the file system for the file size in bytes.
            long size = Files.size(path);
            // Return true only if the file’s actual size is > thresholdBytes.
            return (size > thresholdBytes);
        } catch (IOException e) {
            // If we can’t read the size (e.g., no permission), wrap it in an unchecked exception
            // so the caller sees what went wrong.
            throw new UncheckedIOException("Failed to get file size for: " + path, e);
        }
    }
}