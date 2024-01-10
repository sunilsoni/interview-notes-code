package com.interview.notes.code.months.year2023.Aug23.test3;

public class IkmTest {
    public static void main(String[] args) {
        Helper h = new Helper();
        int data = 2;
        h.bump(data);


        System.out.println(h.data + "" + data);
    }

    static class Helper {
        private int data = 5;

        public void bump(int inc) {
            inc++;
            data = data + inc;
        }
    }
}