package com.interview.notes.code.year.y2023.Aug23.test1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

//Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
public class WordCount {

    public static void main(String[] args) throws IOException {
        // Get the file name from the command line arguments.
        String fileName = args[0];

        // Create a HashMap to store the word counts.
        Map<String, Integer> wordCounts = new HashMap<>();

        // Open the file and read each line.
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            // Split the line into words.
            String[] words = line.split(" ");

            // Iterate over the words and increment the count for each word.
            for (String word : words) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }

        // Close the file.
        bufferedReader.close();

        // Sort the word counts by word.
        Map<String, Integer> sortedWordCounts = wordCounts.entrySet().stream()
                .sorted((a, b) -> a.getKey().compareTo(b.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Print the sorted word counts.
        for (Map.Entry<String, Integer> entry : sortedWordCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
