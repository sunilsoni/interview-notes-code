package com.interview.notes.code.months.Oct23.test8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TypingCorrection {

    public static String correctTyping(String input) {
        return null;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        String[] inputs = {"comn..pany", "mmm..aieee...n", "some..other...example"};

        for (String input : inputs) {
            executorService.submit(new CorrectTypingTask(input));
        }

        executorService.shutdown();
    }

    public static class CorrectTypingTask implements Runnable {
        private final String input;

        public CorrectTypingTask(String input) {
            this.input = input;
        }

        @Override
        public void run() {
            String output = correctTyping(input);
            System.out.println(output);
        }
    }
}
