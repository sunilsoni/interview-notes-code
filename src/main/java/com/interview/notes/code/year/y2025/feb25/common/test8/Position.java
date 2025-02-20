package com.interview.notes.code.year.y2025.feb25.common.test8;

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

    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String sym) { this.symbol = sym; }

    public long getNumberOfShares() { return numShares; }
    public void setNumberOfShares(long shares) { this.numShares = shares; }

    @Override
    public String toString() {
        return String.format("Position [accountId=%s, symbol=%s, numShares=%s]", accountId, symbol, numShares);
    }
}
