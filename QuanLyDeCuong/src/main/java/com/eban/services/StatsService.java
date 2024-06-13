/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services;

import java.util.List;

/**
 *
 * @author nmau4
 */
public interface StatsService {
    long countTeachers();
    long countStudents();
    List<Object[]> statsSpecsBySubject();
    List<Object[]> statsSpecsByStatus();
    List<Object[]> statsCommentsBySpec();
    List<Object[]> statsAvgScoresBySpec();
}