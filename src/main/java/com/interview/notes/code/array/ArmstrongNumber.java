package com.interview.notes.code.array;


//This program will calculate the number based on the logic 1 power 3 + 5 power 3 + 3 power 3 = 153 and print whether the number is an Armstrong number or not.


public class ArmstrongNumber {
  public static void main(String[] args) {
    int number = 153;
    int originalNumber = number;
    int result = 0;
    int digits = String.valueOf(number).length();

    while (originalNumber != 0) {
      int remainder = originalNumber % 10;
      result += Math.pow(remainder, digits);
      originalNumber /= 10;
    }

    if (result == number) {
      System.out.println(number + " is an Armstrong number.");
    } else {
      System.out.println(number + " is not an Armstrong number.");
    }
  }


}
