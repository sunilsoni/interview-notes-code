package com.interview.notes.code.months.feb24.test7;

class DigitalWallet {
    private String walletId;
    private String userName;
    private String userAccessCode;
    private int balance;

    public DigitalWallet(String walletId, String userName) {
        this.walletId = walletId;
        this.userName = userName;
        this.balance = 0;
    }

    public DigitalWallet(String walletId, String userName, String userAccessCode) {
        this(walletId, userName);
        this.userAccessCode = userAccessCode;
    }

    public String getWalletId() {
        return walletId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAccessCode() {
        return userAccessCode;
    }

    public int getBalance() {
        return balance;
    }

    public void setWalletBalance(int balance) {
        this.balance = balance;
    }

    public boolean hasAccessCode() {
        return userAccessCode != null && !userAccessCode.isEmpty();
    }
}