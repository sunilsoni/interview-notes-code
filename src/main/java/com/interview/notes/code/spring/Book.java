package com.interview.notes.code.spring;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String category;
    private int ratings;

    // getters and setters
}