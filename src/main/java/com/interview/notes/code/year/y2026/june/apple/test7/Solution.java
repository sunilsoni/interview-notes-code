package com.interview.notes.code.year.y2026.june.apple.test7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    private static final String artistsPath = "/home/coderpad/data/artists.txt";
    private static final String songsPath = "/home/coderpad/data/songs.txt";

    public static void main(String[] args) throws IOException {

        Map<String, String> artistsById = new HashMap<>();

        try (BufferedReader artistReader = Files.newBufferedReader(Path.of(artistsPath))) {
            String line;

            while ((line = artistReader.readLine()) != null) {
                if (line.isBlank()) continue;

                String key = getKey(line);
                artistsById.put(key, line);
            }
        }

        try (
            BufferedReader songReader = Files.newBufferedReader(Path.of(songsPath));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            String songLine;

            while ((songLine = songReader.readLine()) != null) {
                if (songLine.isBlank()) continue;

                String key = getKey(songLine);
                String artistLine = artistsById.get(key);

                if (artistLine != null) {
                    writer.write(artistLine + "," + songLine);
                    writer.newLine();
                }
            }
        }
    }

    private static String getKey(String line) {
        return line.split(",", 2)[0].trim();
    }
}