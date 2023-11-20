package org.sandy.school_performance_evaluator.service;

import org.sandy.school_performance_evaluator.repository.StudentsCharacterizationUsingFileRepositoryImpl;

import java.util.List;

public interface CourseGradeAverageCalculatorService {

    double calculateCourseAverage();
    List<String> classifyStudentsWhoPassed();
    List<String> classifyStudentsWhoFailed();
    String findSubjectWithHighestGrade(StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository);
    String findStudentWithHighestAverage();
    String findStudentWithLowestAverage();
}
