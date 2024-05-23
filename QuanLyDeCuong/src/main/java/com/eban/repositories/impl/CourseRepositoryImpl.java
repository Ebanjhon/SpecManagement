/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories.impl;

import com.eban.pojo.Coursestudy;
import com.eban.repositories.CourseRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.Root;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author eban
 */
@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Coursestudy> getListCourse() {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Coursestudy> query = builder.createQuery(Coursestudy.class);
        Root root = query.from(Coursestudy.class);

        query.select(root);

        Query q = session.createQuery(query);

        List<Coursestudy> khoaHocs = q.getResultList();
        return khoaHocs;
    }

    @Override
    public Coursestudy getCourseById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        return session.get(Coursestudy.class, id);
    }

    @Override
    public void createOrUpdate(Coursestudy khoaHoc) {
        Session session = this.factory.getObject().openSession(); // Mở một phiên làm việc mới
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction(); // Bắt đầu giao dịch

            if (khoaHoc.getIdCourse() == null) {
                session.save(khoaHoc);
            } else {
                session.update(khoaHoc);
            }

            transaction.commit(); // Commit giao dịch
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback giao dịch nếu có lỗi
            }
            e.printStackTrace();
        } finally {
            session.close(); // Đóng phiên làm việc
        }
    }

    @Override
    public void deleteCourse(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Coursestudy kh = this.getCourseById(id);
        session.delete(kh);
    }

}
