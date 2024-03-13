package com.interview.notes.code.months.march24.test7;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {
    public static int gradeSchoolFinished() {
        System.out.print("G");
        return 5;
    }

    public static int highSchoolFinished() {
        System.out.print("H");
        return 6;
    }

    public static int underGradFinished() {
        System.out.print("U");
        return 7;
    }

    public static int mastersFinished() {
        System.out.print("M");
        return 8;
    }

    public static int getAJob() {
        System.out.print("J");
        return 9;
    }

    public static void main(String[] args) {
        try {
            CompletableFuture<Integer> career = CompletableFuture.supplyAsync(() -> gradeSchoolFinished())
                    .thenApply(x -> highSchoolFinished())
                    .thenApply(y -> underGradFinished())
                    .thenApply(z -> mastersFinished())
                    .exceptionally(a -> getAJob());
            System.out.println(career.get());
        } catch (InterruptedException i) {
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            System.out.println("ExecutionException");
        } catch (CompletionException c) {
            System.out.println("CompletionException");
        }
    }
}
