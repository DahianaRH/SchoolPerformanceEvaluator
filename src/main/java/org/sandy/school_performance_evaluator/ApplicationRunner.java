package org.sandy.school_performance_evaluator;

import org.sandy.school_performance_evaluator.repository.GradeSubjectsRepositoryImpl;
import org.sandy.school_performance_evaluator.repository.StudentsCharacterizationRepositoryImpl;
import org.sandy.school_performance_evaluator.service.*;

import java.text.MessageFormat;

public class ApplicationRunner {
    public static void main(String[] args) {
        CourseGradeAverageCalculatorService courseGradeAverageCalculatorService = new CourseGradeAverageCalculatorServiceImpl(new StudentGradeAverageCalculatorServiceImpl(new StudentsCharacterizationRepositoryImpl()), new GradeSubjectsRepositoryImpl(new StudentsCharacterizationRepositoryImpl()), new StudentsCharacterizationRepositoryImpl());
        System.out.println(MessageFormat.format("Class average grades: ",courseGradeAverageCalculatorService.calculateCourseAverage()));

        StatisticsCalculatorService statisticsCalculatorService = new StatisticsCalculatorServiceImpl(new GradeSubjectsRepositoryImpl(new StudentsCharacterizationRepositoryImpl()));
        //System.out.println(MessageFormat.format("Class average grades: ",statisticsCalculatorService.calculateGradeMode()));

    }
}