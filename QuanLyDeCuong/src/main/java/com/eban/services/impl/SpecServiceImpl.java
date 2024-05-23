/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services.impl;

import com.eban.pojo.Specification;
import com.eban.repositories.SpecRepocitory;
import com.eban.services.SpecService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class SpecServiceImpl implements SpecService{

    @Autowired
    private SpecRepocitory specRepo;
    
    @Override
    public List<Specification> getListSpec() {
        return this.specRepo.getListSpec();
    }
    
}
