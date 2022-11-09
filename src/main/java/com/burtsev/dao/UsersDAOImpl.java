package com.burtsev.dao;

import com.burtsev.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UsersDAOImpl implements UsersDAO {
    @PersistenceContext
    private EntityManager em;

    public UsersDAOImpl() {
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }
    @Override
    public User getUser (int id){
        return em.find(User.class, id);
    }
    @Override
    @Transactional
    public void save(User user) {
        em.persist(user);
    }
    @Override
    public void update(User updatedUser) {
        em.merge(updatedUser);
    }
    @Override
    public void delete(int id) {
        User user = em.find(User.class, id);
        em.remove(user);
    }
}
