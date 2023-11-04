package com.interview.notes.code.months.nov23.test2;

public class PerfectNumbers {

    public static void main(String[] args) {
        int N = 10000;  // Example value, you can change this.
        for (int i = 1; i <= N; i++) {
            if (isPerfect(i)) {
                System.out.println(i + " is a perfect number.");
            }
        }
    }

    /**
     * This method checks whether a given number num is perfect or not.
     * We initialize a variable sum to 0. This will hold the sum of the divisors of the number.
     * We then iterate from 1 to num/2 using a for loop. We only need to go up to num/2 because no divisor (other than the number itself) can be larger than half the number.
     * Inside the loop, we use the modulo operator % to check if i is a divisor of num. If it is, we add i to sum.
     * After the loop completes, we check if sum is equal to num. If they're equal, it's a perfect number, and the method returns true. Otherwise, it returns false
     *
     * @param num
     * @return
     */
    public static boolean isPerfect(int num) {
        int sum = 0;
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum == num;
    }

    /**
     * The runtime complexity of the isPerfect() method will vary depending on the implementation of the method. The simplest implementation, which checks each number from 1 to half of the number's value to see if it is a divisor, will have a runtime complexity of O(n). This is because the number of times the loop iterates is proportional to the value of n.
     * <p>
     * The modified implementation, which uses the Sieve of Eratosthenes to find all of the prime numbers up to the value of n, will have a runtime complexity of O(n log log n). This is because the Sieve of Eratosthenes has a runtime complexity of O(n log log n).
     * <p>
     * The following table shows the runtime complexity of the two implementations of the isPerfect() method:
     * <p>
     * Implementation	Runtime complexity
     * Simple implementation	O(n)
     * Modified implementation	O(n log log n)
     * The modified implementation is more efficient than the simple implementation for large numbers. However, it is also more complex to implement.
     * <p>
     * In general, the runtime complexity of the isPerfect() method will be proportional to the size of the number being tested. This is because the method needs to check all of the number's divisors in order to determine if it is perfect.
     *
     * @param num
     * @return
     */
    public static boolean isPerfect2(int num) {
        boolean[] primes = new boolean[num + 1];
        for (int i = 2; i <= num; i++) {
            primes[i] = true;
        }

        for (int i = 2; i * i <= num; i++) {
            if (primes[i]) {
                for (int j = i * i; j <= num; j += i) {
                    primes[j] = false;
                }
            }
        }

        int sum = 0;
        for (int i = 2; i <= num; i++) {
            if (primes[i] && num % i == 0) {
                sum += i;
            }
        }
        return sum == num;
    }
}
