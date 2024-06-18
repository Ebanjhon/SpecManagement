/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories.impl;

import com.eban.pojo.Gradingsheet;
import com.eban.repositories.GrandingSheetRepository;
import java.util.List;
import javax.persistence.Query;
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
public class GrandingSheetRepositoryImpl implements GrandingSheetRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Gradingsheet> getGradings() {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createNamedQuery("Gradingsheet.findAll");
        return query.getResultList();
    }
}
