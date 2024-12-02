//package com.interview.notes.code.months.oct24.tst24;
//
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//import org.apache.catalina.Manager;
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Data
//@RequiredArgsConstructor
//class UserService {
//    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 5; // 5 hours in milliseconds
//    private final UserRepository userRepository;
//
//    // Cache structure: Maps Manager to a CacheEntry (list of users + timestamp)
//    private final Map<Manager, CacheEntry> cache = new ConcurrentHashMap<>();
//
//    public List<User> getUsersBy(final Manager manager) {
//        CacheEntry cachedEntry = cache.get(manager);
//
//        // Check if cache entry is valid
//        if (cachedEntry != null && !cachedEntry.isExpired()) {
//            return cachedEntry.getUsers();
//        }
//
//        // Retrieve data from repository and update the cache
//        List<User> users = userRepository.findUsers(manager);
//        cache.put(manager, new CacheEntry(users));
//        return users;
//    }
//
//    // Nested class for cache entry
//    private static class CacheEntry {
//        private final List<User> users;
//        private final long timestamp;
//
//        public CacheEntry(List<User> users) {
//            this.users = users;
//            this.timestamp = System.currentTimeMillis();
//        }
//
//        public List<User> getUsers() {
//            return users;
//        }
//
//        public boolean isExpired() {
//            return (System.currentTimeMillis() - timestamp) > EXPIRATION_TIME;
//        }
//    }
//}
