package com.interview.notes.code.year.y2023.june23.test2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/*
 * Create TransactionException, Account, and Bank classes here.
 *
 *

ANF Backend Java Test Banking System

Banks can hold many accounts. Once authenticated a banking user can do 2 types of transactions :
•  Adding money to your account
•  Withdraw money from your account.
Both the transactions are validated before being processed. For an invalid transaction the following error
code and the error message are displayed:
•   USER_NOT_AUTHORIZEEf. The error code reports the unauthorized transaction, i.e„ if there is no access
token for the account. The error message should be User not authorized.
•  INVALID_AMOUNT. The error code reports the attempt of adding or paying zero or negative amount. The
error message should be Amount should be greater than zero.
•  INSUFFICIENT_BALANCE: The error code reports the attempt of making a payment greater than the
available balance. The error message should be Insufficient balance.
In this challenge, you are building the account workflow by writing the complete implementation of the
following three classes:
1. The TransactionException class should implement:
o The constructor TransactionExceptionfString message, String errorCode).
o The method StringgetErrorCode()to return the error code of the exception being thrown.
2. The Account class should implement:
o The constructor AccountfString accountld, String userName).
o The constructor AccountfString accountld. String userName, String userAccessToken).


2.  The Account class should implement:
o The constructor Account(String accountld, String userName).
o The constructor AccountfString accountld. String userName, String userAccessToken).
o The method StringgetAccountldf) to return the account ID.
o The method StringgetUserNamef) to return the owner's user name.
o The method StringgetUserAccessTokenf) to return the access token.
o The method intgetAccountBalance()to return the account balance.
o The method void setAccountBalancefint accountBalance) to update the account balance.

3. The Bank class should implement:
o The method voidaddAccount(Accountaccount) to open an account in the Bank.
o The method void addMoney(Account account, int amount) to add money to the account. It should also
throw the required exceptions for any invalid transactions.
o The method void withdrawMoney(Account account, int amount) to withdraw money from the account.
It should also throw the required exceptions for any invalid transactions.
The locked stub code in the editor validates the correctness of the TransactionException, Account, and
Bank class implementations by first creating the authorized and unauthorized accounts and then
processing the following two type of transactions:
•  accountID add amount. This transaction is handled by the method Bank.addMoneyfaccount, amount).
•  accountID pay amount. This transaction is handled by the method Bank.withdrawMoney(account, amount).
After processing all the queries, the locked stub code prints the Account ID, user name, and the balance
amount for each of the account.



Constraint:


•   7 < numberOfAccounts < 100
•  1 < numberOfTransactions <2 * 10



    Sample Input

    2
1 Julia bff834a2c117a76d9ceb782f98e428686ca3c
2 Rob bff834a2c117a76d9ceb782f98e428686ca5c
7
1 add 100
1 add 0
1 withdraw 30
2 add 500
1 add 1000
1 withdraw 100
1 add 720


Sample Output:

Account successfully credited.
INVALID_AMOUNT: Amount should be greater than zero.
Account successfully debited.
Account successfully credited.
Account successfully credited.
Account successfully debited.
Account successfully credited.
1 Julia 1690
2 Rob 500


 */

public class Solution {
    private static final Scanner INPUT_READER = new Scanner(System.in);
    private static final Bank bank = new Bank();


    public static void main(String[] args) {
        int numberOfAccounts = Integer.parseInt(INPUT_READER.nextLine());
        while (numberOfAccounts-- > 0) {
            String[] accounts = INPUT_READER.nextLine().split(" ");
            Account account;

            if (accounts.length == 2) {
                account = new Account(accounts[0], accounts[1]);
            } else {
                account = new Account(accounts[0], accounts[1], accounts[2]);
            }

            bank.addAccount(account);
        }

        int numberOfTransactions = Integer.parseInt(INPUT_READER.nextLine());
        while (numberOfTransactions-- > 0) {
            String[] transaction = INPUT_READER.nextLine().split(" ");
            Account account = bank.getAccount(transaction[0]);

            if (transaction[1].equals("add")) {
                try {
                    bank.addMoney(account, Integer.parseInt(transaction[2]));
                    System.out.println("Account successfully credited.");
                } catch (TransactionException ex) {
                    System.out.println(ex.getErrorCode() + ": " + ex.getMessage() + ".");
                }
            } else {
                try {
                    bank.withdrawMoney(account, Integer.parseInt(transaction[2]));
                    System.out.println("Account successfully debited.");
                } catch (TransactionException ex) {
                    System.out.println(ex.getErrorCode() + ": " + ex.getMessage() + ".");
                }
            }
        }

        System.out.println(bank);


    }
}

class Account {
    private String accountId;
    private String userName;
    private String userAccessToken;
    private int accountBalance;

    public Account(String accountId, String userName) {
        this.accountId = accountId;
        this.userName = userName;

    }

    public Account(String accountId, String userName, String userAccessToken) {
        this.accountId = accountId;
        this.userName = userName;
        this.userAccessToken = userAccessToken;

    }

    public String getAccountId() {
        return this.accountId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserAccessToken() {
        return this.userAccessToken;
    }

    public int getAccountBalance() {
        return this.accountBalance;
    }

    /*
   public String getAccountId(){
      return  this.accountId=accountId;
   }
   public String getUserName(){
      return   this.userName=userName;
   }
   public String getUserAccessToken(){
      return   this.userAccessToken=userAccessToken;
   }*/
    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }


}

class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public void addMoney(Account account, int amount) throws TransactionException {

        if (account.getUserAccessToken() == null) {
            throw new TransactionException("User not authorized", "USER_NOT_AUTHORIZED");
        }

        if (amount <= 0) {
            throw new TransactionException("Amount should be greater than zero", "INVALID_AMOUNT");
        }
        account.setAccountBalance(account.getAccountBalance() + amount);
    }

    public void withdrawMoney(Account account, int amount) throws TransactionException {
        if (account.getUserAccessToken() == null) {
            throw new TransactionException("User not authorized", "USER_NOT_AUTHORIZED");
        }

        if (amount <= 0) {
            throw new TransactionException("Amount should be greater than zero", "INVALID_AMOUNT");
        }

        if (account.getAccountBalance() < amount) {
            throw new TransactionException("Insufficient balance", "INSUFFICIENT_BALANCE");

        }
        account.setAccountBalance(account.getAccountBalance() - amount);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("\n");
        // b.append("\n");
        for (Map.Entry<String, Account> e : accounts.entrySet()) {
            Account account = e.getValue();

            b.append(account.getAccountId() + " ");
            b.append(account.getUserName() + " ");
            b.append(account.getAccountBalance());
            b.append("\n");

        }
        return b.toString();
    }
}

class TransactionException extends Exception {
    private String errorCode;

    public TransactionException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
}

