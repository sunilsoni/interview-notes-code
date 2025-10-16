//package com.interview.notes.code.year.y2025.october.common.test;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
//public class VirtualThreadFanOutExample {
//    static String fetchPrimary() {
//        sleep(500);
//        return "User: Bhargav";
//    }
//
//    static String fetchSecondary1() {
//        sleep(800);
//        return "Profile: Developer";
//    }
//
//    static String fetchSecondary2() {
//        sleep(600);
//        return "Location: Hyderabad";
//    }
//
//    static void sleep(long ms) {
//        try { Thread.sleep(ms); } catch (InterruptedException e) { throw new RuntimeException(e); }
//    }
//
//    public static void main(String[] args) throws Exception {
//        long start = System.currentTimeMillis();
//        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
//            Future<String> f1 = executor.submit(VirtualThreadFanOutExample::fetchPrimary);
//            Future<String> f2 = executor.submit(VirtualThreadFanOutExample::fetchSecondary1);
//            Future<String> f3 = executor.submit(VirtualThreadFanOutExample::fetchSecondary2);
//
//            String result = f1.get() + ", " + f2.get() + ", " + f3.get();
//            System.out.println(result);
//        }
//        System.out.println("Completed in " + (System.currentTimeMillis() - start) + " ms");
//    }
//}