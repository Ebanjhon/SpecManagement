/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services.impl;

import com.eban.pojo.Coursestudy;
import com.eban.repositories.CourseRepository;
import com.eban.services.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eban
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository CourseRepo;

    @Override
    public List<Coursestudy> getListCourse() {
        return this.CourseRepo.getListCourse();
    }

    @Override
    public Coursestudy getCourseById(int id) {
        return this.CourseRepo.getCourseById(id);
    }

    @Override
    public void createOrUpdate(Coursestudy khoaHoc) {
        this.CourseRepo.createOrUpdate(khoaHoc);
    }

    @Override
    public void deleteCourse(int id) {
        this.CourseRepo.deleteCourse(id);
    }

}
