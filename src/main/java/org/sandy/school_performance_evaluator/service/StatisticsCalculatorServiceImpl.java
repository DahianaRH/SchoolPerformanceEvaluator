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
        // Obtener la posición de la materia en la secuencia
        int subjectIndex = getSubjectIndex(subjectName);

        // Verificar si el índice es válido
        if (subjectIndex < 0 || subjectIndex >= allSubjectsGrades.get(0).size()) {
            throw new IllegalArgumentException("Nombre de materia no válido: " + subjectName);
        }

        List<Double> subjectGrades = new java.util.ArrayList<>();

        // Iterar sobre las filas (estudiantes) y obtener las calificaciones de la materia específica
        for (List<Double> studentGrades : allSubjectsGrades) {
            subjectGrades.add(studentGrades.get(subjectIndex));
        }

        // Calcular la mediana y retornar el resultado
        return calculateMedian(subjectGrades);
    }

    private int getSubjectIndex(String subjectName) {
        // Asignar un índice a cada materia según la secuencia dada
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
                return -1; // Retorna -1 si el nombre de la materia no es válido
        }
    }

    @Override
    public List<Double> calculateMedianGradesForAllSubjects() {
        List<List<Double>> allSubjectsGrades = gradeSubjectsRepository.findAllGradeBySubjects();
        List<Double> medianGrades = new ArrayList<>();
        // Itera sobre las columnas (materias)
        for (int i = 0; i < allSubjectsGrades.get(0).size(); i++) {
            List<Double> subjectGrades = new ArrayList<>();

            // Itera sobre las filas (estudiantes)
            for (List<Double> studentGrades : allSubjectsGrades) {
                subjectGrades.add(studentGrades.get(i));
            }

            // Calcula la mediana y agrega al resultado
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