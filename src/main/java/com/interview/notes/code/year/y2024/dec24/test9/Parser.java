package com.interview.notes.code.year.y2024.dec24.test9;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Parser {
    private File file;

    // 1. Consider making file final and initializing in constructor
    public Parser(File file) {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        this.file = file;
    }

    // 2. Synchronized might not be needed if file is final
    public File getFile() {
        return file;
    }

    // 3. Improved getContent using try-with-resources and StringBuilder
    public String getContent() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            return content.toString();
        }
    }

    // 4. Improved saveContent using try-with-resources and BufferedWriter
    public void saveContent(String content) throws IOException {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(file, StandardCharsets.UTF_8))) {
            writer.write(content);
        }
    }

    // 5. Alternative modern approach using Files API
    public String getContentModern() throws IOException {
        return Files.readString(file.toPath(), StandardCharsets.UTF_8);
    }

    public void saveContentModern(String content) throws IOException {
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        Files.writeString(file.toPath(), content, StandardCharsets.UTF_8);
    }
}
