package com.interview.notes.code.year.y2025.may.hackerank.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class User implements Comparable<User> {
    private final Integer id;
    private final String name;
    private final Set<User> followers;
    private final Set<User> following;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.followers = new TreeSet<>();
        this.following = new TreeSet<>();
    }

    public String getName() {
        return name;
    }

    public void post(String content) {
        System.out.println(this.name + " posted: \"" + content + "\".");
        notifyFollowers(content);
    }

    public void notifyFollowers(String message) {
        for (User follower : followers) {
            follower.update(message, this);
        }
    }

    public void update(String message, User followee) {
        System.out.println(this.name + " received notification: " + followee.getName() + " posted: \"" + message + "\".");
    }

    public void follow(User user) {
        if (this.following.add(user)) {
            user.addFollowers(this);
        }
    }

    public void unfollow(User user) {
        if (this.following.remove(user)) {
            user.removeFollowers(this);
        }
    }

    public void addFollowers(User user) {
        this.followers.add(user);
    }

    public void removeFollowers(User user) {
        this.followers.remove(user);
    }

    @Override
    public int compareTo(User otherUser) {
        return this.id.compareTo(otherUser.id);
    }
}

class Platform {
    private final Map<Integer, User> users;

    public Platform() {
        this.users = new HashMap<>();
    }

    public void addUser(Integer id, String name) {
        if (!users.containsKey(id)) {
            User newUser = new User(id, name);
            users.put(id, newUser);
            System.out.println(name + " added successfully.");
        }
    }

    public void follow(Integer followerId, Integer followeeId) {
        User follower = users.get(followerId);
        User followee = users.get(followeeId);
        if (follower != null && followee != null && !follower.equals(followee)) {
            follower.follow(followee);
            System.out.println(follower.getName() + " is now following " + followee.getName() + ".");
        }
    }

    public void unfollow(Integer followerId, Integer followeeId) {
        User follower = users.get(followerId);
        User followee = users.get(followeeId);
        if (follower != null && followee != null && !follower.equals(followee)) {
            follower.unfollow(followee);
            System.out.println(follower.getName() + " has unfollowed " + followee.getName() + ".");
        }
    }

    public void post(Integer userId, String content) {
        User user = users.get(userId);
        if (user != null) {
            user.post(content);
        }
    }
}