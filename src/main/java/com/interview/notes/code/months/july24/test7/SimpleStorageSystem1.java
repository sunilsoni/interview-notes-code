package com.interview.notes.code.months.july24.test7;

import java.util.HashSet;
import java.util.Set;

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
public class SimpleStorageSystem1 {
    private Set<String> files;

    public SimpleStorageSystem1() {
        files = new HashSet<>();
    }

    public static void main(String[] args) {
        SimpleStorageSystem1 storage = new SimpleStorageSystem1();
        System.out.println(storage.addFile("document.txt"));  // Output: true
        System.out.println(storage.addFile("document.txt"));  // Output: false
        System.out.println(storage.deleteFile("document.txt"));  // Output: true
        System.out.println(storage.deleteFile("document.txt"));  // Output: false
    }

    /**
     * Adds a file to the storage.
     *
     * @param fileName the name of the file to add
     * @return "true" if the file was added successfully, "false" if the file already exists
     */
    public String addFile(String fileName) {
        // Attempt to add the file to the set
        boolean added = files.add(fileName);
        return added ? "true" : "false";
    }

    /**
     * Deletes a file from the storage.
     *
     * @param fileName the name of the file to delete
     * @return "true" if the file was deleted successfully, "false" if the file does not exist
     */
    public String deleteFile(String fileName) {
        // Attempt to remove the file from the set
        boolean removed = files.remove(fileName);
        return removed ? "true" : "false";
    }
}
