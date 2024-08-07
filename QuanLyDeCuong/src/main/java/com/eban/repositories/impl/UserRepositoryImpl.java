/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories.impl;

import com.eban.DTO.SearchChatDTO;
import com.eban.DTO.UserDTO;
import com.eban.pojo.Chat;
import com.eban.pojo.User;
import com.eban.repositories.UserRepository;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public List<User> getListTeacher() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        // Add the condition to the query
        query.select(root).where(builder.equal(root.get("role"), "ROLE_TEACHER"));

        Query q = session.createQuery(query);

        List<User> teachers = q.getResultList();
        return teachers;
    }

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username = :username");
        q.setParameter("username", username);

        List<User> result = q.getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    @Override
    public void addUser(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(user);
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);

        if (u != null && this.passEncoder.matches(password, u.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        session.update(user);
    }

    @Override
    public User getUserById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("From User WHERE idUser=:idUser");
        query.setParameter("idUser", id);

        return (User) query.getSingleResult();
    }

    @Override
    public void updateUserCoin(int id, int coin) {
        Session session = this.factory.getObject().getCurrentSession();
        User user = session.get(User.class, id);
        if (user != null) {
            int currentcoin = user.getCoin();
            user.setCoin(currentcoin + coin);
            session.update(user);
        } else {
            throw new RuntimeException("User not found for ID: " + id);
        }
    }

    @Override
    public void updateUserWhenBuySpec(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        session.update(user);
    }

    @Override
    public List<User> getListUser() {
        Session session = this.factory.getObject().getCurrentSession();

        Query q = session.createNamedQuery("User.findAll");

        return q.getResultList();

    }
    @Override
    public void deleteUser(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        User user = this.getUserById(id);
        session.delete(user);
    }

}
