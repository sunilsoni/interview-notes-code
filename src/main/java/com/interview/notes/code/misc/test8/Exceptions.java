package com.interview.notes.code.misc.test8;

/**
 * Programming question #3:
 *
 *
 * Which of the following exceptions would you use in your program, and why?
 */
public class Exceptions {
/*
    // method 1
    public String getImport(int importId) {
        try {
            return dao.findById(importId);
        } catch (Exception e) {
            throw new RuntimeException("Error loading import id " + importId + " for user " + getUser());
        }
    }


    // method 2
    public String getImport(int importId) {
        try {
            return dao.findById(importId);
        } catch (Exception e) {
            throw new RuntimeException("Error loading import id " + importId + " for user " + getUser() + " error was: " + e.toString());
        }
    }


    // method 3
    public String getImport(int importId) throws Exception {
        return dao.findById(importId);
    }


    // method 4
    public String getImport(int importId) {
        try {
            return dao.findById(importId);
        } catch (Exception e) {
            throw new RuntimeException("Error loading import id " + importId + " for user " + getUser(), e);
        }
    }

*/
}
