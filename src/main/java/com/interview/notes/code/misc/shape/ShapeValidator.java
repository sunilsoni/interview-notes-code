package com.interview.notes.code.misc.shape;

public class ShapeValidator {

    public static Boolean isAnySideZero(double a, double b, double c) {
        return a <= 0 || b <= 0 || c <= 0;
    }

    public static Boolean isTriangle(double[] t) {
        return t[2] < t[0] + t[1] && t[1] < t[0] + t[2] && t[0] < t[1] + t[2];
    }

    public static Boolean isNotRectangle(double[] t) {
        throw new IllegalArgumentException("Not implemented");
    }

}