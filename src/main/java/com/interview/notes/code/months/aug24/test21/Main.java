//package com.interview.notes.code.months.aug24.test21;
//
//import org.junit.runner.JUnitCore;
//import org.junit.runner.Result;
//import org.junit.runner.notification.Failure;
//
//public class Main {
//    public static void main(String[] args) {
//        // Run tests
//        Result result = JUnitCore.runClasses(TestJunit.class);
//
//        // Count passing/failing tests
//        int testsRun = result.getRunCount();
//        int testsFailed = result.getFailureCount();
//
//        // Print failures
//        for (Failure failure : result.getFailures()) {
//            System.out.println(failure.toString());
//        }
//
//        // Print required output
//        System.out.println("JUNIT_TESTS_RUN_COUNT: " + testsRun);
//        System.out.println("JUNIT_TESTS_FAILURE_COUNT: " + testsFailed);
//        System.out.println("JUNIT_ALL_TESTS_PASSING: " + result.wasSuccessful());
//
//        // Additional examples
//        Converter converter = new Converter();
//        try {
//            System.out.println("100 DTH to GJ: " + converter.convert(100, ConvertType.DTH_TO_GJ));
//            System.out.println("105 GJ to DTH: " + converter.convert(105, ConvertType.GJ_TO_DTH));
//            System.out.println("0 DTH to GJ: " + converter.convert(0, ConvertType.DTH_TO_GJ));
//            System.out.println("1000000 GJ to DTH: " + converter.convert(1000000, ConvertType.GJ_TO_DTH));
//        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//}
