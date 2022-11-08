package com.burtsev.dao;

import com.burtsev.model.User;

import java.util.List;

public interface UsersDAO {
    List<User> getAllUsers();
    User getUser (int id);
    void save(User user);
    void update(int id, User updatedUser);
    void delete(int id);
}
