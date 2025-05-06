package com.interview.notes.code.year.y2025.may.amazon.test1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Movie {
    String title;
    LocalDate releaseDate;

    Movie(String title, LocalDate releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return title;
    }
}

class SimpleMovieFinder {
    private List<Movie> movies;

    SimpleMovieFinder() {
        this.movies = new ArrayList<>();
    }

    void addMovie(String title, LocalDate releaseDate) {
        movies.add(new Movie(title, releaseDate));
    }

    Movie findMovie(LocalDate searchDate) {
        if (movies.isEmpty()) {
            return null;
        }

        // First check for exact match
        for (Movie movie : movies) {
            if (movie.releaseDate.equals(searchDate)) {
                return movie;
            }
        }

        // If no exact match, find closest date
        Movie closestMovie = movies.get(0);
        long smallestDiff = Math.abs(searchDate.toEpochDay() - movies.get(0).releaseDate.toEpochDay());

        for (Movie movie : movies) {
            long currentDiff = Math.abs(searchDate.toEpochDay() - movie.releaseDate.toEpochDay());
            if (currentDiff < smallestDiff) {
                smallestDiff = currentDiff;
                closestMovie = movie;
            }
        }

        return closestMovie;
    }
}

public class Main {
    public static void main(String[] args) {
        SimpleMovieFinder finder = new SimpleMovieFinder();

        // Add some test movies
        finder.addMovie("Avatar", LocalDate.of(2009, 12, 18));
        finder.addMovie("Inception", LocalDate.of(2010, 7, 16));
        finder.addMovie("The Dark Knight", LocalDate.of(2008, 7, 18));

        // Test Case 1: Exact match
        Movie result1 = finder.findMovie(LocalDate.of(2009, 12, 18));
        System.out.println("Test 1 - Exact match");
        System.out.println("Expected: Avatar");
        System.out.println("Got: " + result1);
        System.out.println("Result: " + (result1.toString().equals("Avatar") ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 2: Closest match
        Movie result2 = finder.findMovie(LocalDate.of(2009, 12, 20));
        System.out.println("Test 2 - Closest match");
        System.out.println("Expected: Avatar");
        System.out.println("Got: " + result2);
        System.out.println("Result: " + (result2.toString().equals("Avatar") ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 3: Date before all movies
        Movie result3 = finder.findMovie(LocalDate.of(2008, 1, 1));
        System.out.println("Test 3 - Date before all movies");
        System.out.println("Expected: The Dark Knight");
        System.out.println("Got: " + result3);
        System.out.println("Result: " + (result3.toString().equals("The Dark Knight") ? "PASS" : "FAIL"));
        System.out.println();

        // Test Case 4: Date after all movies
        Movie result4 = finder.findMovie(LocalDate.of(2011, 1, 1));
        System.out.println("Test 4 - Date after all movies");
        System.out.println("Expected: Inception");
        System.out.println("Got: " + result4);
        System.out.println("Result: " + (result4.toString().equals("Inception") ? "PASS" : "FAIL"));
        System.out.println();

        // Test with large dataset
        System.out.println("Testing with large dataset...");
        for (int i = 0; i < 1000; i++) {
            finder.addMovie(
                "Movie" + i, 
                LocalDate.of(2000 + i/100, 1 + i%12, 1 + i%28)
            );
        }
        Movie result5 = finder.findMovie(LocalDate.of(2005, 6, 15));
        System.out.println("Large dataset test completed successfully");
    }
}
