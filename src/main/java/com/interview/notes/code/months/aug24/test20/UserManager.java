package com.interview.notes.code.months.aug24.test20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class User {
    int id;
    String info; // assuming a single string represents all other user information for simplicity

    public User(int id, String info) {
        this.id = id;
        this.info = info;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id && (info != null ? info.equals(user.info) : user.info == null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }
}

public class UserManager {
    public static List<User>[] compareUsers(List<User> usersListInDB, List<User> newUsersList) {
        Map<Integer, User> currentUsers = new HashMap<>();
        for (User user : usersListInDB) {
            currentUsers.put(user.id, user);
        }

        List<User> updatedUsers = new ArrayList<>();
        List<User> insertedUsers = new ArrayList<>();

        for (User newUser : newUsersList) {
            User existingUser = currentUsers.get(newUser.id);
            if (newUser.id == 0) { // New user
                insertedUsers.add(newUser);
            } else if (existingUser != null && !newUser.equals(existingUser)) {
                updatedUsers.add(newUser);
            }
        }

        return new List[]{updatedUsers, insertedUsers};
    }

    public static void main(String[] args) {
        List<User> usersListInDB = new ArrayList<>();
        usersListInDB.add(new User(1, "User1Info"));
        usersListInDB.add(new User(2, "User2Info"));

        List<User> newUsersList = new ArrayList<>();
        newUsersList.add(new User(0, "UserNew1Info"));  // new user
        newUsersList.add(new User(1, "User1NewInfo"));  // updated info
        newUsersList.add(new User(3, "UserNew3Info"));  // new user

        List<User>[] result = compareUsers(usersListInDB, newUsersList);
        System.out.println("Updated Users: " + result[0].size());
        System.out.println("Inserted Users: " + result[1].size());

        for (User user : result[0]) {
            System.out.println("Updated: ID=" + user.id + ", Info=" + user.info);
        }
        for (User user : result[1]) {
            System.out.println("Inserted: ID=" + user.id + ", Info=" + user.info);
        }
    }
}
