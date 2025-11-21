package com.interview.notes.code.year.y2024.jan24.test7;

public class InMemoryFileSystem {
    private final Directory root;

    public InMemoryFileSystem() {
        root = new Directory("/");
    }

    public static void main(String[] args) {
        InMemoryFileSystem fileSystem = new InMemoryFileSystem();

        // Creating directories and files
        fileSystem.getRoot().createDirectory("docs");
        fileSystem.getRoot().createFile("file1.txt");

        // Finding and updating files
        File file1 = fileSystem.getRoot().findFile("file1.txt");
        if (file1 != null) {
            file1.setContent("Hello, World!");
        }

        // Navigating through directories
        Directory docsDirectory = fileSystem.getRoot().findDirectory("docs");
        if (docsDirectory != null) {
            docsDirectory.createFile("readme.txt");
        }
    }

    public Directory getRoot() {
        return root;
    }
}