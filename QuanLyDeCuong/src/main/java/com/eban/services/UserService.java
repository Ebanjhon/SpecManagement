/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services;

import com.eban.DTO.ChatUserDTO;
import com.eban.pojo.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Admin
 */
public interface UserService extends UserDetailsService {

    List<User> getListTeacher();

    User getUserByUsername(String username);

    void addUser(User user);

    boolean authUser(String username, String password);

    void updateUser(User user);

    User getUserById(int id);
}
