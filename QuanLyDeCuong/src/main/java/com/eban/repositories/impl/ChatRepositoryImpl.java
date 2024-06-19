/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories.impl;

import com.eban.DTO.ChatUserDTO;
import com.eban.DTO.RoomChatDTO;
import com.eban.pojo.Chat;
import com.eban.pojo.Roomchat;
import com.eban.pojo.User;
import com.eban.repositories.ChatRepository;
import java.util.ArrayList;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author nmau4
 */
@Repository
@Transactional
public class ChatRepositoryImpl implements ChatRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<RoomChatDTO> findAllChatRoomsWithUsers(Integer userId) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query roomQuery = session.createQuery("SELECT DISTINCT c.roomID FROM Chat c WHERE c.userID.idUser = :userId");
        roomQuery.setParameter("userId", userId);

        List<Roomchat> rooms = roomQuery.getResultList();
        List<RoomChatDTO> roomChatDTOs = new ArrayList<>();

        for (Roomchat room : rooms) {
            RoomChatDTO roomDTO = new RoomChatDTO();
            roomDTO.setIdRoomChat(room.getIdRoomChat());
            roomDTO.setNameRoom(room.getNameRoom());

            Query userQuery = session.createQuery("SELECT c FROM Chat c WHERE c.roomID.idRoomChat = :roomId");
            userQuery.setParameter("roomId", room.getIdRoomChat());
            List<Chat> roomChats = userQuery.getResultList();

            List<ChatUserDTO> chatUserDTOs = new ArrayList<>();
            for (Chat chat : roomChats) {
                if (!chat.getUserID().getIdUser().equals(userId)) {
                    chatUserDTOs.add(toChatUserDTO(chat.getUserID(), room.getIdRoomChat()));
                }
            }

            roomDTO.setUsers(chatUserDTOs);
            roomChatDTOs.add(roomDTO);
        }

        return roomChatDTOs;
    }

    private ChatUserDTO toChatUserDTO(User user, Integer roomId) {
        ChatUserDTO dto = new ChatUserDTO();
        dto.setIdUser(user.getIdUser());
        dto.setUsername(user.getUsername());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setRole(user.getRole());
        dto.setAvatar(user.getAvatar());
        dto.setRoomId(roomId);
        return dto;
    }

    @Override
    public List<ChatUserDTO> findUsersByUsername(String username) {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("FROM User WHERE username LIKE :username");
        query.setParameter("username", "%" + username + "%");
        List<User> users = query.getResultList();
        List<ChatUserDTO> userDTOs = new ArrayList<ChatUserDTO>();
        for (User user : users) {
            userDTOs.add(toChatUserDTO(user));
        }
        return userDTOs;
    }

    private ChatUserDTO toChatUserDTO(User user) {
        ChatUserDTO dto = new ChatUserDTO();
        dto.setIdUser(user.getIdUser());
        dto.setUsername(user.getUsername());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setRole(user.getRole());
        dto.setAvatar(user.getAvatar());
        return dto;
    }
}
