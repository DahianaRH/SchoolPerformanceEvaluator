package org.sandy.school_performance_evaluator.controller;

import org.sandy.school_performance_evaluator.service.StatisticsCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistics")
@CrossOrigin(origins = "*")
public class StatisticsController {

    private final StatisticsCalculatorService statisticsCalculatorService;

    @Autowired
    public StatisticsController(StatisticsCalculatorService statisticsCalculatorService) {
        this.statisticsCalculatorService = statisticsCalculatorService;
    }

    @GetMapping("/medianGradesForAllSubjects")
    public List<Double> getMedianGradesForAllSubjects() {
        return statisticsCalculatorService.calculateMedianGradesForAllSubjects();
    }

    @GetMapping("/medianGradesBySubject/{subjectName}")
    public double getMedianGradesBySubject(@PathVariable String subjectName) {
        return statisticsCalculatorService.calculateMedianGradeBySubject(subjectName);
    }
}