/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services.impl;

import com.eban.pojo.Subject;
import com.eban.repositories.SubjectRepository;
import com.eban.services.SubjectService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eban
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    
    @Autowired
    private SubjectRepository subjectRepo;

    @Override
    public List<Subject> getSubjects() {
        return this.subjectRepo.getSubjects();
    }

    @Override
    public void addOrUpdateSubject(Subject sub) {
        this.subjectRepo.addOrUpdateSubject(sub);
    }

    @Override
    public Subject getSubjectById(int id) {
        return this.subjectRepo.getSubjectById(id);
    }

    @Override
    public void deleteSubject(int id) {
        this.subjectRepo.deleteSubject(id);
    }

}
