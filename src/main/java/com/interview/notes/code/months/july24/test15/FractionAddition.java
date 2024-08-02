package com.interview.notes.code.months.july24.test15;

public class FractionAddition {
    public static Fraction addFractions(Fraction f1, Fraction f2) {
        int newNumerator = f1.numerator * f2.denominator + f2.numerator * f1.denominator;
        int newDenominator = f1.denominator * f2.denominator;

        int gcd = findGCD(newNumerator, newDenominator);
        return new Fraction(newNumerator / gcd, newDenominator / gcd);
    }

    private static int findGCD(int a, int b) {
        return b == 0 ? a : findGCD(b, a % b);
    }

    public static void main(String[] args) {
        Fraction f1 = new Fraction(1, 4);
        Fraction f2 = new Fraction(1, 2);
        Fraction result = addFractions(f1, f2);
        System.out.println("Sum: " + result.numerator + "/" + result.denominator);
    }

    static class Fraction {
        int numerator;
        int denominator;

        Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }
}
