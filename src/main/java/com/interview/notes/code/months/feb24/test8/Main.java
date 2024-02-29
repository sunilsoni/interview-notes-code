package com.interview.notes.code.months.feb24.test8;

import java.util.HashMap;
import java.util.Map;

class DigitalWallet {
    private String id;
    private String name;
    private String accessCode;
    private int balance;
    private boolean isAuthenticationRequired;

    public DigitalWallet(String id, String name) {
        this.id = id;
        this.name = name;
        this.balance = 0;
        this.isAuthenticationRequired = false;
    }

    public DigitalWallet(String id, String name, String accessCode) {
        this.id = id;
        this.name = name;
        this.accessCode = accessCode;
        this.balance = 0;
        this.isAuthenticationRequired = true;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return name;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isAuthenticationRequired() {
        return isAuthenticationRequired;
    }
}

class DigitalWalletTransaction {
    public void addMoney(DigitalWallet digitalWallet, int amount) throws TransactionException {
        if (digitalWallet.isAuthenticationRequired() && digitalWallet.getAccessCode() == null) {
            throw new TransactionException("User not authorized", "USER_NOT_AUTHORIZED");
        }
        if (amount <= 0) {
            throw new TransactionException("Amount should be greater than zero", "INVALID_AMOUNT");
        }
        int currentBalance = digitalWallet.getBalance();
        digitalWallet.setBalance(currentBalance + amount);
    }

    public void payMoney(DigitalWallet digitalWallet, int amount) throws TransactionException {
        if (digitalWallet.isAuthenticationRequired() && digitalWallet.getAccessCode() == null) {
            throw new TransactionException("User not authorized", "USER_NOT_AUTHORIZED");
        }
        if (amount <= 0) {
            throw new TransactionException("Amount should be greater than zero", "INVALID_AMOUNT");
        }
        if (digitalWallet.getBalance() < amount) {
            throw new TransactionException("Insufficient balance", "INSUFFICIENT_BALANCE");
        }
        int currentBalance = digitalWallet.getBalance();
        digitalWallet.setBalance(currentBalance - amount);
    }
}

class TransactionException extends Exception {
    private String errorCode;

    public TransactionException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

public class Main {
    public static void main(String[] args) {
        // Initialize wallets and transactions
        Map<String, DigitalWallet> wallets = new HashMap<>();
        wallets.put("1", new DigitalWallet("1", "Julia", "bff834a2c117a76d9ceb782f98e428686ca3c4ea"));
        wallets.put("2", new DigitalWallet("2", "Samantha"));

        DigitalWalletTransaction digitalWalletTransaction = new DigitalWalletTransaction();

        // Transactions based on input
        String[] transactions = {
                "1 pay 50", "1 add 100", "1 add 0", "1 pay 30", "2 add 500",
                "1 add -5", "1 add 1000", "1 pay -20", "1 pay 100", "1 add 720"
        };

        for (String transaction : transactions) {
            String[] parts = transaction.split(" ");
            String walletId = parts[0];
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
        for (Map.Entry<String, DigitalWallet> entry : wallets.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getUserName() + " " + entry.getValue().getBalance());
        }
    }
}
