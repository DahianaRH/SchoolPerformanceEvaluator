package org.sandy.school_performance_evaluator.service;

import org.sandy.school_performance_evaluator.repository.GradeSubjectsRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatisticsCalculatorServiceImpl implements StatisticsCalculatorService{
    private static final Logger logger = LoggerFactory.getLogger(StatisticsCalculatorServiceImpl.class);
    private GradeSubjectsRepositoryImpl gradeSubjectsRepository = null;
    public StatisticsCalculatorServiceImpl(GradeSubjectsRepositoryImpl gradeSubjectsRepository){
        this.gradeSubjectsRepository = gradeSubjectsRepository;
    }

    @Override
    public List<Double> calculateMedianGradeBySubject(GradeSubjectsRepositoryImpl gradeSubjectsRepository) {
        List<List<Double>> allSubjectsGrades = gradeSubjectsRepository.findAllGradeBySubjects(); // Asegúrate de que el método real devuelva todas las calificaciones de las materias.

        List<Double> medianGradesBySubject = new ArrayList<>();

        for (List<Double> subjectGrades : allSubjectsGrades) {
            double median = calculateMedian(subjectGrades);
            medianGradesBySubject.add(median);
        }

        return medianGradesBySubject;
    }


    public double calculateMedian(List<Double> grades) {
        Collections.sort(grades); // Ordena las calificaciones de la materia actual.
        int n = grades.size();

        if (n % 2 == 0) {
                // Si hay un número par de elementos, promedio de los dos elementos del medio
                int half1 = n / 2 - 1;
                int half2 = n / 2;
                double value1 = grades.get(half1);
                double value2 = grades.get(half2);
                return (value1 + value2) / 2.0;
        } else {
                // Si hay un número impar de elementos, el elemento del medio es la mediana
                int medio = n / 2;
                return grades.get(medio);
        }
    }

}
