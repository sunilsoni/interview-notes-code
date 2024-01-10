package com.interview.notes.code.months.year2023.Aug23.test1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordCounter {
    public static void main(String[] args) {
        String filePath = "path/to/your/text/file.txt"; // Specify the path to your text file

        try {
            Map<String, Integer> wordCountMap = new HashMap<>();

            // Read the text file
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s+"); // Split by whitespace
                    for (String word : words) {
                        word = word.toLowerCase(); // Normalize word to lowercase
                        wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                    }
                }
            }

            // Sort the results alphabetically
            TreeMap<String, Integer> sortedWordCountMap = new TreeMap<>(wordCountMap);

            // Display the results
            for (Map.Entry<String, Integer> entry : sortedWordCountMap.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
