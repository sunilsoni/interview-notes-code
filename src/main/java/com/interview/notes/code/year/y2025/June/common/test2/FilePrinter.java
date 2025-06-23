package com.interview.notes.code.year.y2025.June.common.test2;

import java.io.File;
import java.util.concurrent.*;

public class FilePrinter {

    public static void main(String[] args) throws InterruptedException {
        File dir = new File("your-directory-path-here");
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) return;

        BlockingQueue<File> queue = new LinkedBlockingQueue<>();
        for (File file : files) {
            if (file.isFile()) queue.add(file);
        }

        ExecutorService executor = Executors.newFixedThreadPool(5);
        Runnable worker = () -> {
            while (true) {
                File file = queue.poll();
                if (file == null) break;
                System.out.println(file.getName());
            }
        };

        for (int i = 0; i < 5; i++) {
            executor.submit(worker);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}





