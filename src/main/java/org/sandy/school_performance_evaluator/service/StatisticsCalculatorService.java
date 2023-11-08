package org.sandy.school_performance_evaluator.service;

import org.sandy.school_performance_evaluator.repository.GradeBySubjectInMemoryRepositoryImpl;

import java.util.List;

public interface StatisticsCalculatorService {
    List<Double> calculateMedianGradeBySubject(GradeBySubjectInMemoryRepositoryImpl gradeSubjectsRepository);
}
