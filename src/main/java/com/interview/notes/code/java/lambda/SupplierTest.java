package com.interview.notes.code.java.lambda;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public class SupplierTest {

    public static void main(String[] args) {
        Supplier<Long> randomId = () -> new Random().nextLong();
        Supplier<UUID> uuid = () -> UUID.randomUUID();

        Trade trade = new Trade();
        populate(trade, randomId);
        populate(trade, uuid);
    }

    static <R> void populate(Trade t, Supplier<R> supplier) {
        t.tradeDate = new Date();
        t.tradeId = (String) supplier.get();
        t.location = "XYZ Hub";
    }

    static class Trade {
        String tradeId;
        Date tradeDate;
        String location;
    }
}