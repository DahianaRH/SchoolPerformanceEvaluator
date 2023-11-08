package org.sandy.school_performance_evaluator;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;
import org.sandy.school_performance_evaluator.repository.GradeBySubjectRepositoryImpl;
import org.sandy.school_performance_evaluator.repository.StudentsCharacterizationUsingFileRepositoryImpl;
import org.sandy.school_performance_evaluator.service.*;

public class ApplicationRunner {
    public static void main(String[] args) {
        StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationUsingFileRepository =  new StudentsCharacterizationUsingFileRepositoryImpl();
        GradeBySubjectRepositoryImpl gradeSubjectsRepositoryImp = new GradeBySubjectRepositoryImpl(studentsCharacterizationUsingFileRepository);
        StudentGradeAverageCalculatorServiceImpl studentGradeAverageCalculatorServiceImpl = new StudentGradeAverageCalculatorServiceImpl(studentsCharacterizationUsingFileRepository);
        CourseGradeAverageCalculatorService courseGradeAverageCalculatorService = new CourseGradeAverageCalculatorServiceImpl(studentGradeAverageCalculatorServiceImpl, gradeSubjectsRepositoryImp, studentsCharacterizationUsingFileRepository);
        StatisticsCalculatorService statisticsCalculatorService = new StatisticsCalculatorServiceImpl(gradeSubjectsRepositoryImp);
        StudentCharacterization student = studentsCharacterizationUsingFileRepository.findAllStudentsCharacterization().get(0); // Por ejemplo, el primer estudiante
        double average = studentGradeAverageCalculatorServiceImpl.calculateAverage(student);

        System.out.println("Course average: " +courseGradeAverageCalculatorService.calculateCourseAverage());
        System.out.println("Students with an average above 3.0");
        System.out.println("\t"+courseGradeAverageCalculatorService.classifyStudentsWhoPassed(studentsCharacterizationUsingFileRepository));
        System.out.println("Students with an average lower than 3.0");
        System.out.println("\t"+courseGradeAverageCalculatorService.classifyStudentsWhoFailed(studentsCharacterizationUsingFileRepository));
        System.out.println("Subject with highest grade: " +courseGradeAverageCalculatorService.findSubjectWithHighestGrade(studentsCharacterizationUsingFileRepository));
        System.out.println("Student with highest average: " +courseGradeAverageCalculatorService.findStudentWithHighestAverage(studentsCharacterizationUsingFileRepository));
        System.out.println("Student with lowest average: " +courseGradeAverageCalculatorService.findStudentWithLowestAverage(studentsCharacterizationUsingFileRepository));
        System.out.println("Median by subject: " + statisticsCalculatorService.calculateMedianGradeBySubject(gradeSubjectsRepositoryImp));
        System.out.println("Average for " + student.name() + ": " + average);

        StudentCharacterization newStudentCharacterization = new StudentCharacterization("1028032866", "Laura Jimenez",3.2,
                3.4, 4.1, 3.4,4.5, 3.8, 4.0, 3.5);
        studentsCharacterizationUsingFileRepository.addStudentCharacterization(newStudentCharacterization);

        System.out.println("--------New student added to List--------");
        System.out.println(studentsCharacterizationUsingFileRepository.getStudentCharacterization("1028032866"));
    }
    }
