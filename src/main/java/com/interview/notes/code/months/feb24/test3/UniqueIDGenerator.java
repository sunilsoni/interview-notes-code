package com.interview.notes.code.months.feb24.test3;

import java.util.Random;

//ServiceNow
public class UniqueIDGenerator {

    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static long lastTime = -1;
    private static int counter = 0;
    private static final Random random = new Random();

    public static synchronized String generateUniqueID() {
        long currentTime = System.currentTimeMillis();
        if (currentTime == lastTime) {
            counter++; // Increment counter if the same millisecond
        } else {
            counter = 0; // Reset counter if a new millisecond
            lastTime = currentTime;
        }

        // Use a combination of the current time, counter, and a random element
        int randomPart = random.nextInt(CHAR_SET.length());
        String timePart = toBase62((currentTime % 1000) * 100 + counter); // Simplified for demonstration
        char randomChar = CHAR_SET.charAt(randomPart);

        // Construct ID from time-based part and random character
        String uniqueID = timePart + randomChar;
        return uniqueID.length() > 6 ? uniqueID.substring(0, 6) : uniqueID;
    }

    private static String toBase62(long value) {
        StringBuilder sb = new StringBuilder();
        while (value > 0) {
            sb.append(CHAR_SET.charAt((int) (value % CHAR_SET.length())));
            value /= CHAR_SET.length();
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        // Example usage
        for (int i = 0; i < 10; i++) {
            System.out.println(generateUniqueID());
        }
    }
}
