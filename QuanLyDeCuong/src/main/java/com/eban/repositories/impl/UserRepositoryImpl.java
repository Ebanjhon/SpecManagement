/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories.impl;

import com.eban.pojo.User;
import com.eban.repositories.UserRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        query.select(root).where(builder.equal(root.get("role"), "Teacher"));

        Query q = session.createQuery(query);

        List<User> teachers = q.getResultList();
        return teachers;
    }

//    @Override
//    public User getUserByUserName(String username) {
//        Session session = this.factory.getObject().getCurrentSession();
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<User> query = builder.createQuery(User.class);
//        Root<User> root = query.from(User.class);
//        
//        query.select(root).where(builder.equal(root.get("username"), username));
//        
//        Query q = session.createQuery(query);
//       
//        User user = (User) q.getSingleResult();
//        
//        return user;
//    }
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
//        return (User) q.getSingleResult();
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

}
