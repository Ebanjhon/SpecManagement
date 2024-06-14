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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class SpecRepositoryImpl implements SpecRepocitory {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

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

        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("PAGE_SIZE_SPEC").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }
        return query.getResultList();
    }

    @Override
    public Specification getSpecById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(Specification.class, id);

    }

    @Override
    public boolean addSpec(Specification s) {
        Session session = this.factory.getObject().getCurrentSession();

        session.save(s);
        return true;
    }

    @Override
    public boolean updateSpec(Specification s) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.update(s);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteSpec(int i) {
        Session session = this.factory.getObject().getCurrentSession();

        try {
            Specification spec = session.get(Specification.class,
                    i);
            if (spec != null) {
                session.delete(spec);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public List<Specification> getSpecsBySubjectId(int i) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("SELECT s FROM Specification s WHERE s.subjectID.idSubject = :subjectId");
        query.setParameter("subjectId", i);
        return query.getResultList();
    }

    @Override
    public List<Specification> searchSpecifications(String nameSpec, Integer credit, Integer page, String teacherName, Integer subjectId) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder(); // Tạo Truy vấn SQL động
        CriteriaQuery<Specification> query = builder.createQuery(Specification.class); // Xác định đối tượng truy vấn 
        Root<Specification> root = query.from(Specification.class); // Đại diện cho Specification

        Predicate predicate = builder.conjunction(); // Tạo cấu truy vấn

        if (nameSpec != null && !nameSpec.isEmpty()) {
            predicate = builder.and(predicate, builder.like(root.get("nameSpec").as(String.class), "%" + nameSpec + "%"));
        }
        if (credit != null) {
            predicate = builder.and(predicate, builder.equal(root.get("credit"), credit));
        }
        if (teacherName != null && !teacherName.isEmpty()) {
            predicate = builder.and(predicate, builder.like(root.get("authorID").get("username").as(String.class), "%" + teacherName + "%"));
        }
        if (subjectId != null) {
            predicate = builder.and(predicate, builder.equal(root.get("subjectID").get("idSubject"), subjectId));
        }
        query.where(predicate);

        TypedQuery<Specification> typedQuery = session.createQuery(query);
        if (page != null) {
            int pageSize = Integer.parseInt(env.getProperty("PAGE_SIZE_SPEC"));
            int start = (page - 1) * pageSize;
            typedQuery.setFirstResult(start);
            typedQuery.setMaxResults(pageSize);
        }

        return typedQuery.getResultList();
    }

}
