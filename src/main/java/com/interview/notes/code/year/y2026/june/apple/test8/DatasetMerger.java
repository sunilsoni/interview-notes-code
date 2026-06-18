package com.interview.notes.code.year.y2026.june.apple.test8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

public class DatasetMerger {

    private final Path referenceDataPath;
    private final Path targetDataPath;
    private final Path outputPath;

    public DatasetMerger(String referenceFile, String targetFile, String outputFile) {
        this.referenceDataPath = Path.of(referenceFile);
        this.targetDataPath = Path.of(targetFile);
        this.outputPath = Path.of(outputFile);
    }

    // --- Execution ---
    public static void main(String[] args) {
        var merger = new DatasetMerger(
                "/home/coderpad/data/artists.txt",
                "/home/coderpad/data/songs.txt",
                "/home/coderpad/data/output.txt"
        );

        try {
            merger.merge();
            System.out.println("Merge completed successfully.");
        } catch (IOException e) {
            System.err.println("I/O Error during merge operation: " + e.getMessage());
            // In production, this would route to a logging framework like SLF4J
        }
    }

    /**
     * Orchestrates the join operation.
     */
    public void merge() throws IOException {
        Map<String, String> referenceCache = loadReferenceData();
        processAndWrite(referenceCache);
    }

    /**
     * Loads the smaller dataset (artists) into memory for O(1) lookups.
     */
    private Map<String, String> loadReferenceData() throws IOException {
        try (var lines = Files.lines(referenceDataPath)) {
            return lines.collect(Collectors.toMap(
                    this::extractKey,
                    line -> line,
                    (existing, replacement) -> existing // Gracefully handle duplicate keys
            ));
        }
    }

    /**
     * Streams the larger dataset (songs) and performs the join, writing to output.
     */
    private void processAndWrite(Map<String, String> referenceCache) throws IOException {
        try (var reader = Files.newBufferedReader(targetDataPath);
             var writer = Files.newBufferedWriter(outputPath)) {

            String targetLine;
            while ((targetLine = reader.readLine()) != null) {
                String key = extractKey(targetLine);
                String referenceLine = referenceCache.get(key);

                if (referenceLine != null) {
                    writer.write(referenceLine);
                    writer.write(targetLine);
                    writer.newLine();
                }
            }
        }
    }

    /**
     * High-performance key extraction. Avoids String.split() to prevent
     * continuous array allocations in the heap during large iterations.
     */
    private String extractKey(String line) {
        int commaIdx = line.indexOf(',');
        return commaIdx == -1 ? line : line.substring(0, commaIdx);
    }
}