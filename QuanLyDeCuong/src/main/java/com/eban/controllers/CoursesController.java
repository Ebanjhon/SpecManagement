/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.pojo.Coursestudy;
import com.eban.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author eban
 */
@Controller
public class CoursesController {

    @Autowired
    private CourseService coursService;

//    gui doi tuong rong
    @GetMapping("/courses")
    public String viewCreateOrUpdateCourses(Model model){
        model.addAttribute("course", new Coursestudy());
        return "courses";
    }
    
}
