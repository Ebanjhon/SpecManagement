/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories.impl;

import com.eban.pojo.Gradingsheet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.eban.repositories.GradingRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

/**
 *
 * @author nmau4
 */
@Repository
@Transactional
public class GradingRepositoryImpl implements GradingRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addGrad(Gradingsheet grand) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(grand);

    }

    @Override
    public List<Gradingsheet> getGrads() {
        Session session = this.factory.getObject().getCurrentSession();
        Query q = session.createNamedQuery("Gradingsheet.findAll");
        return q.getResultList();

    }

}
