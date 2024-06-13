/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services.impl;

import com.eban.repositories.StatsRepository;
import com.eban.services.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nmau4
 */
@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRepository;

    @Override
    public long countTeachers() {
        return this.statsRepository.countTeachers();
    }

    @Override
    public long countStudents() {
        return this.statsRepository.countStudents();
    }

    @Override
    public List<Object[]> statsSpecsBySubject() {
        return this.statsRepository.statsSpecsBySubject();
    }

    @Override
    public List<Object[]> statsSpecsByStatus() {
        return this.statsRepository.statsSpecsByStatus();
    }

    @Override
    public List<Object[]> statsCommentsBySpec() {
        return this.statsRepository.statsCommentsBySpec();
    }

    @Override
    public List<Object[]> statsAvgScoresBySpec() {
        return this.statsRepository.statsAvgScoresBySpec();
    }
}