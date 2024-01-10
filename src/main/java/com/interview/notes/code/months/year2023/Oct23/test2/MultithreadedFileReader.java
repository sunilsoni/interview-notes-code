package com.interview.notes.code.months.year2023.Oct23.test2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultithreadedFileReader {
    public static void main(String[] args) {
        final int numThreads = 4; // Number of threads to read the file
        File inputFile = new File("path/to/your/input/file.txt");

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        List<Future<List<String>>> results = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            long fileSize = inputFile.length();
            long chunkSize = fileSize / numThreads;
            long remainingSize = fileSize;

            for (int i = 0; i < numThreads; i++) {
                long currentChunkSize = (i == numThreads - 1) ? remainingSize : chunkSize;
                Callable<List<String>> fileReaderTask = new FileReaderTask(br, currentChunkSize);
                results.add(executorService.submit(fileReaderTask));
                remainingSize -= currentChunkSize;
            }

            // Wait for all threads to finish
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            // Combine results if necessary
            List<String> allLines = new ArrayList<>();
            for (Future<List<String>> result : results) {
                allLines.addAll(result.get());
            }

            // Process the combined data as needed
            for (String line : allLines) {
                // Process each line
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class FileReaderTask implements Callable<List<String>> {
    private BufferedReader reader;
    private long chunkSize;

    FileReaderTask(BufferedReader reader, long chunkSize) {
        this.reader = reader;
        this.chunkSize = chunkSize;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> lines = new ArrayList<>();
        String line;
        long bytesRead = 0;

        while ((line = reader.readLine()) != null && bytesRead < chunkSize) {
            lines.add(line);
            bytesRead += line.length() + 1; // +1 for newline character
        }

        return lines;
    }
}
