/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories.impl;

import com.eban.pojo.Coursestudy;
import com.eban.pojo.Gradingsheet;
import com.eban.pojo.Specgrande;
import com.eban.pojo.Specification;
import com.eban.repositories.SpecRepocitory;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
    public List<Specification> searchSpecifications(String nameSpec, Integer credit, Integer page, String teacherName, Integer subjectId, Integer idCourse, Integer authorID) {
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
        if (authorID != null) {
            predicate = builder.and(predicate, builder.equal(root.get("authorID").get("idUser"), authorID));
        }
        if (idCourse != null) {
            Join<Specification, Coursestudy> courseJoin = root.join("coursestudySet");
            predicate = builder.and(predicate, builder.equal(courseJoin.get("idCourse"), idCourse));
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

    @Override
    public long countSpecifications(String nameSpec, Integer credit, String teacherName, Integer subjectId, Integer idCourse, Integer authorID) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Specification> root = query.from(Specification.class);

        Predicate predicate = builder.conjunction();

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
        if (authorID != null) {
            predicate = builder.and(predicate, builder.equal(root.get("authorID").get("idUser"), authorID));
        }
        if (idCourse != null) {
            Join<Specification, Coursestudy> courseJoin = root.join("coursestudySet");
            predicate = builder.and(predicate, builder.equal(courseJoin.get("idCourse"), idCourse));
        }
        query.select(builder.count(root)).where(predicate);

        return session.createQuery(query).getSingleResult();
    }

    @Override
    public Gradingsheet findGradingSheetByName(String name) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("SELECT g FROM Gradingsheet g WHERE g.nameColumn = :nameColumn");
        query.setParameter("nameColumn", name);
        try {
            return (Gradingsheet) query.getSingleResult();
        } catch (NoResultException e) {
            return null; // 
        }
    }

    @Override
    public void addGradingSheet(Gradingsheet gradingsheet) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(gradingsheet);
    }

    @Override
    public void addSpecgrande(Specgrande specgrande) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(specgrande);
    }

    @Override
    public List<Specgrande> getSpecgrandeBySpecId(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("SELECT sg FROM Specgrande sg WHERE sg.specifiID.idSpec = :specId");
        query.setParameter("specId", id);
        return query.getResultList();
    }
////Lấy danh sách Spec của user đã mua 
//    truy vấn phức tạp nên note lại 
//    SELECT s: Chọn tất cả các đối tượng Specification (được gán bí danh là s).
//FROM Specification s: Từ bảng Specification (được gán bí danh là s).
//JOIN Oderdc o ON s.idSpec = o.specID.idSpec: Kết nối bảng Specification với bảng Oderdc (được gán bí danh là o) 
//dựa trên điều kiện s.idSpec = o.specID.idSpec. Điều này có nghĩa là lấy tất cả các dòng mà idSpec của Specification bằng specID.idSpec của Oderdc.
//WHERE o.userID.idUser =
//: Thêm điều kiện để chỉ lấy các dòng mà userID.idUser trong bảng Oderdc bằng với tham số userId được truyền vào.
    @Override
    public List<Specification> getSpecsbyUserId(int userId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query query = session.createQuery("SELECT s FROM Specification s JOIN Oderdc o ON s.idSpec = o.specID.idSpec WHERE o.userID.idUser = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

}
