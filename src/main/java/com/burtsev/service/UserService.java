package com.burtsev.service;

import com.burtsev.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser (int id);
    void save(User user);
    void update(int id, User updatedUser);
    void delete(int id);
}
