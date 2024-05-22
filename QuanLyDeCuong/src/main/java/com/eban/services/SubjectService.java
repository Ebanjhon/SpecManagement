/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services;

import com.eban.pojo.Subject;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eban
 */
public interface SubjectService {

    List<Subject> getSubjects();

    void addOrUpdateSubject(Subject sub);

    Subject getSubjectById(int id);

    void deleteSubject(int id);

}
