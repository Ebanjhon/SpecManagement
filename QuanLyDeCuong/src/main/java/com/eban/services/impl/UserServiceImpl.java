/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services.impl;

import com.eban.pojo.User;
import com.eban.repositories.UserRepository;
import com.eban.services.UserService;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.eban.DTO.SearchChatDTO;
import com.eban.DTO.UserDTO;
import com.eban.repositories.OderdcRepository;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Admin
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private OderdcRepository oderdcRepository;
    
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<User> getListTeacher() {
        return this.userRepo.getListTeacher();
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.userRepo.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Không tồn tại!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole()));

        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public void addUser(User user) {
        if (!user.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(user.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.userRepo.addUser(user);
        }
    }

    @Override
    public void addUserByAdmin(User user) {
        this.userRepo.addUser(user);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.userRepo.authUser(username, password);
    }

    @Override
    public void updateUser(User user) {
        if (user.getFile() != null && !user.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(user.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(UserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Luôn luôn cập nhật người dùng vào cơ sở dữ liệu
        this.userRepo.updateUser(user);
    }

    @Override
    public User getUserById(int id) {
        return this.userRepo.getUserById(id);
    }

    @Override
    public void updateUserCoin(int id, int coin) {
        this.userRepo.updateUserCoin(id, coin);
    }

    @Override
    public void updateUserWhenBuySpec(User user) {

        this.userRepo.updateUserWhenBuySpec(user);
    }

    @Override
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        User user = this.getUserById(userId);
        if (user != null && checkPassword(user, oldPassword)) {
            user.setPassword(this.passwordEncoder.encode(newPassword));
            this.updateUser(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPassword(User user, String rawPassword) {

        return passwordEncoder.matches(rawPassword, user.getPassword());//so sánh mk người dung nhap sau khi ma hoa voi mk db 

    }

    @Override
    public List<User> getListUser() {
        return this.userRepo.getListUser();
    }

    @Override
    public void deleteUser(int id) {
        this.userRepo.deleteUser(id);
    }
    @Override
    public boolean hasUserBoughtSpec(int userId, int specId) {
        return oderdcRepository.existsByUserIdAndSpecId(userId, specId);
    }
    
    

}
