package com.interview.notes.code.year.y2024.march24.test7;

import java.util.Arrays;
import java.util.List;

public class IkmTest {
    static int index = 0;

    public static void main(String[] args) {
        List<MathOperation> myList = Arrays.asList(null, null);
        myList.forEach(m -> System.out.print(m.calculate(args[index++], 1, 2) + " "));
    }
}

class MathOperation {
    public static int calculate(String choice, int a, int b) {
        int c = 3;
        switch (choice) {
            case "ADD":
                c += a + b;
                break;
            case "SUBTRACT":
                c += a - b;
                break;
            default:
                c += a * b;
        }
        return c;
    }
}
