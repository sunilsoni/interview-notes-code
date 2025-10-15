package com.interview.notes.code.year.y2025.october.common.test4;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class KWayMerge {
    // Merge multiple sorted files (each line an integer) to outputWriter
    public static void mergeFiles(List<File> inputs, Writer outputWriter, int bufferSizePerStream) throws IOException {
        int k = inputs.size();
        BufferedReader[] readers = new BufferedReader[k];
        try (BufferedWriter out = new BufferedWriter(outputWriter)) {
            // open readers with buffer
            for (int i = 0; i < k; i++) {
                readers[i] = new BufferedReader(new FileReader(inputs.get(i)), Math.max(8192, bufferSizePerStream));
            }

            PriorityQueue<Item> pq = new PriorityQueue<>();
            // initialize heap with first int from each file
            for (int i = 0; i < k; i++) {
                String line = readers[i].readLine();
                if (line != null && !line.isEmpty()) {
                    pq.add(new Item(Integer.parseInt(line.trim()), i));
                }
            }

            // Merge loop
            while (!pq.isEmpty()) {
                Item it = pq.poll();
                out.write(Integer.toString(it.value));
                out.newLine();

                // read next value from same stream
                String next = readers[it.streamId].readLine();
                if (next != null && !next.isEmpty()) {
                    pq.add(new Item(Integer.parseInt(next.trim()), it.streamId));
                }
            }
            out.flush();
        } finally {
            for (BufferedReader r : readers) {
                if (r != null) try { r.close(); } catch (IOException ignored) {}
            }
        }
    }

    // Example usage
    public static void main(String[] args) throws Exception {
        List<File> inputs = Arrays.asList(new File("a.txt"), new File("b.txt"), new File("c.txt"));
        try (Writer w = new FileWriter("merged.txt")) {
            mergeFiles(inputs, w, 64 * 1024); // 64KB buffer per stream
        }
    }

    static class Item implements Comparable<Item> {
        int value;
        int streamId;
        Item(int value, int streamId) { this.value = value; this.streamId = streamId; }
        public int compareTo(Item o) { return Integer.compare(this.value, o.value); }
    }
}