/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services.impl;

import com.eban.pojo.Comment;
import com.eban.repositories.CommentRepository;
import com.eban.services.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nmau4
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Override
    public void addComment(Comment comment) {
        this.commentRepo.addComment(comment);
    }

    @Override
    public boolean updateComment(Comment comment) {
        return this.commentRepo.updateComment(comment);
    }

    @Override
    public boolean deleteComment(int id) {
        return this.commentRepo.deleteComment(id);
    }

    @Override
    public List<Comment> getCommentsBySpecId(int specId) {
        return this.commentRepo.getCommentsBySpecId(specId);
    }

}