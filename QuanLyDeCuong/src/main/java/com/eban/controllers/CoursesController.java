/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.pojo.Coursestudy;
import com.eban.services.CourseService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String viewCreateOrUpdateCourses(Model model) {
        model.addAttribute("course", new Coursestudy());
        return "courses";
    }

    @GetMapping("/courses/{idcourse}")
    public String update(Model model, @PathVariable(value = "idcourse") int id){
        model.addAttribute("course", this.coursService.getCourseById(id));
        return "courses";
    }
    
    @PostMapping("/courses")
    public String createOrUpdateCourses(@ModelAttribute(value = "course") @Valid Coursestudy c, Model model) {
        try {
            this.coursService.createOrUpdate(c);
            return "redirect:/courses";
        } catch (Exception ex) {

        }
        return "courses";
    }
    
     @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable int id) {
        this.coursService.deleteCourse(id);
        return "redirect:/courses";
    }
}
