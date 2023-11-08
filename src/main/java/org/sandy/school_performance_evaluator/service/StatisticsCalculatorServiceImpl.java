package org.sandy.school_performance_evaluator.service;

import org.sandy.school_performance_evaluator.repository.GradeBySubjectRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatisticsCalculatorServiceImpl implements StatisticsCalculatorService{
    DecimalFormat decimalFormat = new DecimalFormat("#.#");
    private static final Logger logger = LoggerFactory.getLogger(StatisticsCalculatorServiceImpl.class);
    private GradeBySubjectRepositoryImpl gradeSubjectsRepository = null;
    public StatisticsCalculatorServiceImpl(GradeBySubjectRepositoryImpl gradeSubjectsRepository){
        this.gradeSubjectsRepository = gradeSubjectsRepository;
    }

    @Override
    public List<Double> calculateMedianGradeBySubject(GradeBySubjectRepositoryImpl gradeSubjectsRepository) {
        List<List<Double>> allSubjectsGrades = gradeSubjectsRepository.findAllGradeBySubjects();

        List<Double> medianGradesBySubject = new ArrayList<>();

        for (List<Double> subjectGrades : allSubjectsGrades) {
            double median = Double.valueOf(decimalFormat.format(calculateMedian(subjectGrades)).replace(',', '.'));
            medianGradesBySubject.add(median);
        }

        return medianGradesBySubject;
    }


    public double calculateMedian(List<Double> grades) {
        Collections.sort(grades); // Ordena las calificaciones de la materia actual.
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
