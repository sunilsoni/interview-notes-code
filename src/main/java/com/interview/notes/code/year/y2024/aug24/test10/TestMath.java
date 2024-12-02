package com.interview.notes.code.year.y2024.aug24.test10;

public class TestMath {
    public static void main(String args[]) {
        // First Interface
        Math1 myMath1 = (Valuel, Value2) -> Valuel + Value2;
        System.out.println("Math1:");
        System.out.println(myMath1.DoMath(5, 6)); // Outputs 11
        System.out.println(myMath1.Truncate(5, 6)); // Outputs 1

        //  Second Interface
//        Math2 myMath2 = (Valuel, Value2) -> Valuel + Value2;
//        System.out.println("Math2:");
//        System.out.println(myMath2.Truncate(5, 6)); // Outputs 11
//        System.out.println(myMath2.DoMath(5, 6)); // Outputs 11

        // Third Interface
        Math3 myMath3 = (Valuel, Value2) -> Valuel + Value2;
        System.out.println("Math3:");
        System.out.println(myMath3.DoMath(5, 6)); // Outputs 11
        System.out.println(myMath3.Truncate(5, 6)); // Outputs 11

        // Fourth Interface
        Math4 myMath4 = (Valuel, Value2) -> Valuel + Value2;
        System.out.println("Math4:");
        System.out.println(myMath4.DoMath(5, 6)); // Outputs 11
        System.out.println(myMath4.Truncate(5, 6)); // Outputs 1

        // Fifth Interface
//        Math5 myMath5 = (Valuel, Value2) -> Valuel + Value2;
//        System.out.println("Math5:");
//        System.out.println(myMath5.DoMath(5, 6)); // Outputs 11
    }

    @FunctionalInterface
    interface Math1 {
        int DoMath(int Valuel, int Value2);

        default String Truncate(Integer Valuel, Integer Value2) {
            return null;//Integer.sum(Valuel, Value2).toString().substring(0, 1);
        }
    }

    @FunctionalInterface
    interface Math2 {
        String Truncate(int Valuel, int Value2);

        default int DoMath(int Valuel, int Value2) {
            return Valuel + Value2;
        }
    }

    @FunctionalInterface
    interface Math3 {
        int DoMath(int Valuel, int Value2);

        default String Truncate(Integer Valuel, Integer Value2) {
            return new Integer(Integer.sum(Valuel, Value2)).toString();
        }
    }

    @FunctionalInterface
    interface Math4 {
        int DoMath(int Valuel, int Value2);

        default String Truncate(int Valuel, int Value2) {
            Integer Out = Valuel + Value2;
            return Out.toString().substring(0, 1);
        }
    }

    // @FunctionalInterface
    interface Math5 {
        int DoMath(int Valuel, int Value2);

        String Truncate(int Valuel, int Value2);
    }
}
