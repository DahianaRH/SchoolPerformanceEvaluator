package org.sandy.school_performance_evaluator.repository;

import org.sandy.school_performance_evaluator.model.GradeBySubject;

import java.util.List;
import java.util.Optional;

public interface GradeBySubjectRepository {
    List<List<Double>> findAllGradeBySubjects();
    List<GradeBySubject> subjectsSummationGrades();
    Optional<GradeBySubject> getGradeBySubject(String name);
    GradeBySubject addGradeBySubject(String name, double grade);
}
