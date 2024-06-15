/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services.impl;

import com.eban.DTO.ChatUserDTO;
import com.eban.DTO.RoomChatDTO;
import com.eban.pojo.Chat;
import com.eban.pojo.User;
import com.eban.repositories.ChatRepository;
import com.eban.services.ChatService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nmau4
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepo;

    @Override
    public List<RoomChatDTO> findAllChatRoomsWithUsers(Integer userId) {
        return chatRepo.findAllChatRoomsWithUsers(userId);
    }

}
