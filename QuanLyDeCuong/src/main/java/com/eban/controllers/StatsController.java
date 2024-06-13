/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.services.StatsService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author nmau4
 */
@Controller
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping("/stats")
    public String statsView(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("totalTeachers", this.statsService.countTeachers());
        model.addAttribute("totalStudents", this.statsService.countStudents());
        model.addAttribute("specsBySubject", this.statsService.statsSpecsBySubject());
        model.addAttribute("specsByStatus", this.statsService.statsSpecsByStatus());
        model.addAttribute("commentsBySpec", this.statsService.statsCommentsBySpec());
        model.addAttribute("avgScoresBySpec", this.statsService.statsAvgScoresBySpec());

        return "stats";
    }
}
