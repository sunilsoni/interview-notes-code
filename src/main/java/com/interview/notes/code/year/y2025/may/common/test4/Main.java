package com.interview.notes.code.year.y2025.may.common.test4;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class AccountDetails {
    private final String accountNo;
    private final double balance;

    public AccountDetails(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDetails that = (AccountDetails) o;
        return Objects.equals(accountNo, that.accountNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo);
    }
}

public class Main {
    public static void main(String[] args) {
        Map<AccountDetails, String> map = new HashMap<>();
        map.put(new AccountDetails("ACC001", 1000.0), "Active");
        map.put(new AccountDetails("ACC002", 2000.0), "Inactive");
        map.put(new AccountDetails("ACC003", 3000.0), "Active");

        // Filter by account number
        String searchAccountNo = "ACC001";
        Map<AccountDetails, String> filteredByAccountNo = map.entrySet().stream()
                .filter(entry -> entry.getKey().getAccountNo().equals(searchAccountNo))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

        // Filter by status
        String searchStatus = "Active";
        Map<AccountDetails, String> filteredByStatus = map.entrySet().stream()
                .filter(entry -> entry.getValue().equals(searchStatus))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

        // Filter by balance
        double minBalance = 2000.0;
        Map<AccountDetails, String> filteredByBalance = map.entrySet().stream()
                .filter(entry -> entry.getKey().getBalance() >= minBalance)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }
}
