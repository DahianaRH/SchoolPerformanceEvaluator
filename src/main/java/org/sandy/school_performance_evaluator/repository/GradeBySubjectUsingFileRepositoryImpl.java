package org.sandy.school_performance_evaluator.repository;

import org.sandy.school_performance_evaluator.model.GradeBySubject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class GradeBySubjectUsingFileRepositoryImpl implements GradeBySubjectRepository {
    private StudentsCharacterizationRepository studentsCharacterizationRepository = null;
    public GradeBySubjectUsingFileRepositoryImpl(StudentsCharacterizationRepository studentsCharacterizationRepository){
        this.studentsCharacterizationRepository = studentsCharacterizationRepository;
    }

    @Override
    public List<List<Double>> findAllGradeBySubjects() {
        return null;
    }

    @Override
    public List<GradeBySubject> subjectsSummationGrades() {
        return null;
    }

    @Override
    public Optional<GradeBySubject> getGradeBySubject(String name) {
        return Optional.empty();
    }

    @Override
    public GradeBySubject addGradeBySubject(String name, double grade) {
        return null;
    }
}
