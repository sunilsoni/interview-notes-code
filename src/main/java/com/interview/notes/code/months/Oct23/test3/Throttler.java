package com.interview.notes.code.months.Oct23.test3;

import redis.clients.jedis.Jedis;

public class Throttler {
    private final Jedis jedis;
    private final int refillRate;  // tokens added per second
    private final int burstSize;   // max tokens bucket can hold

    public Throttler(Jedis jedis, int refillRate, int burstSize) {
        this.jedis = jedis;
        this.refillRate = refillRate;
        this.burstSize = burstSize;
    }

    // This function checks whether the request can go through
    public boolean allowRequest(String serviceId) {
        long currentTime = System.currentTimeMillis() / 1000; // current time in seconds
        
        // Atomic operation to update and check the bucket in Redis
        String script =
            "local tokens = redis.call('get', KEYS[1]) or 0" +
            "local lastRefillTime = redis.call('get', KEYS[2]) or 0" +
            "local newTokens = (ARGV[1] - lastRefillTime) * ARGV[2]" +
            "tokens = math.min(tokens + newTokens, ARGV[3])" +
            "if tokens < 1 then return false else tokens = tokens - 1 end" +
            "redis.call('set', KEYS[1], tokens)" +
            "redis.call('set', KEYS[2], ARGV[1])" +
            "return true";

        return (boolean) jedis.eval(script, 
            2, 
            "tokens:" + serviceId, 
            "lastRefill:" + serviceId, 
            String.valueOf(currentTime), 
            String.valueOf(refillRate), 
            String.valueOf(burstSize)
        );
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        Throttler throttler = new Throttler(jedis, 10, 100);  // 10 requests per second, up to 100 in burst

        String serviceId = "service123";
        if (throttler.allowRequest(serviceId)) {
            // Process the request
        } else {
            // Throttle the request
        }
    }
}

// Main execution


