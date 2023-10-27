package com.interview.notes.code.months.Oct23.test12;

class Bank {
    private String name;
    private int account_no;
    private float balance;

    public void getData() { 
        name = "ABC"; 
        account_no = 12345; 
        balance = 0;
    }

    private void deposit(float amt) {
        balance += amt; // balance = balance + amt
    }

    private float calculateAmount(float principal, float rate, int time) { 
        float simpleInterest = (principal * rate * time) / 100; // fixed spelling and replaced 'I' with '/'
        float amount = principal + simpleInterest; 
        return amount;
    }

    public void displayData() {
        System.out.println("Name: " + name);
        System.out.println("Account number: " + account_no); // fixed variable name
        System.out.println("Balance: " + balance); 
    }

    public static void main(String args[]) { 
        Bank bankObj = new Bank(); // Created an object of Bank class since non-static methods are being accessed
        bankObj.getData();
        bankObj.deposit(1500);
        float amount = bankObj.calculateAmount(300, 4, 2);
        System.out.println("Amount calculated for 300 at a rate of 4% for 2 years is: " + amount); // fixed 'System.out.printIn' to 'System.out.println'
        bankObj.displayData();
    }
}
