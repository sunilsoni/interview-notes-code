package com.interview.notes.code.year.y2025.may.amazon.test10;

import java.nio.file.Path;

/**
 * A functional interface for defining a single file‐matching criterion.
 */
public interface SearchCriteria {
    /**
     * Returns true if the given path satisfies this criterion.
     *
     * @param path the file or directory to test
     * @return true if it matches; false otherwise
     */
    boolean matches(Path path);
}