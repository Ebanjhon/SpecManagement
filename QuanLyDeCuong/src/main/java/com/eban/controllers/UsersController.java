/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author eban
 */
@Controller
@PropertySource("classpath:configs.properties")
public class UsersController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/accounts")
    public String viewAccounts(Model model) {
//        model.addAttribute(teachers, new user);
        return "accounts";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        this.userService.deleteUser(id);
        return "redirect:/accounts";
    }
}
