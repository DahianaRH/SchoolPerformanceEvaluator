package org.sandy.school_performance_evaluator.repository;

import org.sandy.school_performance_evaluator.model.GradeSubjects;

import java.util.List;

public interface GradeSubjectsRepository {
    List<List<Double>> findAllGradeBySubjects();
    List<GradeSubjects> subjectsSummationGrades();
}
