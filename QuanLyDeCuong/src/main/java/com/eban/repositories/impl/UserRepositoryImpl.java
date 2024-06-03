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
    public User getUserByUserName(String username) {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createQuery("FROM User WHERE username = :username");
        q.setParameter("username", username);
        return (User) q.getSingleResult();
    }

    @Override
    public void addUser(User user) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(user);
        
        

    }

}
