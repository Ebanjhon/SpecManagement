/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories;

import com.eban.DTO.ChatUserDTO;
import com.eban.DTO.RoomChatDTO;
import java.util.List;

/**
 *
 * @author nmau4
 */
public interface ChatRepository {

    List<RoomChatDTO> findAllChatRoomsWithUsers(Integer userId);
    

}
