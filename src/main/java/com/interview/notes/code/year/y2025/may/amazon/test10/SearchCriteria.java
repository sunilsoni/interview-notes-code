package com.interview.notes.code.year.y2025.may.amazon.test10;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A functional interface for defining a single file‐matching criterion.
 */
public interface SearchCriteria {
    /**
     * Returns true if the given path satisfies this criterion.
     * @param path the file or directory to test
     * @return true if it matches; false otherwise
     */
    boolean matches(Path path);
}