package com.interview.notes.code.year.y2024.aug24.test19;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileSearchAPI {

    public static void main(String[] args) {
        FileSearchAPI api = new FileSearchAPI();
        String directoryPath = "/path/to/your/directory";
        String keyword = "Amazon";

        try {
            List<FileInfo> matchingFiles = api.searchFiles(directoryPath, keyword);
            System.out.println("Files matching '" + keyword + "':");
            for (FileInfo file : matchingFiles) {
                System.out.println(file);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while searching files: " + e.getMessage());
        }
    }

    /**
     * Searches for files in the given directory that contain the specified keyword.
     *
     * @param directoryPath The path to the directory to search in.
     * @param keyword       The keyword to search for in file names (case sensitive).
     * @return A list of FileInfo objects representing matching files.
     * @throws IOException If an I/O error occurs.
     */
    public List<FileInfo> searchFiles(String directoryPath, String keyword) throws IOException {
        Path dir = Paths.get(directoryPath);
        if (!Files.isDirectory(dir)) {
            throw new IllegalArgumentException("The provided path is not a directory.");
        }

        List<FileInfo> matchingFiles = new ArrayList<>();
        File[] files = dir.toFile().listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().contains(keyword)) {
                    matchingFiles.add(new FileInfo(file));
                }
            }
        }

        return matchingFiles;
    }

    /**
     * Internal class to represent file information.
     */
    public static class FileInfo {
        private final String name;
        private final long size;
        private final String path;

        public FileInfo(File file) {
            this.name = file.getName();
            this.size = file.length();
            this.path = file.getAbsolutePath();
        }

        // Getters and toString() method...
    }
}
