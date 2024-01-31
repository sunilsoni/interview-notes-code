package com.interview.notes.code.months.jan24.test7;

import java.util.ArrayList;
import java.util.List;

class Directory {
    private String name;
    private List<File> files;
    private List<Directory> subDirectories;

    public Directory(String name) {
        this.name = name;
        this.files = new ArrayList<>();
        this.subDirectories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<File> getFiles() {
        return files;
    }

    public List<Directory> getSubDirectories() {
        return subDirectories;
    }

    public void createFile(String name) {
        files.add(new File(name));
    }

    public void createDirectory(String name) {
        subDirectories.add(new Directory(name));
    }

    public File findFile(String name) {
        for (File file : files) {
            if (file.getName().equals(name)) {
                return file;
            }
        }
        return null;
    }

    public Directory findDirectory(String name) {
        for (Directory directory : subDirectories) {
            if (directory.getName().equals(name)) {
                return directory;
            }
        }
        return null;
    }
}