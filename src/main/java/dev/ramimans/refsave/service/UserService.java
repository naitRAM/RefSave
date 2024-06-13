package dev.ramimans.refsave.service;

import dev.ramimans.refsave.dto.User;

public interface UserService {
    public User readUser(String username);
    public void createUser(User user);
}
