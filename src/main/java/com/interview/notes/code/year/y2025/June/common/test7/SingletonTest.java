package com.interview.notes.code.year.y2025.June.common.test7;

public class SingletonTest {
    public static void main(String[] args) {
        // Test class-based singleton
        testClassBasedSingleton();
        
        // Test enum singleton
        testEnumSingleton();
    }
    
    private static void testClassBasedSingleton() {
        System.out.println("Testing Class-based Singleton:");
        
        // Create multiple threads
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Singleton instance = Singleton.getInstance();
                System.out.println("Thread " + Thread.currentThread().getId() + 
                    " - Instance Hash: " + instance.hashCode());
            }).start();
        }
    }
    
    private static void testEnumSingleton() {
        System.out.println("\nTesting Enum Singleton:");
        
        // Create multiple threads
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                SingletonEnum instance = SingletonEnum.INSTANCE;
                System.out.println("Thread " + Thread.currentThread().getId() + 
                    " - Instance Hash: " + instance.hashCode() + 
                    " - Data: " + instance.getData());
            }).start();
        }
    }
}
