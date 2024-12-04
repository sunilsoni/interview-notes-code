package com.interview.notes.code.months.dec24.test4;

public class ReverseInteger1 {
    
    public static int reverse(int x) {
        int reversed = 0;
        int sign = (x < 0) ? -1 : 1;  // Preserve the sign of x
        x = Math.abs(x);  // Work with positive value of x
        
        while (x != 0) {
            int digit = x % 10;
            x /= 10;
            
            // Check for overflow before multiplying and adding the digit
            if (reversed > Integer.MAX_VALUE / 10 || (reversed == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return 0;
            }
            
            reversed = reversed * 10 + digit;
        }
        
        return reversed * sign;
    }

    public static void main(String[] args) {
        // Test cases
        testReverse(123);           // Expected: 321
        testReverse(-123);          // Expected: -321
        testReverse(120);           // Expected: 21
        testReverse(0);             // Expected: 0
        testReverse(1534236469);    // Expected: 0 (overflow case)
        testReverse(-2147483648);   // Expected: 0 (overflow case)
        testReverse(2147483647);    // Expected: 0 (overflow case)
    }
    
    private static void testReverse(int x) {
        int result = reverse(x);
        System.out.println("Input: " + x + " | Reversed: " + result);
    }
}
