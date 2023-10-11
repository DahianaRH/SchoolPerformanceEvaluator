package org.sandy.school_performance_evaluator.domain.service;

import org.sandy.school_performance_evaluator.domain.model.StudentData;

import java.util.List;

public interface StudentDataProcessorService {
    double calculateAverage(StudentData student);

    List<Double> calculateSubjectAverages(StudentData student);
}
