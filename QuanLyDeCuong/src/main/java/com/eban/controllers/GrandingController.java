/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.pojo.Gradingsheet;
import com.eban.pojo.Specgrande;
import com.eban.services.GradingService;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nmau4
 */
@RestController
@RequestMapping("/api")
public class GrandingController {

    @Autowired
    private GradingService gradingService;

    @PostMapping(path = "/grading/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public void addCommentToSpec(@RequestParam Map<String, String> params) {
        Gradingsheet grand = new Gradingsheet();
        grand.setNameColumn(params.get("nameColumn"));

        this.gradingService.addGrad(grand);
    }
    
    
    @GetMapping("/gradings/")
    @CrossOrigin // cho phép tất cả các domain truy cập
    public ResponseEntity<List<Gradingsheet>> listGrads() {
        return new ResponseEntity<>(this.gradingService.getGrads(), HttpStatus.OK);
    }
}
