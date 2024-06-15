/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.DTO.CommentDTO;
import com.eban.pojo.Comment;
import com.eban.pojo.Specification;
import com.eban.pojo.User;
import com.eban.services.CommentService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author nmau4
 */
@RestController
@RequestMapping("/api")
public class ApiCommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/spec/{specId}")
    @CrossOrigin
    public ResponseEntity<List<CommentDTO>> getCommentsBySpecId(@PathVariable(value = "specId") int specId) {
        List<CommentDTO> comments = this.commentService.getCommentsBySpecId(specId);
        if (comments == null || comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    //Tao cmt cha 
    @PostMapping(path = "/comments/spec/{specId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void addCommentToSpec(@PathVariable(value = "specId") int specId, @RequestBody Map<String, String> params) {
        Logger.getLogger(ApiCommentController.class.getName()).log(Level.INFO, "Params: {0}", params);

        Comment comment = new Comment();
        comment.setContent(params.get("content"));
        comment.setCmDate(new Date());

        Specification spec = new Specification();
        spec.setIdSpec(specId);
        comment.setSpecID(spec);

        if (params.get("userId") != null) {
            User user = new User();
            user.setIdUser(Integer.parseInt(params.get("userId")));
            comment.setUserID(user);
        }

        this.commentService.addComment(comment);
    }

    /// Tao CMT con 
    @PostMapping(path = "/comments/parent/{parentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void addCommentToParent(@PathVariable(value = "parentId") int parentId, @RequestBody Map<String, String> params) {
        Logger.getLogger(ApiCommentController.class.getName()).log(Level.INFO, "Params: {0}", params);
        
        //Tạo commnet cha để lấy id SPec 
        Comment parentComment = this.commentService.getCommentById(parentId);
        if (parentComment == null) {
            throw new RuntimeException("Parent comment not found");
        }
        
        

        Comment comment = new Comment();
        comment.setContent(params.get("content"));
        comment.setCmDate(new Date());
        comment.setCmParentID(parentId);
        
        // Lấy specID từ comment cha
        Specification spec = parentComment.getSpecID();
        comment.setSpecID(spec);
        
        

        if (params.get("userId") != null) {
            User user = new User();
            user.setIdUser(Integer.parseInt(params.get("userId")));
            comment.setUserID(user);
        }

        this.commentService.addComment(comment);
    }

    @PutMapping(path = "/comments/{commentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Comment> updateComment(@PathVariable(value = "commentId") int commentId, @RequestBody Comment comment) {
        comment.setIdComment(commentId);
        boolean result = this.commentService.updateComment(comment);
        if (result) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/comment/{commentId}")
    @CrossOrigin
    public ResponseEntity<Void> deleteComment(@PathVariable(value = "commentId") int commentId) {
        boolean result = this.commentService.deleteComment(commentId);
        if (result) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
