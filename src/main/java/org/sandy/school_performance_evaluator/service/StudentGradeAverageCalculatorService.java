package org.sandy.school_performance_evaluator.service;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;

import java.util.List;

public interface StudentGradeAverageCalculatorService {
    double calculateAverage(StudentCharacterization student);
}
