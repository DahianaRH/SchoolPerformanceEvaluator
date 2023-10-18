package org.sandy.school_performance_evaluator.service;

import org.sandy.school_performance_evaluator.repository.StudentsCharacterizationRepositoryImpl;

import java.util.List;

public interface CourseGradeAverageCalculatorService {

    double calculateCourseAverage();
    List<String> classifyStudentsWhoPassed(StudentsCharacterizationRepositoryImpl studentsCharacterizationRepository);
    List<String> classifyStudentsWhoFailed(StudentsCharacterizationRepositoryImpl studentsCharacterizationRepository);
    String findSubjectWithHighestGrade(StudentsCharacterizationRepositoryImpl studentsCharacterizationRepository);
    String findStudentWithHighestAverage(StudentsCharacterizationRepositoryImpl studentsCharacterizationRepository);
    String findStudentWithLowestAverage(StudentsCharacterizationRepositoryImpl studentsCharacterizationRepository);
}
