package com.interview.notes.code.months.Aug23.test4;

public class Main {
    public static void main(String[] args) {
        String str1 = "aa";
        String str2 = "abcca";
        boolean isPart = kmpSearch(str1, str2);
        System.out.println(isPart ? str1 + " is a part of " + str2 : str1 + " is not a part of " + str2);
    }

    // Fills lps[] for given pattern pat[0..M-1]
    static void computeLPSArray(String pat, int M, int[] lps) {
        int len = 0;  // length of the previous longest prefix suffix
        int i = 1;
        lps[0] = 0;  // lps[0] is always 0

        // The loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    // KMP Search Algorithm
    static boolean kmpSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        int[] lps = new int[M];
        computeLPSArray(pat, M, lps);

        int i = 0;  // Index for txt[]
        int j = 0;  // Index for pat[]

        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                return true;
            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false;
    }
}
