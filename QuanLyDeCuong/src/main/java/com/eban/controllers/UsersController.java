/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author eban
 */
@Controller
public class UsersController {
    
    @GetMapping("/accounts")
    public String viewAccounts(Model model)
    {
//        model.addAttribute(teachers, new user);
        return "accounts";
    }
}
