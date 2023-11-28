package com.interview.notes.code.months.nov23.MorganStanley;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WordFrequencyCounter {

    public static void main(String[] args) {
        String filePath = "path/to/your/file.txt"; // Replace with your file path
        Map<String, Long> wordFrequency = countWordFrequency(filePath);
        wordFrequency.forEach((word, count) -> System.out.println(word + ": " + count));
    }

    private static Map<String, Long> countWordFrequency(String filePath) {
        Map<String, Long> wordCountMap = new ConcurrentHashMap<>();
        try {
            Files.lines(Paths.get(filePath))
                    .parallel() // Enables parallel stream
                    .flatMap(line -> Arrays.stream(line.trim().split("\\s+")))
                    .forEach(word -> wordCountMap.merge(word, 1L, Long::sum));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordCountMap;
    }
}
