package com.interview.notes.code.year.y2025.may.amazon.test10;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A criterion that checks if a file’s name ends with a given extension.
 * We perform a case‐insensitive check (convert both to lowercase).
 */
public class ExtensionCriteria implements SearchCriteria {
    private final String extensionLower;
    // e.g., ".xml" (converted to lowercase in constructor)

    /**
     * Constructs the criterion for the given extension.
     *
     * @param extension file extension to match, e.g. ".xml" (dot included)
     */
    public ExtensionCriteria(String extension) {
        if (extension == null || extension.isEmpty()) {
            throw new IllegalArgumentException("Extension cannot be null or empty");
        }
        // Normalize to lowercase so that "data.XML" also matches ".xml"
        this.extensionLower = extension.toLowerCase();
    }

    @Override
    public boolean matches(Path path) {
        // First, ensure it’s a regular file.  If not, return false.
        if (!Files.isRegularFile(path)) {
            return false;
        }
        // Get actual filename as string
        String fileName = path.getFileName().toString();
        // Compare lowercase name with our lowercase extension
        return fileName.toLowerCase().endsWith(extensionLower);
    }
}