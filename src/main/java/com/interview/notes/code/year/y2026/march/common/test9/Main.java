package com.interview.notes.code.year.y2026.march.common.test9;

import java.util.concurrent.CompletableFuture;

class Main {
    static String api1() { sleep(2000); return "API1"; }
    static String api2() { sleep(3000); return "API2"; }

    static void sleep(long ms){
        try { Thread.sleep(ms); } catch(Exception e){}
    }

    public static void main(String[] args) {
        var f1 = CompletableFuture.supplyAsync(Main::api1);
        var f2 = CompletableFuture.supplyAsync(Main::api2);

        var result = f1.thenCombine(f2, (r1, r2) -> r1 + " " + r2).join();

        System.out.println(result);
    }
}