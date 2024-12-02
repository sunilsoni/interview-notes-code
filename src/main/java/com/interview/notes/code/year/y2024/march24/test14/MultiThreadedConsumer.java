package com.interview.notes.code.year.y2024.march24.test14;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedConsumer {
    private final ExecutorService executor;
    private final KafkaConsumer<String, String> consumer;

    public MultiThreadedConsumer(KafkaConsumer<String, String> consumer, int numThreads) {
        this.consumer = consumer;
        this.executor = Executors.newFixedThreadPool(numThreads);
    }

    public void startConsuming() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                executor.submit(new MessageProcessor(record));
            }
        }
    }

    public void shutdown() {
        executor.shutdown();
        consumer.close();
    }

    private static class MessageProcessor implements Runnable {
        private final ConsumerRecord<String, String> record;

        public MessageProcessor(ConsumerRecord<String, String> record) {
            this.record = record;
        }

        @Override
        public void run() {
            // Process the message asynchronously
            String message = record.value();
            // Perform message processing logic here
            System.out.println("Processed message: " + message);
        }
    }
}
