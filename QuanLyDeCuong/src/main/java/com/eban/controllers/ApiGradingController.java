/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.pojo.Gradingsheet;
import com.eban.services.GrandingSheetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiGradingController {

    @Autowired
    private GrandingSheetService gradingService;
// lấy tất cả các 
    @GetMapping("/gradingsheets")
    @CrossOrigin // cho phép tất cả các domain truy cập
    public ResponseEntity<List<Gradingsheet>> listGradingSheet() {
        return new ResponseEntity<>(this.gradingService.getGradings(), HttpStatus.OK);
    }

    @PostMapping("/gradingsheets")
    @CrossOrigin
    public ResponseEntity<String> addGradingSheet(@RequestBody Gradingsheet gradname) {
        if (gradname.getNameColumn().length() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Gradingsheet grading = new Gradingsheet();
        grading.setNameColumn(gradname.getNameColumn());
        this.gradingService.creatGradingSheet(grading);
        return new ResponseEntity<>(gradname.toString(), HttpStatus.CREATED);
    }
}
