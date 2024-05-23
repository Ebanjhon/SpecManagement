/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories.impl;

import com.eban.pojo.Specification;
import com.eban.repositories.SpecRepocitory;
import java.util.List;
import java.util.Map;
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
public class SpecRepositoryImpl implements SpecRepocitory {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Specification> getListSpec(Map<String, String> params) {
        Session session = this.factory.getObject().getCurrentSession();
        String kw = params.get("kw");
        Query query;
        if (kw == null || kw.trim().isEmpty()) {
            query = session.createNamedQuery("Specification.findAll");
        } else {
            query = session.createQuery("SELECT s FROM Specification s WHERE s.nameSpec LIKE :kw");
            query.setParameter("kw", "%" + kw + "%");
        }
        return query.getResultList();
    }

}
