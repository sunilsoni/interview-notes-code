package com.interview.notes.code.tricky;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Compression2 {
    public static byte[] compress(byte[] input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int countZeros = 0;
        int countNonZeros = 0;
        boolean lastByteWasZero = false;

        for (byte b : input) {
            if (b == 0) {
                countZeros++;
                if (!lastByteWasZero) {
                    // start a new block of zeros
                    flushNonZeros(output, countNonZeros);
                    countNonZeros = 0;
                    lastByteWasZero = true;
                }
                if (countZeros == 128) {
                    // reached maximum number of zeros that can be encoded in a single byte
                    output.write(0);
                    output.write(127);
                    countZeros = 0;
                }
            } else {
                countNonZeros++;
                if (lastByteWasZero) {
                    // start a new block of non-zeros
                    flushZeros(output, countZeros);
                    countZeros = 0;
                    lastByteWasZero = false;
                }
                if (countNonZeros == 128) {
                    // reached maximum number of bytes that can be encoded in a single byte
                    output.write(128 | 127);
                    output.write(input, input.length - countNonZeros, countNonZeros);
                    countNonZeros = 0;
                }
            }
        }

        // flush any remaining zeros or non-zeros
        if (lastByteWasZero) {
            flushZeros(output, countZeros);
        } else {
            flushNonZeros(output, countNonZeros);
        }

        return output.toByteArray();
    }

    private static void flushZeros(ByteArrayOutputStream output, int countZeros) {
        if (countZeros > 0) {
            output.write(countZeros);
        }
    }

    private static void flushNonZeros(ByteArrayOutputStream output, int countNonZeros) throws IOException {
        if (countNonZeros > 0) {
            int numBytes = countNonZeros > 127 ? 127 : countNonZeros;
            output.write(128 | numBytes);
            output.write(new byte[numBytes]);
            countNonZeros -= numBytes;
            while (countNonZeros > 0) {
                numBytes = countNonZeros > 127 ? 127 : countNonZeros;
                output.write(128 | numBytes);
                output.write(new byte[numBytes]);
                countNonZeros -= numBytes;
            }
        }
    }

    public static void main(String[] args) {
        byte[] input = {0, 0, 1, 2, 0, 0};
        //byte[] input = {0, 0, 1,2,0,0};
        byte[] compressed = Compression.compress(input);
        System.out.println(Arrays.toString(compressed)); // prints [2, 2, 1, 2]

    }
}
