package org.sandy.school_performance_evaluator.service;

import org.sandy.school_performance_evaluator.repository.GradeSubjectRepositoryImpl;

import java.util.List;

public interface StatisticsCalculatorService {
    double calculateMedianGradeBySubject(String subjectName);
    List<Double> calculateMedianGradesForAllSubjects();
}
