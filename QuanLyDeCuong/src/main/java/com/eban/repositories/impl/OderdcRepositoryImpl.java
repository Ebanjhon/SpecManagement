/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories.impl;

import com.eban.pojo.Oderdc;
import com.eban.pojo.Specification;
import com.eban.pojo.User;
import com.eban.repositories.OderdcRepository;
import org.hibernate.Session;
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
public class OderdcRepositoryImpl implements OderdcRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addOderdc(Oderdc oderdc) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(oderdc);

    }

}
