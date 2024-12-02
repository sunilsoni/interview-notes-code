package com.interview.notes.code.year.y2023.Oct23.test3;

import redis.clients.jedis.Jedis;

public class Throttler1 {
    private Jedis jedis;
    private int capacity;
    private int refillRate;
    private String key;

    public Throttler1(String redisHost, int redisPort, int capacity, int refillRate, String key) {
        this.jedis = new Jedis(redisHost, redisPort);


        this.capacity = capacity;
        this.refillRate = refillRate;
        this.key = key;
    }

    public boolean shouldAllowRequest() {
        long currentTime = System.currentTimeMillis() / 1000; // Current time in seconds

        // Get the last refill time and token count
        String lastRefillTimeStr = jedis.hget(key, "lastRefillTime");
        String tokenCountStr = jedis.hget(key, "tokenCount");

        if (lastRefillTimeStr == null || tokenCountStr == null) {
            // If it's the first request, initialize the token count and set the last refill time
            jedis.hset(key, "tokenCount", String.valueOf(capacity - 1));
            jedis.hset(key, "lastRefillTime", String.valueOf(currentTime));
            return true;
        }

        long lastRefillTime = Long.parseLong(lastRefillTimeStr);
        int tokenCount = Integer.parseInt(tokenCountStr);

        // Calculate how many tokens to refill based on the elapsed time
        int tokensToRefill = (int) ((currentTime - lastRefillTime) * refillRate);
        tokenCount = Math.min(capacity, tokenCount + tokensToRefill);

        // Check if there are tokens left
        if (tokenCount > 0) {
            jedis.hset(key, "tokenCount", String.valueOf(tokenCount - 1));
            jedis.hset(key, "lastRefillTime", String.valueOf(currentTime));
            return true;
        } else {
            // If not, do not allow the request
            return false;
        }
    }
}
