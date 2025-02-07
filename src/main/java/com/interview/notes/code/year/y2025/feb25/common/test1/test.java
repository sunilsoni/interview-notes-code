package com.interview.notes.code.year.y2025.feb25.common.test1;

class test {
    public static String method1() {
        try {
            int i = 9 / 0;  // Division by zero
            System.out.println(i);
        } catch (Exception e) {
            System.out.println("exception caught");
            return "from catch";  // return from catch
        } finally {
            System.out.println("finally block executing");  // Always executed
        }

        System.out.println("end");  // This line will not execute
        return "from end";  // This line will also not execute
    }


    public static void main(String[] args) {
        method1();
    }
}
