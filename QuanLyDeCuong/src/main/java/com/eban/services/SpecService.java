/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services;

import com.eban.pojo.Specification;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface SpecService {

    List<Specification> getListSpec(Map<String, String> params);

    Specification getSpecById(int id);

    void addSpec(Specification spec);

    boolean updateSpec(Specification spec);

    boolean deleteSpec(int id);

    List<Specification> getSpecsBySubjectId(int subjectId);

    List<Specification> searchSpecifications(String nameSpec, Integer credit, String teacherName, Integer subjectId);

}
