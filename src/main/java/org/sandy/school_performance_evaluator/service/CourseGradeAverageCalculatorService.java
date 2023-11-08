package org.sandy.school_performance_evaluator.service;

import org.sandy.school_performance_evaluator.repository.StudentsCharacterizationUsingFileRepositoryImpl;

import java.util.List;

public interface CourseGradeAverageCalculatorService {

    double calculateCourseAverage();
    List<String> classifyStudentsWhoPassed(StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository);
    List<String> classifyStudentsWhoFailed(StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository);
    String findSubjectWithHighestGrade(StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository);
    String findStudentWithHighestAverage(StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository);
    String findStudentWithLowestAverage(StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository);
}
