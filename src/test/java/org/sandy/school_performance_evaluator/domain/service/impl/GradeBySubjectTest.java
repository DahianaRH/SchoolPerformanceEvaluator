package org.sandy.school_performance_evaluator.domain.service.impl;

import org.junit.jupiter.api.Test;
import org.sandy.school_performance_evaluator.model.GradeBySubject;

import static org.junit.jupiter.api.Assertions.*;

class GradeBySubjectTest {

    @Test
    void testConstructorAndGetters() {
        GradeBySubject grade = new GradeBySubject("Math", 4.5);
        assertEquals("Math", grade.name());
        assertEquals(4.5, grade.grade());
    }
}