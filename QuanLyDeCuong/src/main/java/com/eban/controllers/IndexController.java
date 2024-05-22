/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.pojo.Subject;
import com.eban.services.SubjectService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author eban
 */
@Controller
@ControllerAdvice
public class IndexController {

    // khai bao controller trang chu
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }
    
    // goi cac service
    @Autowired
//    private SubjectService subjectService;

    // cac du lieu se duoc dung chung
    @ModelAttribute
    public void commonAttr(Model model) {
//        model.addAttribute("subs", this.subjectService.getSubjects());
    }
}