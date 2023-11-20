package org.sandy.school_performance_evaluator.service;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;
import org.sandy.school_performance_evaluator.repository.StudentsCharacterizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class StudentGradeAverageCalculatorServiceImpl implements StudentGradeAverageCalculatorService {
    DecimalFormat decimalFormat = new DecimalFormat("#.#");
    private static final Logger logger = LoggerFactory.getLogger(StudentGradeAverageCalculatorServiceImpl.class);
    private final StudentsCharacterizationRepository studentsCharacterizationRepository;

    @Autowired
    public StudentGradeAverageCalculatorServiceImpl(StudentsCharacterizationRepository studentsCharacterizationRepository){this.studentsCharacterizationRepository = studentsCharacterizationRepository; }
    @Override
    public double calculateAverage(StudentCharacterization student) {
        double sumatoria = (student.calculusGrade() * 2) + (student.spanishGrade() * 2) + (student.socialGrade() * 2) +
                (student.physicsGrade() * 2) + (student.chemistryGrade() * 2) + (student.citizenEducationGrade() * 1) +
                (student.philosophyGrade() * 1) + (student.sportsGrade() * 1);
        double average = Double.valueOf(decimalFormat.format((float) (sumatoria) / 13F).replace(',', '.'));
        return average;
    }

    @Override
    public List<StudentCharacterization> listStudentsCharacterization(){ return this.studentsCharacterizationRepository.findAllStudentsCharacterization();}

    @Override
    public StudentCharacterization addStudentCharacterization(StudentCharacterization newStudentCharacterization){
        try {
            return studentsCharacterizationRepository.addStudentCharacterization(newStudentCharacterization);
        } catch (Exception e) {
            logger.error("Error adding student characterization: {}", e.getMessage());
            return null;
        }
    }
}
