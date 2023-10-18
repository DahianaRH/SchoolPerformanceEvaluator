package org.sandy.school_performance_evaluator.repository;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;

import java.util.List;

public interface StudentsCharacterizationRepository {
    List<StudentCharacterization> findAllStudentsCharacterization();
}
