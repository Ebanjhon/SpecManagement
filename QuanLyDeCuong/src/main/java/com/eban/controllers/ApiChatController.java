/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.DTO.ChatUserDTO;
import com.eban.DTO.RoomChatDTO;
import com.eban.services.ChatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nmau4
 */
@RestController
@RequestMapping("/api")
public class ApiChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/chatrooms/users")
    @CrossOrigin
    public ResponseEntity<List<RoomChatDTO>> getChatRoomsAndUsers(@RequestParam Integer userId) {
        List<RoomChatDTO> roomChatDTOs = chatService.findAllChatRoomsWithUsers(userId);
        if (roomChatDTOs == null || roomChatDTOs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(roomChatDTOs, HttpStatus.OK);
    }

    // API tìm người dùng theo username
    @GetMapping("/searchusername")
    @CrossOrigin
    public ResponseEntity<List<ChatUserDTO>> searchUsersByUsername(@RequestParam String username) {
        List<ChatUserDTO> users = chatService.findUsersByUsername(username);
        if (users == null || users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // api tạo phong chat
    @PostMapping("/addchat")
    @CrossOrigin
    public ResponseEntity<Integer> addChatroom(@RequestParam("userId1") int userId1, @RequestParam("userId2") int userId2) {
        int[] iduser = {userId1, userId2};
        int roomId = this.chatService.addChat(iduser);
        return new ResponseEntity<>(roomId,HttpStatus.CREATED);
    }

}
