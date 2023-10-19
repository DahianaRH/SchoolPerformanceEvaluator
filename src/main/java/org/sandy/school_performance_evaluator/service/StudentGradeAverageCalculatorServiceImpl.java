package org.sandy.school_performance_evaluator.service;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;
import org.sandy.school_performance_evaluator.repository.StudentsCharacterizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

public class StudentGradeAverageCalculatorServiceImpl implements StudentGradeAverageCalculatorService {
    //StudentGradeAverageCalculatorServiceImpl --> (leer datos) --> interface StudentsCharacterizationRepository

    DecimalFormat decimalFormat = new DecimalFormat("#.#");
    private static final Logger logger = LoggerFactory.getLogger(StudentGradeAverageCalculatorServiceImpl.class);

    private final StudentsCharacterizationRepository studentsCharacterizationRepository;

    public StudentGradeAverageCalculatorServiceImpl(StudentsCharacterizationRepository studentsCharacterizationRepository){this.studentsCharacterizationRepository = studentsCharacterizationRepository; }
    @Override
    public double calculateAverage(StudentCharacterization student) {
        double sumatoria = (student.calculusGrade() * 2) + (student.spanishGrade() * 2) + (student.socialGrade() * 2) +
                (student.physicsGrade() * 2) + (student.chemistryGrade() * 2) + (student.citizenEducationGrade() * 1) +
                (student.philosophyGrade() * 1) + (student.sportsGrade() * 1);

        double average = Double.valueOf(decimalFormat.format((float) (sumatoria) / 13F).replace(',', '.'));
        return average;
    }

}
