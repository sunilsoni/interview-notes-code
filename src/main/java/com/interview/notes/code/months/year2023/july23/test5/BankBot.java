package com.interview.notes.code.months.year2023.july23.test5;

import java.util.Arrays;

public class BankBot {

    public static int[] solution(int[] balances, String[] requests) {
        int n = balances.length;
        int[] result = Arrays.copyOf(balances, n);

        for (int i = 0; i < requests.length; i++) {
            String request = requests[i];
            String[] parts = request.split(" ");

            if (parts.length < 3) {
                // Invalid request format
                return new int[]{-i - 1};
            }

            String action = parts[0];
            int accountFrom = Integer.parseInt(parts[1]) - 1; // Convert 1-based to 0-based index

            if (accountFrom < 0 || accountFrom >= n) {
                // Invalid account number
                return new int[]{-i - 1};
            }

            int amount = Integer.parseInt(parts[2]);

            switch (action) {
                case "withdraw":
                    if (result[accountFrom] < amount) {
                        // Insufficient balance for withdrawal
                        return new int[]{-i - 1};
                    }
                    result[accountFrom] -= amount;
                    break;
                case "deposit":
                    result[accountFrom] += amount;
                    break;
                case "transfer":
                    int accountTo = Integer.parseInt(parts[3]) - 1; // Convert 1-based to 0-based index

                    if (accountTo < 0 || accountTo >= n) {
                        // Invalid account number
                        return new int[]{-i - 1};
                    }

                    if (result[accountFrom] < amount) {
                        // Insufficient balance for transfer
                        return new int[]{-i - 1};
                    }
                    result[accountFrom] -= amount;
                    result[accountTo] += amount;
                    break;
                default:
                    // Invalid action in the request
                    return new int[]{-i - 1};
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] balances1 = {10, 100, 20, 50, 30};
        String[] requests1 = {"withdraw 2 10", "transfer 5 1 20", "deposit 5 20", "transfer 3 4 15"};
        int[] result1 = solution(balances1, requests1);
        System.out.println(Arrays.toString(result1)); // Output: [30, 90, 5, 65, 30]

        int[] balances2 = {20, 1000, 500, 40, 90};
        String[] requests2 = {"deposit 3 400", "transfer 1 2 30", "withdraw 4 50"};
        int[] result2 = solution(balances2, requests2);
        System.out.println(Arrays.toString(result2)); // Output: [-2]
    }
}
