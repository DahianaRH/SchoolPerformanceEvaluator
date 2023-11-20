package org.sandy.school_performance_evaluator.controller;

import org.sandy.school_performance_evaluator.repository.StudentsCharacterizationUsingFileRepositoryImpl;
import org.sandy.school_performance_evaluator.service.CourseGradeAverageCalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseGradeAverageCalculatorService courseGradeAverageCalculatorService;

    public CourseController(CourseGradeAverageCalculatorService courseGradeAverageCalculatorService, StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository) {
        this.courseGradeAverageCalculatorService = courseGradeAverageCalculatorService;
    }

    @GetMapping("/average")
    public ResponseEntity<Double> getCourseAverage() {
        double average = courseGradeAverageCalculatorService.calculateCourseAverage();
        return ResponseEntity.ok(average);
    }

    @GetMapping("/passedStudents")
    public List<String> getPassedStudents() {
        return courseGradeAverageCalculatorService.classifyStudentsWhoPassed();
    }

    @GetMapping("/failedStudents")
    public List<String> getFailedStudents() {
        return courseGradeAverageCalculatorService.classifyStudentsWhoFailed();
    }
}