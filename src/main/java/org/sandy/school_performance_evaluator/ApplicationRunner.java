package org.sandy.school_performance_evaluator;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;
import org.sandy.school_performance_evaluator.repository.GradeSubjectsRepositoryImpl;
import org.sandy.school_performance_evaluator.repository.StudentsCharacterizationRepositoryImpl;
import org.sandy.school_performance_evaluator.service.*;

import java.text.MessageFormat;

public class ApplicationRunner {
    public static void main(String[] args) {
        StudentsCharacterizationRepositoryImpl studentsCharacterizationRepositoryImpl =  new StudentsCharacterizationRepositoryImpl();
        GradeSubjectsRepositoryImpl gradeSubjectsRepositoryImp = new GradeSubjectsRepositoryImpl(studentsCharacterizationRepositoryImpl);
        StudentGradeAverageCalculatorServiceImpl studentGradeAverageCalculatorServiceImpl = new StudentGradeAverageCalculatorServiceImpl(studentsCharacterizationRepositoryImpl);
        CourseGradeAverageCalculatorService courseGradeAverageCalculatorService = new CourseGradeAverageCalculatorServiceImpl(studentGradeAverageCalculatorServiceImpl, gradeSubjectsRepositoryImp, studentsCharacterizationRepositoryImpl);
        StatisticsCalculatorService statisticsCalculatorService = new StatisticsCalculatorServiceImpl(gradeSubjectsRepositoryImp);
        StudentCharacterization student = studentsCharacterizationRepositoryImpl.findAllStudentsCharacterization().get(0); // Por ejemplo, el primer estudiante
        double average = studentGradeAverageCalculatorServiceImpl.calculateAverage(student);

        System.out.println("Course average: " +courseGradeAverageCalculatorService.calculateCourseAverage());
        System.out.println("Students with an average above 3.0");
        System.out.println("\t"+courseGradeAverageCalculatorService.classifyStudentsWhoPassed(studentsCharacterizationRepositoryImpl));
        System.out.println("Students with an average lower than 3.0");
        System.out.println("\t"+courseGradeAverageCalculatorService.classifyStudentsWhoFailed(studentsCharacterizationRepositoryImpl));
        System.out.println("Subject with highest grade: " +courseGradeAverageCalculatorService.findSubjectWithHighestGrade(studentsCharacterizationRepositoryImpl));
        System.out.println("Student with highest average: " +courseGradeAverageCalculatorService.findStudentWithHighestAverage(studentsCharacterizationRepositoryImpl));
        System.out.println("Student with lowest average: " +courseGradeAverageCalculatorService.findStudentWithLowestAverage(studentsCharacterizationRepositoryImpl));
        System.out.println("Median by subject: " + statisticsCalculatorService.calculateMedianGradeBySubject(gradeSubjectsRepositoryImp));
        System.out.println("Average for " + student.name() + ": " + average);}
    }
