package com.interview.notes.code.year.y2025.jan24.test10;

// Position class
class Position {
    private String accountId;
    private String symbol;
    private long numShares;

    public Position(String positionStr) {
        String[] tokens = positionStr.split(",");
        this.accountId = tokens[0];
        this.symbol = tokens[1];
        this.numShares = Long.valueOf(tokens[2]);
    }

    public String getAccountId() {
        return accountId;
    }

    public String getSymbol() {
        return symbol;
    }

    public long getNumberOfShares() {
        return numShares;
    }

    @Override
    public String toString() {
        return String.format("Position [accountId=%s, symbol=%s, numShares=%d]",
                accountId, symbol, numShares);
    }
}