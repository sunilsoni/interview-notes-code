package com.interview.notes.code.months.year2023.dec23.test6;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ParallelFileProcessing {

    public static void main(String[] args) {
        String directoryPath = "/path/to/your/directory";

        try {
            processFilesInDirectory(directoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processFilesInDirectory(String directoryPath) throws IOException {
        try (Stream<Path> files = Files.list(Path.of(directoryPath))) {
            files.parallel()
                    .filter(Files::isRegularFile)
                    .forEach(ParallelFileProcessing::processFile);
        }
    }

    private static void processFile(Path filePath) {
        try (Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8)) {
            System.out.println("Processing file: " + filePath.getFileName());

            // Process each line in the file
            lines.forEach(line -> {
                // Replace this with your specific processing logic
                System.out.println("Line: " + line);
            });
        } catch (IOException e) {
            System.err.println("Error processing file: " + filePath.getFileName() + " - " + e.getMessage());
        }
    }
}
