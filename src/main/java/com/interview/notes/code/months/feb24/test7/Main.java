package com.interview.notes.code.months.feb24.test7;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize wallets and transactions
            Map<Integer, DigitalWallet> wallets = new HashMap<>();
            wallets.put(1, new DigitalWallet("1", "Julia", "bff834a2c117a76d9ceb782f98e428686ca3c4ea"));
            wallets.put(2, new DigitalWallet("2", "Samantha"));

            DigitalWalletTransaction digitalWalletTransaction = new DigitalWalletTransaction();

            // Transactions based on input
            String[] transactions = {
                    "1 pay 50", "1 add 100", "1 add 0", "1 pay 30", "2 add 500",
                    "1 add -5", "1 add 1000", "1 pay -20", "1 pay 100", "1 add 720"
            };

            for (String transaction : transactions) {
                String[] parts = transaction.split(" ");
                int walletId = Integer.parseInt(parts[0]);
                String action = parts[1];
                int amount = Integer.parseInt(parts[2]);

                DigitalWallet wallet = wallets.get(walletId);
                try {
                    if ("add".equals(action)) {
                        digitalWalletTransaction.addMoney(wallet, amount);
                        System.out.println("Wallet successfully credited.");
                    } else if ("pay".equals(action)) {
                        digitalWalletTransaction.payMoney(wallet, amount);
                        System.out.println("Wallet successfully debited.");
                    }
                } catch (TransactionException te) {
                    System.out.println(te.getErrorCode() + ": " + te.getMessage());
                }
            }

            // Print final balances
            for (Map.Entry<Integer, DigitalWallet> entry : wallets.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue().getUserName() + " " + entry.getValue().getBalance());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

// Define exceptions and classes DigitalWallet and DigitalWalletTransaction here as provided previously
