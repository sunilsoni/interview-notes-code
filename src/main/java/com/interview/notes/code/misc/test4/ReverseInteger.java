package com.interview.notes.code.misc.test4;

public class ReverseInteger {
    public static void main(String[] args) {
        System.out.println(reverse(123));
    }
    public static int reverse(int x)
    {
        int result = 0;

        while (x != 0)
        {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result)
            { return 0; }
            result = newResult;
            x = x / 10;
        }

        return result;
    }

}
