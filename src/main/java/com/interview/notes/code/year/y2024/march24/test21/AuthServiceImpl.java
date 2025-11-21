package com.interview.notes.code.year.y2024.march24.test21;

public class AuthServiceImpl implements AuthService {
    private final IUserRepository userRepository;


    public AuthServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserSession login(String username, String password) {
        User user = userRepository.findByUsername(username);

        return null;
    }

    @Override
    public void logout(UserSession session) {
        // Invalidate the session
    }

    @Override
    public User register(String username, String password) {
        // Check if username exists
        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
//        String passwordHash = SecurityUtils.hashPassword(password);
//        User newUser = new User(username, passwordHash);
//        userRepository.save(newUser);
        return null;
    }
}
