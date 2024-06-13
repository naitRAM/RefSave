package dev.ramimans.refsave.dao.mapper;

import dev.ramimans.refsave.dto.User;

public interface UserDao {
    public User readUser(String user);
    public void createUser(User user);
}
