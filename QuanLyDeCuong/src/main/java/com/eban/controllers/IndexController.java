/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.pojo.Specification;
import com.eban.pojo.Subject;
import com.eban.services.CourseService;
import com.eban.services.SpecService;
import com.eban.services.SubjectService;
import com.eban.services.UserService;
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
    private SubjectService subjectService;

    @Autowired
    private CourseService coursService;

    @Autowired
    private SpecService specService;

    @Autowired
    private UserService userService;

    // cac du lieu se duoc dung chung
    @ModelAttribute
    public void commonAttr(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("subs", this.subjectService.getSubjects());
        model.addAttribute("courses", this.coursService.getListCourse());
        model.addAttribute("specs", this.specService.getListSpec(params));
        model.addAttribute("teachers", this.userService.getListTeacher());
        model.addAttribute("spec", new Specification());
    }
}
