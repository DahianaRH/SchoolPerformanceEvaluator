package org.sandy.school_performance_evaluator.repository;

import org.sandy.school_performance_evaluator.model.GradeBySubject;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeBySubjectRepository {
    List<List<Double>> findAllGradeBySubjects();
    List<GradeBySubject> subjectsSummationGrades(String subjectName);
    double getGradeBySubject(String name);
    GradeBySubject addGradeBySubject(String name, double grade);
}
