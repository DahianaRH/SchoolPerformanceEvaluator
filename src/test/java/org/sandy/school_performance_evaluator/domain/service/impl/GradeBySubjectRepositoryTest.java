package org.sandy.school_performance_evaluator.domain.service.impl;

import org.junit.jupiter.api.Test;
import org.sandy.school_performance_evaluator.model.GradeBySubject;
import org.sandy.school_performance_evaluator.repository.GradeBySubjectRepository;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class GradeBySubjectRepositoryTest {

    private GradeBySubjectRepository gradeRepository;

    @Test
    void testFindAllGradeBySubjects() {
        List<List<Double>> allSubjectsGrades = gradeRepository.findAllGradeBySubjects();
        // Agrega las aserciones según lo esperado
    }

    @Test
    void testSubjectsSummationGrades() {
        //List<GradeBySubject> subjectsSummationGrades = gradeRepository.subjectsSummationGrades();
        // Agrega las aserciones según lo esperado
    }

    @Test
    void testGetGradeBySubject() {
        // Agrega las pruebas para getGradeBySubject
    }

    @Test
    void testAddGradeBySubject() {
        // Agrega las pruebas para addGradeBySubject
    }
}
