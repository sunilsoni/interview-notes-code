package com.interview.notes.code.year.y2025.november.common.test8;

interface Job extends Runnable {
}

class FileUploadJob implements Job {
    public void run() {
        System.out.println("Uploading file...");
    }
}

class DataSyncJob implements Job {
    public void run() {
        System.out.println("Syncing data...");
    }
}

public class Runner {
    public static void main(String[] args) {
        Job job = new FileUploadJob();
        job.run();

        job = new DataSyncJob();   // runtime substitution
        job.run();
    }
}
