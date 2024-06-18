/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories;

import com.eban.pojo.Gradingsheet;
import com.eban.pojo.Specgrande;
import com.eban.pojo.Specification;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface SpecRepocitory {
    List<Specification> getListSpec(Map<String, String> params);
    Specification getSpecById(int id);
    boolean addSpec(Specification spec);
    boolean updateSpec(Specification spec);
    boolean deleteSpec(int id);
    List<Specification> getSpecsBySubjectId(int subjectId);

    public List<Specification> searchSpecifications(String nameSpec, Integer credit, Integer page ,String teacherName, Integer subjectId);
    long countSpecifications(String nameSpec, Integer credit, String teacherName, Integer subjectId);
    Gradingsheet findGradingSheetByName(String name);
    void addGradingSheet(Gradingsheet gradingsheet);
    void addSpecgrande(Specgrande specgrande);
    List<Specgrande> getSpecgrandeBySpecId(int id);
}
