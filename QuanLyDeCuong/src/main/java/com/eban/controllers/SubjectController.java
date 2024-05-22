/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.pojo.Subject;
import com.eban.services.SubjectService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author eban
 */
@Controller
@PropertySource("classpath:configs.properties")
public class SubjectController {

    @Autowired
    private Environment env;

    @Autowired
    private SubjectService subjectService;

    // day len view mot doi tuong sub rong va danh sach doi tuong subject 
    @GetMapping("/subjects")
    public String index(Model model) {
        model.addAttribute("sub", new Subject());

        return "subjects";
    }
    // controler de lay chi tiet mot doi tuong theo id

    //    Annotation @PathVariable được sử dụng để trích xuất giá trị của biến đường dẫn {object} từ URL và gán nó cho tham số id của phương thức.
    @GetMapping("/subjects/{idSubject}")
    public String updateView(Model model, @PathVariable(value = "idSubject") int id) {
        model.addAttribute("sub", this.subjectService.getSubjectById(id));
        return "subjects";
    }

    // controller lay doi tuong rong va tao doi tuong or cap nhat check id
    @PostMapping("/subjects")
    public String createSubject(@ModelAttribute(value = "sub") @Valid Subject s) {
        try {
            this.subjectService.addOrUpdateSubject(s);
            return "redirect:/subjects";
        } catch (Exception e) {

        }
        return "subjects";
    }

    @GetMapping("/deleteSubject/{id}")
    public String deleteSubject(@PathVariable int id) {
        this.subjectService.deleteSubject(id);
        return "redirect:/subjects";
    }
}
