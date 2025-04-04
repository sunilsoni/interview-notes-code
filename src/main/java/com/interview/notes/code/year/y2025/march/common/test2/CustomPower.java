package com.interview.notes.code.year.y2025.march.common.test2;

public class CustomPower {
    
    public static double pow(double base, double exponent) {
        if(exponent == 0) return 1;
        if(exponent == 1) return base;
        if(base == 0) return 0;
        
        boolean isNegativeExponent = exponent < 0;
        exponent = Math.abs(exponent);


        
        double result = 1;
        if(exponent % 1 == 0) {
            for(int i = 0; i < exponent; i++) {
                result *= base;
            }
        } else {
            result = Math.exp(exponent * Math.log(Math.abs(base)));
        }
        
        if(base < 0 && exponent % 2 == 1) result = -result;
        return isNegativeExponent ? 1/result : result;
    }

    public static void main(String[] args) {
        System.out.println("pow(2,3) = " + pow(2,3));
        System.out.println("pow(5,2) = " + pow(5,2)); 
        System.out.println("pow(3,4) = " + pow(3,4));
        System.out.println("pow(10,3) = " + pow(10,3));
        System.out.println("pow(7,0) = " + pow(7,0));
        System.out.println("pow(4,0.5) = " + pow(4,0.5));
        System.out.println("pow(-2,3) = " + pow(-2,3));
        System.out.println("pow(2,-2) = " + pow(2,-2));
        System.out.println("pow(9,0.5) = " + pow(9,0.5));
        System.out.println("pow(2.5,2) = " + pow(2.5,2));
    }
}
