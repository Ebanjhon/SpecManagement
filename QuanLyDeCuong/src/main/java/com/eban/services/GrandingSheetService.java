/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services;

import com.eban.pojo.Gradingsheet;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface GrandingSheetService {

    List<Gradingsheet> getGradings();

    void creatGradingSheet(Gradingsheet grading);

}
