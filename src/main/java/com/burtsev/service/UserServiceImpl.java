package com.burtsev.service;

import com.burtsev.dao.UsersDAO;
import com.burtsev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private final UsersDAO usersDAO;
    @Autowired
    public UserServiceImpl(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public List<User> getAllUsers() {
        return usersDAO.getAllUsers();
    }

    @Override
    public User getUser(int id) {
        return usersDAO.getUser(id);
    }

    @Override
    public void save(User user) {
        usersDAO.save(user);
    }

    @Override
    public void update(int id, User updatedUser) {
        usersDAO.update(id, updatedUser);
    }

    @Override
    public void delete(int id) {
        usersDAO.delete(id);
    }
}
