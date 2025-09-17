package com.interview.notes.code.year.y2025.september.common.test5;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

/**
 * This class defines a stream printer.
 */
class StreamPrinter {

    /**
     * Reads from the given reader and prints to stdout robustly.
     * This improved version uses try-with-resources to prevent resource leaks,
     * adds a null check for the reader, and uses a buffer for efficient reading.
     *
     * @param reader The reader to read from. Must not be null.
     * @throws IOException If an I/O error occurs.
     */
    void print(Reader reader) throws IOException {
        // 1. Add a null-check to fail-fast if the input is invalid.
        Objects.requireNonNull(reader, "Reader cannot be null");

        // 2. Use a try-with-resources statement to guarantee the reader is
        //    always closed, preventing resource leaks.
        try (reader) {
            // 3. Use a buffer for efficient, chunked I/O, which is much faster
            //    than reading one character at a time.
            char[] buffer = new char[4096];
            int charsRead;
            while ((charsRead = reader.read(buffer)) != -1) {
                // Print the portion of the buffer that was filled.
                System.out.print(new String(buffer, 0, charsRead));
            }
        }
    }
}