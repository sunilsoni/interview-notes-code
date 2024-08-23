package com.interview.notes.code.months.aug24.test22;

public class Solution {
    public static String solution(String text) {
        StringBuilder result = new StringBuilder();
        
        for (char c : text.toCharArray()) {
            if (c == 'F' || c == 'f') {
                result.append('K');
            }
            result.append(c);
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(solution("force"));  // Expected: Kforce
        System.out.println(solution("fluffy")); // Expected: KfluKfKfy
        System.out.println(solution("CAPITAL")); // Expected: CAPITAL
        System.out.println(solution("fF")); // Expected: KfKF
        System.out.println(solution("")); // Expected: 
        System.out.println(solution("f f F")); // Expected: Kf Kf KF
    }
}
