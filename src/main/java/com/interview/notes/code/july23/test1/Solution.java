package com.interview.notes.code.july23.test1;

class Solution {
    public static void main(String[] args) {

    }

    public void reverseString(char[] s) {
        int len = s.length;
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }
}

