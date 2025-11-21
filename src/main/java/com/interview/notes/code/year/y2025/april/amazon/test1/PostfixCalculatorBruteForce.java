package com.interview.notes.code.year.y2025.april.amazon.test1;

public class PostfixCalculatorBruteForce {
    public static double calculate(String[] tokens) {
        // Create a mutable array to store our working values
        String[] workingArray = tokens.clone();

        // Continue processing until we have only one number left
        while (workingArray.length > 1) {
            // Find the first operator from left to right
            int operatorIndex = findFirstOperator(workingArray);

            if (operatorIndex == -1) {
                throw new IllegalArgumentException("Invalid expression: no operator found");
            }

            // Get the two numbers before the operator
            if (operatorIndex < 2) {
                throw new IllegalArgumentException("Invalid expression: insufficient operands");
            }

            // Perform the calculation
            double num1 = Double.parseDouble(workingArray[operatorIndex - 2]);
            double num2 = Double.parseDouble(workingArray[operatorIndex - 1]);
            String operator = workingArray[operatorIndex];

            double result = performOperation(num1, num2, operator);

            // Create new array with the result replacing the operation
            workingArray = createNewArray(workingArray, operatorIndex, result);
        }

        return Double.parseDouble(workingArray[0]);
    }

    private static int findFirstOperator(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            if (isOperator(tokens[i])) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") ||
                token.equals("*") || token.equals("/");
    }

    private static double performOperation(double num1, double num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    private static String[] createNewArray(String[] original, int operatorIndex, double result) {
        String[] newArray = new String[original.length - 2];

        // Copy elements before the operation
        if (operatorIndex - 2 >= 0) System.arraycopy(original, 0, newArray, 0, operatorIndex - 2);

        // Add the result
        newArray[operatorIndex - 2] = String.valueOf(result);

        // Copy remaining elements
        if (original.length - (operatorIndex + 1) >= 0)
            System.arraycopy(original, operatorIndex + 1, newArray, operatorIndex + 1 - 2, original.length - (operatorIndex + 1));

        return newArray;
    }

    // Test the calculator
    public static void main(String[] args) {
        // Test cases
        String[][] testCases = {
                {"2", "3", "+", "5", "*"},           // (2 + 3) * 5 = 25
                {"16", "5", "+"},                    // 16 + 5 = 21
                {"10", "5", "/"},                    // 10 / 5 = 2
                {"4", "5", "2", "*", "+"},          // 4 + (5 * 2) = 14
                {"3", "4", "2", "+", "*"}           // 3 * (4 + 2) = 18
        };

        for (String[] test : testCases) {
            try {
                System.out.print("Expression: ");
                for (String token : test) {
                    System.out.print(token + " ");
                }
                double result = calculate(test);
                System.out.println("= " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
