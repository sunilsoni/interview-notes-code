package com.interview.notes.code.tricky;

import java.util.ArrayList;
import java.util.List;

public class Compression {
    public static byte[] compress(byte[] input) {
        List<Byte> outputList = new ArrayList<>();
        int countZeros = 0;
        
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 0) {
                countZeros++;
            } else {
                if (countZeros > 0) {
                    outputList.add((byte) 0);
                    outputList.add((byte) countZeros);
                    countZeros = 0;
                }
                outputList.add(input[i]);
            }
        }
        
        if (countZeros > 0) {
            outputList.add((byte) 0);
            outputList.add((byte) countZeros);
        }
        
        byte[] output = new byte[outputList.size()];
        for (int i = 0; i < outputList.size(); i++) {
            output[i] = outputList.get(i);
        }
        
        return output;
    }

    public static void main(String[] args) {
       // byte[] input = {0, 0, 0, 1, 2, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0};

        byte[] input = {0, 0, 1,2,0,0};

        byte[] compressed = Compression.compress(input);

        System.out.println(compressed);
    }
}
