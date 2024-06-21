/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.pojo.Subject;
import com.eban.services.SubjectService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiSubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects")
    @CrossOrigin // cho phép tất cả các domain truy cập
    public ResponseEntity<List<Subject>> listSubject() {
        return new ResponseEntity<>(this.subjectService.getSubjects(), HttpStatus.OK);
    }
    
//    @GetMapping("/test")
//    @CrossOrigin // cho phép tất cả các domain truy cập
//    public ResponseEntity<String> test(@RequestParam Map<String, String> params) {
//        String testValue = params.get("test");
//        
//        if (testValue != null) {
//            return new ResponseEntity<>(testValue, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Tham số 'test' không tồn tại", HttpStatus.BAD_REQUEST);
//        }
//    }

}