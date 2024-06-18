/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services.impl;

import com.eban.pojo.Gradingsheet;
import com.eban.repositories.GrandingSheetRepository;
import com.eban.services.GrandingSheetService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class GrandingSheetServiceImpl implements GrandingSheetService{
    @Autowired
    private GrandingSheetRepository gradingRepo;

    @Override
    public List<Gradingsheet> getGradings() {
        return this.gradingRepo.getGradings();
    }
}
