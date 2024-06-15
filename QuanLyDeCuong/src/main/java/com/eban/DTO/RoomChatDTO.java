/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.DTO;

import java.util.List;

/**
 *
 * @author nmau4
 */
public class RoomChatDTO {
    private Integer idRoomChat;
    private String nameRoom;
    private List<ChatUserDTO> users;

    /**
     * @return the idRoomChat
     */
    public Integer getIdRoomChat() {
        return idRoomChat;
    }

    /**
     * @param idRoomChat the idRoomChat to set
     */
    public void setIdRoomChat(Integer idRoomChat) {
        this.idRoomChat = idRoomChat;
    }

    /**
     * @return the nameRoom
     */
    public String getNameRoom() {
        return nameRoom;
    }

    /**
     * @param nameRoom the nameRoom to set
     */
    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    /**
     * @return the users
     */
    public List<ChatUserDTO> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<ChatUserDTO> users) {
        this.users = users;
    }
    
}
