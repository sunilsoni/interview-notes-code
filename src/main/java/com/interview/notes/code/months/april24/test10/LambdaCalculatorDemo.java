package com.interview.notes.code.months.april24.test10;

@FunctionalInterface
interface MyCalculator {
  int calculate(int x, int y);
}

public class LambdaCalculatorDemo {

  public static void main(String[] args) {
    // Addition using lambda expression
    MyCalculator add = (x, y) -> x + y;
    System.out.println("Sum: " + add.calculate(5, 3));

    // Subtraction using lambda expression
    MyCalculator subtract = (x, y) -> x - y;
    System.out.println("Difference: " + subtract.calculate(10, 4));

    // Multiplication using lambda expression
    MyCalculator multiply = (x, y) -> x * y;
    System.out.println("Product: " + multiply.calculate(2, 6));

    // Division using lambda expression
    MyCalculator divide = (x, y) -> {
      if (y == 0) {
        throw new ArithmeticException("Division by zero");
      }
      return x / y;
    };
    System.out.println("Quotient: " + divide.calculate(12, 3));
  }
}
