package com.interview.notes.code.months.july24.Test1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {
    public static void main(String[] args) {
        String filePath = "path/to/your/textfile.txt";
        int wordCount = countWordsInFile(filePath);
        System.out.println("Total number of words in the file: " + wordCount);


    }

    public static int countWordsInFile(String filePath) {
        int wordCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into words using whitespace as the delimiter
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordCount;
    }
}
