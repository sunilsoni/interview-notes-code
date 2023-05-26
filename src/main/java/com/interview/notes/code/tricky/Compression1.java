package com.interview.notes.code.tricky;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * In java
 * <p>
 * We want to implement a compression algorithm for byte sequences with a lot of Os using the following algorithm: -
 * <p>
 * M consecutive zeros: encode as 1 byte with first bit set to 0 and remaining seven bits to indicate the number of zeros (M) -
 * N consecutive non-zeros: encode as 1 byte with first bit set to 1 and remaining seven bits to indicate the number of bytes (N), then copy the N bytes
 */
public class Compression1 {
    public static byte[] compress(byte[] input) {
        // count the frequency of each byte value
        Map<Byte, Integer> freqMap = new HashMap<>();
        for (byte b : input) {
            freqMap.put(b, freqMap.getOrDefault(b, 0) + 1);
        }

        // create a Huffman tree for the byte values
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<Byte, Integer> entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        while (pq.size() > 1) {
            HuffmanNode node1 = pq.poll();
            HuffmanNode node2 = pq.poll();
            pq.add(new HuffmanNode(null, node1.freq + node2.freq, node1, node2));
        }
        HuffmanNode root = pq.poll();

        // create a map of codes for each byte value
        Map<Byte, String> codeMap = new HashMap<>();
        createCodeMap(root, "", codeMap);

        // encode the input using the Huffman codes
        StringBuilder sb = new StringBuilder();
        for (byte b : input) {
            sb.append(codeMap.get(b));
        }
        String encodedString = sb.toString();
        int numBytes = (encodedString.length() + 7) / 8;  // round up to nearest byte
        byte[] output = new byte[numBytes];
        int outputIndex = 0;
        int bitIndex = 0;

        while (bitIndex < encodedString.length()) {
            byte b = 0;
            for (int i = 0; i < 8; i++) {
                if (bitIndex + i < encodedString.length() && encodedString.charAt(bitIndex + i) == '1') {
                    b |= (1 << (7 - i));
                }
            }
            output[outputIndex++] = b;
            bitIndex += 8;
        }

        return output;
    }

    private static void createCodeMap(HuffmanNode node, String code, Map<Byte, String> codeMap) {
        if (node.left == null && node.right == null) {
            codeMap.put(node.value, code);
        } else {
            createCodeMap(node.left, code + "0", codeMap);
            createCodeMap(node.right, code + "1", codeMap);
        }
    }

    private static class HuffmanNode implements Comparable<HuffmanNode> {
        Byte value;
        int freq;
        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode(Byte value, int freq) {
            this.value = value;
            this.freq = freq;
        }

        public HuffmanNode(Byte value, int freq, HuffmanNode left, HuffmanNode right) {
            this.value = value;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public int compareTo(HuffmanNode other) {
            return this.freq - other.freq;
        }
    }
}