package com.interview.notes.code.year.y2024.july24.test7;

import java.util.HashMap;
import java.util.Map;

/*
The simple storage system should support adding files as well as deleting files.
Operations:
ADD_FILE <file_name>
Should add a new file with name file_name to the storage.
If a file with the same name file_name already exists, the current operation fails.
Returns "true" if the file was added successfully, or "false" otherwise.
DELETE_FILE <file_name>
Should delete the file with the file_name.
Returns "true" if the file was deleted successfully.
Returns "false" if the file doesn't exist.

 */
public class SimpleStorageSystem {
    private final Map<String, String> fileStorage;

    public SimpleStorageSystem() {
        fileStorage = new HashMap<>();
    }

    public static void main(String[] args) {
        SimpleStorageSystem storage = new SimpleStorageSystem();
        System.out.println(storage.addFile("document.txt", "This is the content of the document."));  // Output: true
        System.out.println(storage.addFile("document.txt", "This is the content of the document."));  // Output: false
        System.out.println(storage.deleteFile("document.txt"));  // Output: true
        System.out.println(storage.deleteFile("document.txt"));  // Output: false
    }

    /**
     * Adds a file and its content to the storage.
     *
     * @param fileName the name of the file to add
     * @param content  the content of the file
     * @return "true" if the file was added successfully, "false" if the file already exists
     */
    public String addFile(String fileName, String content) {
        // Check if the file already exists
        if (fileStorage.containsKey(fileName)) {
            return "false";
        }
        // Add the file and its content to the map
        fileStorage.put(fileName, content);
        return "true";
    }

    /**
     * Deletes a file from the storage.
     *
     * @param fileName the name of the file to delete
     * @return "true" if the file was deleted successfully, "false" if the file does not exist
     */
    public String deleteFile(String fileName) {
        // Remove the file from the map if it exists
        String removedContent = fileStorage.remove(fileName);
        return removedContent != null ? "true" : "false";
    }
}
