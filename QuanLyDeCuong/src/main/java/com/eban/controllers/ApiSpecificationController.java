/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.pojo.Specification;
import com.eban.services.SpecService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiSpecificationController {
    
    @Autowired
    private SpecService specService;
    
    @GetMapping("/specifications")
    public ResponseEntity<List<Specification>> listSpec(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(this.specService.getListSpec(params), HttpStatus.OK);
    }
    
    
    @GetMapping(path = "/specifications/{idSpec}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Specification> retrieve(@PathVariable(value = "idSpec") int id) {
        return new ResponseEntity<>(this.specService.getSpecById(id), HttpStatus.OK); 
    }
    
    
    
}
