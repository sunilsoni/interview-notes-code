package com.interview.notes.code.months.march24.test21;

public class Main {
    public static void main(String[] args) {
        // Create a user
        User user = new User("JohnDoe");

        // Create a tweet
        //  Tweet tweet = new Tweet(user);

        // Add various types of media to the tweet
//        tweet.addMedia(new Text("Exploring the Grand Canyon!"));
//        tweet.addMedia(new Image("/images/grand_canyon.jpg"));
//        tweet.addMedia(new Video("/videos/grand_canyon.mp4"));
//
//        // Add tweet to the user's list of tweets
//        user.getTweets().add(tweet);

        // Render and display the tweet's media
        MediaRenderer renderer = new ConsoleMediaRenderer();
        //  tweet.displayTweet(renderer);

        // Assuming the User class has a method to retrieve tweets
        // Display all tweets of the user
//        for (Tweet userTweet : user.getTweets()) {
//            System.out.println("Tweet by " + userTweet.getAuthor().getUsername());
//            userTweet.displayTweet(renderer);
//            System.out.println("----------------------------------");
//        }
    }
}
