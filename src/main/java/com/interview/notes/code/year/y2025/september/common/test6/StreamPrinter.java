package com.interview.notes.code.year.y2025.september.common.test6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.Objects;

class StreamPrinter {

    /**
     * Reads all characters from the given reader and prints them to stdout.
     * Uses a buffer for efficiency, validates input, and wraps IOExceptions.
     */
    public static void print(Reader reader) {
        Objects.requireNonNull(reader, "reader must not be null");
        try (BufferedReader br = new BufferedReader(reader)) {
            char[] buf = new char[8_192];
            int len;
            while ((len = br.read(buf)) != -1) {
                System.out.print(new String(buf, 0, len));
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to print stream", e);
        }
    }
}