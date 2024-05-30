package com.interview.notes.code.months.may24.test9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadWriteProcess {
    public static void main(String[] args) {
        // Step 1: Create file and write data
        try {
            FileWriter writer = new FileWriter("data.txt");
            writer.write("Hello world! This is a test.");
            writer.close();
            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        // Step 2: Read file
        try {
            FileReader reader = new FileReader("data.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            System.out.println("Data read from file: " + stringBuilder.toString());

            // Step 3: Process data and reverse words
            String[] words = stringBuilder.toString().split("\\s+");
            StringBuilder reversedData = new StringBuilder();
            for (String word : words) {
                StringBuilder reversedWord = new StringBuilder(word);
                reversedData.append(reversedWord.reverse()).append(" ");
            }
            System.out.println("Reversed words: " + reversedData.toString());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
