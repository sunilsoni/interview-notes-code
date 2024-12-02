package com.interview.notes.code.year.y2023.Oct23.test4;

/**
 * 1. There's a non-static member `args[]` declared in the class `TestApp` with two String values "1" and "2".
 * <p>
 * 2. A `main` method is also declared which takes a parameter `args[]`. When you run a Java program from the command line, this `args[]` receives the command line arguments.
 * <p>
 * 3. Inside the `main` method, there's a condition checking if the length of `args[]` (the parameter) is greater than 0. If true, it prints the length of `args[]`.
 * <p>
 * Now, if you run this program without providing any command line arguments, the `args[]` parameter of the main method will have a length of 0. Hence the answer would be:
 * <p>
 * A. The program compiles but prints nothing.
 * <p>
 * But, if you were to provide any command line arguments while running, it would print the count of those arguments.
 * <p>
 * For the code as it is, the answer is A.
 */
class TestApp {

    String args[] = {
            "1",
            "2"
    };

    public static void main(String args[]) {
        if (args.length > 0)
            System.out.println(args.length);
    }
}