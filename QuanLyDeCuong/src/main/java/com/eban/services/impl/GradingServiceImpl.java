/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services.impl;

import com.eban.pojo.Gradingsheet;
import com.eban.repositories.GradingRepository;
import com.eban.services.GradingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nmau4
 */
@Service
public class GradingServiceImpl implements GradingService{
    
    
    @Autowired
    public GradingRepository gradingRepo;

    @Override
    public void addGrad(Gradingsheet grand) {
        this.gradingRepo.addGrad(grand);
    }

    @Override
    public List<Gradingsheet> getGrads() {
        return this.gradingRepo.getGrads();
    }
    
}
