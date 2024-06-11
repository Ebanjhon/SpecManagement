/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories;

import com.eban.pojo.Comment;
import java.util.List;

/**
 *
 * @author nmau4
 */
public interface CommentRepository {
    void addComment(Comment comment);
    boolean updateComment(Comment comment);
    boolean deleteComment(int id);
    List<Comment> getCommentsBySpecId(int specId);
}
