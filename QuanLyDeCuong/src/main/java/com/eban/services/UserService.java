/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services;

import com.eban.DTO.ChatUserDTO;
import com.eban.DTO.SearchChatDTO;
import com.eban.DTO.UserDTO;
import com.eban.pojo.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Admin
 */
public interface UserService extends UserDetailsService {

    List<User> getListTeacher();

    List<User> getListUser();

    User getUserByUsername(String username);

    void addUser(User user);

    void addUserByAdmin(User user);

    boolean authUser(String username, String password);

    void updateUser(User user);

    User getUserById(int id);

    void updateUserCoin(int id, int coin); // Xy lý nap coin

    void updateUserWhenBuySpec(User user); // Xy lý nap coin

    boolean changePassword(int userId, String oldPassword, String newPassword);

    boolean checkPassword(User user, String rawPassword);//check pass cũ 

    void deleteUser(int id);
    boolean hasUserBoughtSpec(int userId, int specId);

}
