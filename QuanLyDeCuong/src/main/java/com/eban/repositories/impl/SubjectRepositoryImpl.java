/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories.impl;

import com.eban.pojo.Subject;
import com.eban.repositories.SubjectRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eban
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class SubjectRepositoryImpl implements SubjectRepository {

    @Autowired
    private Environment env;

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Subject> getSubjects() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Subject.findAll");

        return q.getResultList();
    }

    @Override
    public void addOrUpdateSubject(Subject sub) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (sub.getIdSubject() == null) {
                s.save(sub);
            } else {
                s.update(sub);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Subject getSubjectById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(Subject.class, id);
    }

    @Override
    public void deleteSubject(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Subject subject = this.getSubjectById(id);
        session.delete(subject);
    }

}
