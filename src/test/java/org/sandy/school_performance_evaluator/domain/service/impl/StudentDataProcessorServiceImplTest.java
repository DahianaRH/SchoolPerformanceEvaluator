package org.sandy.school_performance_evaluator.domain.service.impl;

import org.junit.jupiter.api.Test;
import org.sandy.school_performance_evaluator.domain.model.StudentData;
import org.sandy.school_performance_evaluator.domain.service.StudentDataProcessorService;

import static org.junit.jupiter.api.Assertions.*;

class StudentDataProcessorServiceImplTest {

    private StudentDataProcessorService studentDataProcessorService = new StudentDataProcessorServiceImpl();
    @Test
    void testCalculateAverage() {
        StudentData student = null;
        double average = studentDataProcessorService.calculateAverage(student);
        assertNotNull(average);
    }

    @Test
    void testCalculateSubjectAverages() {
    }
}