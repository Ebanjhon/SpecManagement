/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.DTO;

import java.util.Date;
import java.util.List;

/**
 *
 * @author nmau4
 */
public class CommentDTO {
    private Integer idComment;
    private Integer cmParentID;
    private Date cmDate;
    private String content;
    private UserDTO user;
    private List<CommentDTO> childComments;

    // Getters và Setters
    public Integer getIdComment() {
        return idComment;
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public Integer getCmParentID() {
        return cmParentID;
    }

    public void setCmParentID(Integer cmParentID) {
        this.cmParentID = cmParentID;
    }

    public Date getCmDate() {
        return cmDate;
    }

    public void setCmDate(Date cmDate) {
        this.cmDate = cmDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<CommentDTO> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<CommentDTO> childComments) {
        this.childComments = childComments;
    }
}