package org.sandy.school_performance_evaluator.repository;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;
import org.sandy.school_performance_evaluator.model.GradeBySubject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GradeSubjectRepositoryImpl implements GradeBySubjectRepository {
    private StudentsCharacterizationRepository studentsCharacterizationRepository;
    private String subjectName;

    public GradeSubjectRepositoryImpl(StudentsCharacterizationRepository studentsCharacterizationRepository) {
        this.studentsCharacterizationRepository = studentsCharacterizationRepository;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public List<List<Double>> findAllGradeBySubjects() {
        List<List<Double>> allSubjectsGrades = new ArrayList<>();

        for (StudentCharacterization student : studentsCharacterizationRepository.findAllStudentsCharacterization()) {
            List<Double> subjectGrades = new ArrayList<>();
            subjectGrades.add(getGradeForSubject(student, s -> s.calculusGrade()));
            subjectGrades.add(getGradeForSubject(student, s -> s.spanishGrade()));
            subjectGrades.add(getGradeForSubject(student, s -> s.socialGrade()));
            subjectGrades.add(getGradeForSubject(student, s -> s.physicsGrade()));
            subjectGrades.add(getGradeForSubject(student, s -> s.chemistryGrade()));
            subjectGrades.add(getGradeForSubject(student, s -> s.citizenEducationGrade()));
            subjectGrades.add(getGradeForSubject(student, s -> s.philosophyGrade()));
            subjectGrades.add(getGradeForSubject(student, s -> s.sportsGrade()));
            allSubjectsGrades.add(subjectGrades);
        }

        return allSubjectsGrades;
    }

    @Override
    public List<GradeBySubject> subjectsSummationGrades(String subjectName) {
        double summation = 0;

        for (StudentCharacterization student : studentsCharacterizationRepository.findAllStudentsCharacterization()) {
            summation += getGradeForSubject(student, getGradeExtractorByName(subjectName));
        }

        List<GradeBySubject> subjectsSummationGrades = new ArrayList<>();
        subjectsSummationGrades.add(new GradeBySubject(subjectName, summation));
        return subjectsSummationGrades;
    }

    @Override
    public double getGradeBySubject(String name) {
        double subjectGrade = 0;

        for (StudentCharacterization student : studentsCharacterizationRepository.findAllStudentsCharacterization()) {
            subjectGrade = getGradeForSubject(student, getGradeExtractorByName(name));
        }

        return subjectGrade;
    }

    @Override
    public GradeBySubject addGradeBySubject(String name, double grade) {
        return null;
    }

    private double getGradeForSubject(StudentCharacterization student, GradeExtractor gradeExtractor) {
        return gradeExtractor.extractGrade(student);
    }

    private interface GradeExtractor {
        double extractGrade(StudentCharacterization student);
    }

    private GradeExtractor getGradeExtractorByName(String name) {
        switch (name) {
            case "calculus":
                return s -> s.calculusGrade();
            case "spanish":
                return s -> s.spanishGrade();
            // Agrega más casos para otras materias
            default:
                throw new IllegalArgumentException("Materia no encontrada: " + name);
        }
    }
}