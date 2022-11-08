package com.burtsev.dao;

import com.burtsev.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UsersDAOImpl implements UsersDAO {
//    private final SessionFactory sessionFactory;
//    @Autowired
//    public UsersDAOImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
    private final EntityManager entityManager;

    @Autowired
    public UsersDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
//        return users;
        return entityManager.createQuery("select u from User u", User.class).getResultList();
//        TypedQuery<User> query = sessionFactory
//                .getCurrentSession()
//                .createQuery("select u from User u", User.class);
//        return query.getResultList();
//        return null;
    }
    @Override
    public User getUser (int id){
//        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
        return null;
    }
    @Override
    @Transactional
    public void save(User user) {
//        user.setId(++COUNT_ID);
//        users.add(user);
//        sessionFactory.getCurrentSession().save(user);
    }
    @Override
    public void update(int id, User updatedUser) {
        User userToBeUpdated = getUser(id);
        userToBeUpdated.setFirstName(updatedUser.getFirstName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setAge(updatedUser.getAge());
        userToBeUpdated.setCountry(updatedUser.getCountry());
    }
    @Override
    public void delete(int id) {
//        users.removeIf(p -> p.getId() == id);
    }
}
