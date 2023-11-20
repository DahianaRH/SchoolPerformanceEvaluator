package org.sandy.school_performance_evaluator.controller;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;
import org.sandy.school_performance_evaluator.service.CourseGradeAverageCalculatorService;
import org.sandy.school_performance_evaluator.service.StudentGradeAverageCalculatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentCharacterizationController {

    private final CourseGradeAverageCalculatorService courseGradeAverageCalculatorService;
    private final StudentGradeAverageCalculatorServiceImpl studentGradeAverageCalculatorService;

    @Autowired
    public StudentCharacterizationController(
            CourseGradeAverageCalculatorService courseGradeAverageCalculatorService,
            StudentGradeAverageCalculatorServiceImpl studentGradeAverageCalculatorService) {
        this.courseGradeAverageCalculatorService = courseGradeAverageCalculatorService;
        this.studentGradeAverageCalculatorService = studentGradeAverageCalculatorService;
    }

    @GetMapping("/highestAverageStudent")
    public String getStudentWithHighestAverage() {
        return courseGradeAverageCalculatorService.findStudentWithHighestAverage();
    }

    @GetMapping("/lowestAverageStudent")
    public String getStudentWithLowestAverage() {
        return courseGradeAverageCalculatorService.findStudentWithLowestAverage();
    }

    @PostMapping("/createStudent")
    public ResponseEntity<StudentCharacterization> createStudent(@RequestBody StudentCharacterization newStudentCharacterization) {
        StudentCharacterization studentCharacterization = studentGradeAverageCalculatorService.addStudentCharacterization(newStudentCharacterization);
        return ResponseEntity.status(HttpStatus.OK).body(studentCharacterization);
    }

    @GetMapping("/all")
    public List<StudentCharacterization> getAllStudents() {
        return studentGradeAverageCalculatorService.listStudentsCharacterization();
    }
}
