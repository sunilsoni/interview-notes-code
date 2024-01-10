package com.interview.notes.code.months.year2023.Sep23.test3;

public class Account implements Runnable {
    private int balance = 100;

    public static void main(String[] args) {
        Account sam = new Account();
        Account walton = new Account();
        Thread t1 = new Thread(sam);
        Thread t2 = new Thread(walton);

        t1.run();
        t2.run();
        System.out.println(sam.getBalance());
        System.out.println(walton.getBalance());
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public synchronized boolean withdraw(int amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void run() {
        withdraw(30);
    }
}
