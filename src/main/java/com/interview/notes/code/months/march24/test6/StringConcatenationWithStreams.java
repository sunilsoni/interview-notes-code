package com.interview.notes.code.months.march24.test6;

import java.util.stream.Stream;

public class StringConcatenationWithStreams {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = " world!";
        
        // Create streams from the strings
        Stream<String> stream1 = Stream.of(str1);
        Stream<String> stream2 = Stream.of(str2);
        
        // Concatenate the streams
        Stream<String> concatenatedStream = Stream.concat(stream1, stream2);
        
        // Convert the concatenated stream to a single string
        String result = concatenatedStream.reduce("", (s1, s2) -> s1 + s2);
        
        System.out.println("Concatenated string: " + result);//OUPUT: Concatenated string: Hello world!
    }
}
