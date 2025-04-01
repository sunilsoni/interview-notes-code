package com.interview.notes.code.year.y2025.march.amazon.test11;

public class Solution {
    public static String getMinimumString(String s_id) {
        char[] chars = s_id.toCharArray();
        int[] count = new int[10];
        
        for(char c : chars) {
            count[c - '0']++;
        }
        
        StringBuilder result = new StringBuilder();
        
        // Add smallest digits first
        for(int i = 0; i < 10; i++) {
            while(count[i] > 0) {
                result.append(i);
                count[i]--;
                
                // Increment remaining larger digits
                for(int j = i+1; j < 10 && count[j] > 0; j++) {
                    count[j]--;
                    count[Math.min(j+1, 9)]++;
                }
            }
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        test("26547", "24677");
        test("04829", "02599");
        test("34892", "24599");
        test("99999", "99999");
        test("12345", "12345");
        test("54321", "12459");
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 100000; i++) {
            sb.append((int)(Math.random() * 10));
        }
        String largeInput = sb.toString();
        test(largeInput, getMinimumString(largeInput));
    }

    private static void test(String input, String expected) {
        String result = getMinimumString(input);
        System.out.println("Input: " + input);
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Status: " + (result.equals(expected) ? "PASS" : "FAIL"));
        System.out.println();
    }
}
