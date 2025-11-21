package com.interview.notes.code.year.y2024.july24.test8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExceptionHandlingExample {

    public static void main(String[] args) {
        String filePath = "example.txt";

        // Using try-with-resources to ensure resources are closed properly
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            // Handle the case where the file was not found
            System.err.println("File not found: " + filePath);
            // You may also log the error or rethrow it if appropriate
        } catch (IOException e) {
            // Handle other IO errors
            System.err.println("An error occurred while reading the file.");
            // Log the stack trace for debugging
            e.printStackTrace();
        }
    }
}
