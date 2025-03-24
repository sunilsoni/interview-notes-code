package com.interview.notes.code.year.y2025.march.common.test18;

public class BracketBalancer {
    public static int minOperations(String str) {
        if(str.length() % 2 != 0) return -1;
        int open = 0, operations = 0;
        for(char ch : str.toCharArray()) {
            if(ch == '{') open++;
            else {
                if(open > 0) open--;
                else {
                    operations++;
                    open++;
                }
            }
        }
        operations += open / 2;
        return operations;
    }

    public static void main(String[] args) {
        String[] tests = {"{{", "{}{}", "}{{}}{{{", "{{{{", "{{{{}}}}", "{}{}{}", "}{"};
        int[] expected = {1, 0, 3, 2, 0, 0, 2};
        for(int i = 0; i < tests.length; i++) {
            int result = minOperations(tests[i]);
            System.out.println("Test " + (i+1) + " : " + (result == expected[i] ? "PASS" : "FAIL"));
        }
        
        // Large input test
        String largeInput = new String(new char[1000000]).replace("\0", "{");
        System.out.println("Large Test : " + (minOperations(largeInput) == 500000 ? "PASS" : "FAIL"));
    }
}
