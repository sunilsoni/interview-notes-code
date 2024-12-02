package com.interview.notes.code.year.y2024.march24.test21;

import java.util.List;
import java.util.Optional;

public interface ITweetRepository {
    Tweet save(Tweet tweet);

    Optional<Tweet> findById(long id);

    List<Tweet> findAll();
    // Additional data access methods as needed
}
