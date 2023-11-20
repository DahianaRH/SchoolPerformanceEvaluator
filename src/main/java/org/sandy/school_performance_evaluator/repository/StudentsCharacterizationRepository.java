package org.sandy.school_performance_evaluator.repository;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentsCharacterizationRepository {
    List<StudentCharacterization> findAllStudentsCharacterization();
    Optional<StudentCharacterization> getStudentCharacterization(String identificationNumber);
    StudentCharacterization addStudentCharacterization(StudentCharacterization newStudentCharacterization);
}
