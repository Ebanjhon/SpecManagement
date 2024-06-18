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

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiGradingController {

    @Autowired
    private GrandingSheetService gradingService;

    @GetMapping("/gradingsheets")
    @CrossOrigin // cho phép tất cả các domain truy cập
    public ResponseEntity<List<Gradingsheet>> listGradingSheet() {
        return new ResponseEntity<>(this.gradingService.getGradings(), HttpStatus.OK);
    }
}
