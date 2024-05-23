/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories;

import com.eban.pojo.Coursestudy;
import java.util.List;

/**
 *
 * @author eban
 */
public interface CourseRepository {
    List<Coursestudy> getListCourse();
    Coursestudy getCourseById(int id);
    void createOrUpdate(Coursestudy khoaHoc);
    void deleteCourse(int id);
}
