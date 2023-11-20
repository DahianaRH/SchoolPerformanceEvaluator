package org.sandy.school_performance_evaluator.service;

import org.sandy.school_performance_evaluator.repository.GradeSubjectRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class StatisticsCalculatorServiceImpl implements StatisticsCalculatorService {
    private final GradeSubjectRepositoryImpl gradeSubjectsRepository;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.#");

    @Autowired
    public StatisticsCalculatorServiceImpl(GradeSubjectRepositoryImpl gradeSubjectsRepository) {
        this.gradeSubjectsRepository = gradeSubjectsRepository;
    }

    @Override
    public double calculateMedianGradeBySubject(String subjectName) {
        List<List<Double>> allSubjectsGrades = gradeSubjectsRepository.findAllGradeBySubjects();
        int subjectIndex = getSubjectIndex(subjectName);

        if (subjectIndex < 0 || subjectIndex >= allSubjectsGrades.get(0).size()) {
            throw new IllegalArgumentException("Nombre de materia no válido: " + subjectName);
        }

        List<Double> subjectGrades = new java.util.ArrayList<>();

        for (List<Double> studentGrades : allSubjectsGrades) {
            subjectGrades.add(studentGrades.get(subjectIndex));
        }

        return calculateMedian(subjectGrades);
    }

    private int getSubjectIndex(String subjectName) {
        switch (subjectName.toLowerCase()) {
            case "calculus":
                return 0;
            case "spanish":
                return 1;
            case "social":
                return 2;
            case "physics":
                return 3;
            case "chemistry":
                return 4;
            case "citizeneducation":
                return 5;
            case "philosophy":
                return 6;
            case "sports":
                return 7;
            default:
                return -1;
        }
    }

    @Override
    public List<Double> calculateMedianGradesForAllSubjects() {
        List<List<Double>> allSubjectsGrades = gradeSubjectsRepository.findAllGradeBySubjects();
        List<Double> medianGrades = new ArrayList<>();
        for (int i = 0; i < allSubjectsGrades.get(0).size(); i++) {
            List<Double> subjectGrades = new ArrayList<>();

            for (List<Double> studentGrades : allSubjectsGrades) {
                subjectGrades.add(studentGrades.get(i));
            }

            double median = calculateMedian(subjectGrades);
            median = Double.valueOf(String.format("%.1f", median).replace(',', '.')); // Redondea a 1 decimal
            medianGrades.add(median);
        }
        return medianGrades;
    }

    private double calculateMedian(List<Double> grades) {
        Collections.sort(grades);
        int n = grades.size();

        if (n % 2 == 0) {
            int half1 = n / 2 - 1;
            int half2 = n / 2;
            double value1 = grades.get(half1);
            double value2 = grades.get(half2);
            return (value1 + value2) / 2.0;
        } else {
            int medio = n / 2;
            return grades.get(medio);
        }
    }
}