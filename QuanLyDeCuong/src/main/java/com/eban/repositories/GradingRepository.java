/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories;

import com.eban.pojo.Gradingsheet;
import java.util.List;

/**
 *
 * @author nmau4
 */
public interface GradingRepository {

    void addGrad(Gradingsheet grand);

    List<Gradingsheet>  getGrads();
    
}
