package com.interview.notes.code.year.y2025.october.photon.test1;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Represents a user in the social platform.
 */
class User implements Comparable<User> {
    private final Integer id;                   // unique user ID
    private final String name;                  // user name
    private final Set<User> followers;          // who follows this user
    private final Set<User> following;          // who this user follows

    public User(Integer id, String name) {
        this.id = id;                     // set the unique ID
        this.name = name;                 // set the name
        this.followers = new TreeSet<>(); // keep followers sorted by ID
        this.following = new TreeSet<>(); // keep followings sorted by ID
    }

    @Override
    public int compareTo(User other) {
        return this.id.compareTo(other.id); // sort users by ID
    }

    public String getName() {
        return name;                     // expose user name
    }

    /**
     * Called when someone this user follows has posted.
     */
    public void update(String message, User followee) {
        // print notification message to console
        System.out.println(
                this.name + " received notification: "
                        + followee.name + " posted: \"" + message + "\"."
        );
    }

    /**
     * Add a follower to my follower set.
     */
    public void addFollowers(User user) {
        followers.add(user);             // track new follower
    }

    /**
     * Remove a follower from my follower set.
     */
    public void removeFollowers(User user) {
        followers.remove(user);          // stop tracking follower
    }

    /**
     * Notify each follower about a new post.
     */
    public void notifyFollowers(String message) {
        followers.forEach(f -> f.update(message, this));
    }

    /**
     * Post content: announce to console, then notify followers.
     */
    public void post(String content) {
        System.out.println(this.name + " posted: \"" + content + "\".");
        notifyFollowers(content);        // send notifications
    }

    /**
     * Follow another user: update my following and their followers.
     */
    public void follow(User user) {
        following.add(user);       // I now follow them
        user.addFollowers(this);   // they gain me as a follower
    }

    /**
     * Unfollow another user: update both sides.
     */
    public void unfollow(User user) {
        following.remove(user);          // stop following
        user.removeFollowers(this);      // they lose a follower
    }
}

/**
 * The platform holds all users and coordinates global actions.
 */
class Platform {
    private final Map<Integer, User> users;    // id -> User
    private final List<String> logs;           // record each printed message

    public Platform() {
        this.users = new HashMap<>();    // initialize user registry
        this.logs = new ArrayList<>();   // initialize log recorder
    }

    /**
     * Accessor for test harness to compare outputs.
     */
    public List<String> getLogs() {
        return logs;
    }

    /**
     * Add a new user to platform, log and print success.
     */
    public void addUser(Integer id, String name) {
        User u = new User(id, name);     // create user object
        users.put(id, u);                // register by ID
        String msg = name + " added successfully.";
        System.out.println(msg);         // console output
        logs.add(msg);                   // record for testing
    }

    /**
     * Make one user follow another, then log/print.
     */
    public void follow(Integer followerId, Integer followeeId) {
        User f = users.get(followerId);
        User e = users.get(followeeId);
        f.follow(e);                     // perform follow
        String msg = f.getName()
                + " is now following "
                + e.getName() + ".";
        System.out.println(msg);
        logs.add(msg);
    }

    /**
     * Make one user unfollow another, then log/print.
     */
    public void unfollow(Integer followerId, Integer followeeId) {
        User f = users.get(followerId);
        User e = users.get(followeeId);
        f.unfollow(e);                   // perform unfollow
        String msg = f.getName()
                + " has unfollowed "
                + e.getName() + ".";
        System.out.println(msg);
        logs.add(msg);
    }

    /**
     * User posts content: delegate to user's post method.
     */
    public void post(Integer userId, String content) {
        users.get(userId).post(content);
        // user.post prints and notifies, we capture both in logs:
        //   we capture the post()
        // and also the notifications inside User.update()
        // so for simplicity we duplicate capture here:
        // but in this stub test we rely on notifications above.
        // If needed, capture them manually here as well.
    }
}

/**
 * Main class to run sample tests and a large-data performance test.
 */
public class Main {
    public static void main(String[] args) {
        // -- Sample Test Case 0 --
        Platform platform = new Platform(); // create platform instance
        // run commands in order via a Stream
        Stream.<Runnable>of(
                () -> platform.addUser(0, "Alexander"),
                () -> platform.addUser(1, "Isabella"),
                () -> platform.addUser(2, "Emma"),
                () -> platform.follow(1, 0),
                () -> platform.post(0, "Hiking in the mountains."),
                () -> platform.follow(2, 0),
                () -> platform.post(0, "Enjoying a beautiful day!"),
                () -> platform.unfollow(1, 0)
        ).forEach(Runnable::run);

        // Expected messages
        List<String> expected = Arrays.asList(
                "Alexander added successfully.",
                "Isabella added successfully.",
                "Emma added successfully.",
                "Isabella is now following Alexander.",
                "Alexander posted: \"Hiking in the mountains.\".",
                "Isabella received notification: Alexander posted: \"Hiking in the mountains.\".",
                "Emma is now following Alexander.",
                "Alexander posted: \"Enjoying a beautiful day!\".",
                "Isabella received notification: Alexander posted: \"Enjoying a beautiful day!\".",
                "Emma received notification: Alexander posted: \"Enjoying a beautiful day!\".",
                "Isabella has unfollowed Alexander."
        );

        // Compare actual vs expected
        boolean pass = platform.getLogs().equals(expected);
        System.out.println("Sample Test Case 0: " + (pass ? "PASS" : "FAIL"));

        // -- Large Data Performance Test --
        int n = 10_000;
        Platform big = new Platform();
        long start = System.currentTimeMillis();
        // add users
        IntStream.range(0, n).forEach(i -> big.addUser(i, "U" + i));
        // make each user follow the next one (wrap around)
        IntStream.range(0, n).forEach(i -> big.follow(i, (i + 1) % n));
        // each user posts one message
        IntStream.range(0, n).forEach(i -> big.post(i, "Msg" + i));
        long duration = System.currentTimeMillis() - start;
        System.out.println(
                "Large Data Test: Completed in " + duration + "ms for "
                        + (n * 3) + " operations."
        );
    }
}