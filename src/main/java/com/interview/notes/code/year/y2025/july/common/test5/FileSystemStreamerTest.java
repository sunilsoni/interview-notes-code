package com.interview.notes.code.year.y2025.july.common.test5;

import com.interview.notes.code.year.y2025.july.common.test6.FileSystemStreamer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileSystemStreamerTest {
    public static void main(String[] args) {
        // --- Build the nested map using HashMap so null values are OK ---
        Map<String, Object> dir1 = new HashMap<>();
        dir1.put("file1.txt", null);
        dir1.put("file2.txt", null);

        Map<String, Object> subdir1 = new HashMap<>();
        subdir1.put("file3.txt", null);

        Map<String, Object> dir2 = new HashMap<>();
        dir2.put("subdir1", subdir1);
        dir2.put("file4.txt", null);

        Map<String, Object> root = new HashMap<>();
        root.put("dir1", dir1);
        root.put("dir2", dir2);

        Map<String, Object> fileSystem = new HashMap<>();
        fileSystem.put("root", root);

        // Now call your streaming method:
        List<String> paths = FileSystemStreamer
                .listAllFiles(fileSystem)
                .collect(Collectors.toList());
        paths.forEach(System.out::println);
    }
}