package org.sandy.school_performance_evaluator.domain.service.impl;

import org.junit.jupiter.api.Test;
import org.sandy.school_performance_evaluator.model.StudentCharacterization;

import static org.junit.jupiter.api.Assertions.*;

class StudentCharacterizationTest {

    @Test
    void testConstructorAndGetters() {
        StudentCharacterization student = new StudentCharacterization("12345", "John Doe", 4.0, 3.5, 3.0, 4.5, 3.8, 4.2, 3.0, 4.0);
        assertEquals("12345", student.identificationNumber());
        assertEquals("John Doe", student.name());
        assertEquals(4.0, student.calculusGrade());
        assertEquals(3.5, student.spanishGrade());
        assertEquals(3.0, student.socialGrade());
        assertEquals(4.5, student.physicsGrade());
        assertEquals(3.8, student.chemistryGrade());
        assertEquals(4.2, student.citizenEducationGrade());
        assertEquals(3.0, student.philosophyGrade());
        assertEquals(4.0, student.sportsGrade());
    }
}
