package com.interview.notes.code.year.y2024.march24.test21;

import javax.naming.AuthenticationException;

public interface AuthService {
    UserSession login(String username, String password) throws AuthenticationException;

    void logout(UserSession session);

    User register(String username, String password);
}
