/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories.impl;

import com.eban.pojo.Comment;
import com.eban.repositories.CommentRepository;
import java.util.List;
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
 * @author nmau4
 */
@Repository
@Transactional
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addComment(Comment comment) {
        Session session = this.factory.getObject().getCurrentSession();
        session.save(comment);
    }

    @Override
    public boolean updateComment(Comment comment) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            session.update(comment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteComment(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        try {
            Comment comment = session.get(Comment.class, id);
            session.delete(comment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Comment> getCommentsBySpecId(int specId) {
        Session session = this.factory.getObject().getCurrentSession(); // Lấy phiên làm việc hiện tại của Hibernate
        CriteriaBuilder builder = session.getCriteriaBuilder(); // Lấy CriteriaBuilder để xây dựng câu truy vấn
        CriteriaQuery<Comment> query = builder.createQuery(Comment.class); // Tạo một CriteriaQuery để truy vấn các đối tượng Comment
        Root<Comment> root = query.from(Comment.class); // Tạo một root để chỉ định bảng 'comment' trong câu truy vấn

        // Xây dựng câu truy vấn để lấy các comment có specID tương ứng với idSpec được truyền vào
        query.select(root).where(builder.equal(root.get("specID").get("idSpec"), specId));

        // Thực thi câu truy vấn và trả về danh sách kết quả
        return session.createQuery(query).getResultList();
    }
}
