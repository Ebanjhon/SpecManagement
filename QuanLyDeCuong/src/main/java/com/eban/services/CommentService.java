/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services;

import com.eban.DTO.CommentDTO;
import com.eban.pojo.Comment;
import java.util.List;

/**
 *
 * @author nmau4
 */
public interface CommentService {
    void addComment(Comment comment);
    boolean updateComment(Comment comment);
    boolean deleteComment(int id);
    List<CommentDTO> getCommentsBySpecId(int specId);
    Comment getCommentById(int id); 
}
