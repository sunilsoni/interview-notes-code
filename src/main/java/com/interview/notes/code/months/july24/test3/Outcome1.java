package com.interview.notes.code.months.july24.test3;

class Outcome1 {
    public static int solve(long M, long N) {
        int count = 0;
        for (long i = (long) Math.ceil(Math.sqrt(M)); i * i <= N; i++) {
            long square = i * i;
            if (isValid(square)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isValid(long num) {
        String s = String.valueOf(num);
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) >= s.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solve(121, 121));
        System.out.println(solve(40, 70));
    }
}
