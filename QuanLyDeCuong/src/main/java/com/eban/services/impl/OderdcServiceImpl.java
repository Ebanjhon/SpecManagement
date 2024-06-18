/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services.impl;

import com.eban.pojo.Oderdc;
import com.eban.pojo.Specification;
import com.eban.pojo.User;
import com.eban.repositories.OderdcRepository;
import com.eban.services.OderdcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nmau4
 */
@Service
public class OderdcServiceImpl implements OderdcService {

    @Autowired
    private OderdcRepository OderdcRepo;

    @Override
    public void addOderdc(Oderdc oderdc) {
        this.OderdcRepo.addOderdc(oderdc);
    }

}
