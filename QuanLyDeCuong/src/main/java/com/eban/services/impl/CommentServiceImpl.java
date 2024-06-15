/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services.impl;

import com.eban.DTO.CommentDTO;
import com.eban.DTO.UserDTO;
import com.eban.pojo.Comment;
import com.eban.pojo.User;
import com.eban.repositories.CommentRepository;
import com.eban.services.CommentService;
import java.util.ArrayList;
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
    public List<CommentDTO> getCommentsBySpecId(int specId) {
        List<Comment> comments = this.commentRepo.getCommentsBySpecId(specId);
        List<CommentDTO> commentDTOs = new ArrayList<>();//tạo ra list CommmentDTO
        for (Comment comment : comments) {
            if (comment.getCmParentID() == null) { // Chỉ thêm các comment cha
                CommentDTO parentCommentDTO = toCommentDTO(comment);
                parentCommentDTO.setChildComments(getChildComments(comment.getIdComment(), comments));
                commentDTOs.add(parentCommentDTO);
            }
        }
        return commentDTOs;
    }

    private List<CommentDTO> getChildComments(Integer parentId, List<Comment> allComments) {
        List<CommentDTO> childComments = new ArrayList<>();
        for (Comment childComment : allComments) {
            if (childComment.getCmParentID() != null && childComment.getCmParentID().equals(parentId)) {
                CommentDTO childCommentDTO = toCommentDTO(childComment);
                childCommentDTO.setChildComments(getChildComments(childComment.getIdComment(), allComments));//Đệ quy để có thể có lưu tiếp nếu có commnet con nữa
                childComments.add(childCommentDTO);
            }
        }
        return childComments;
    }

    private CommentDTO toCommentDTO(Comment comment) {
        CommentDTO dto = new CommentDTO();
        dto.setIdComment(comment.getIdComment());
        dto.setCmParentID(comment.getCmParentID());
        dto.setCmDate(comment.getCmDate());
        dto.setContent(comment.getContent());
        dto.setUser(toUserDTO(comment.getUserID()));
        return dto;
    }

    private UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setIdUser(user.getIdUser());
        dto.setUsername(user.getUsername());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setAvatar(user.getAvatar());
        return dto;
    }

    @Override
    public Comment getCommentById(int id) {
        return this.commentRepo.getCommentById(id);

    }
}
