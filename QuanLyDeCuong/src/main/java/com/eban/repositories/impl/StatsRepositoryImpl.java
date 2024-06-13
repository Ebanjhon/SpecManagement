/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories.impl;

import com.eban.repositories.StatsRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nmau4
 */
@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public long countTeachers() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("SELECT COUNT(u) FROM User u WHERE u.role = 'ROLE_TEACHER'");
        return (long) query.getSingleResult();
    }

    @Override
    public long countStudents() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("SELECT COUNT(u) FROM User u WHERE u.role = 'ROLE_STUDENT'");
        return (long) query.getSingleResult();
    }

    @Override
    public List<Object[]> statsSpecsBySubject() {
        Session session = this.factory.getObject().getCurrentSession();
        String hql = "SELECT sp.subjectID.nameSubject, COUNT(sp) FROM Specification sp GROUP BY sp.subjectID.nameSubject";
        Query query = session.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public List<Object[]> statsSpecsByStatus() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("SELECT sp.status, COUNT(sp) FROM Specification sp GROUP BY sp.status");
        return query.getResultList();
    }

    @Override
    public List<Object[]> statsCommentsBySpec() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("SELECT sp.nameSpec, COUNT(c) FROM Comment c JOIN c.specID sp GROUP BY sp.nameSpec");
        return query.getResultList();
    }

    @Override
    public List<Object[]> statsAvgScoresBySpec() {
        Session session = this.factory.getObject().getCurrentSession();
        String hql = "SELECT sp.nameSpec, AVG(g.weight) FROM Specification sp JOIN sp.specgrandeSet g GROUP BY sp.nameSpec";
        Query query = session.createQuery(hql);
        return query.getResultList();
    }
}
