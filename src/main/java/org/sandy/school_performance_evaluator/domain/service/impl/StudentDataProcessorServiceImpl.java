package org.sandy.school_performance_evaluator.domain.service.impl;

import org.sandy.school_performance_evaluator.domain.model.StudentData;
import org.sandy.school_performance_evaluator.domain.service.StudentDataProcessorService;

import java.util.List;

public class StudentDataProcessorServiceImpl implements StudentDataProcessorService {

    @Override
    public double calculateAverage(StudentData student) {
        /*double sumatoria = (notaCalculo * 2) + (notaEspanol * 2) + (notaSociales * 2) +
                (notaFisica * 2) + (notaQuimica * 2) + (notaFormacionCiudadana * 1) +
                (notaFilosofia * 1) + (notaEducacionFisica * 1);

        double promedio = (float) (sumatoria) / 13F;
        return promedio;*/
        return 0;
    }

    @Override
    public List<Double> calculateSubjectAverages(StudentData student) {

        return null;
    }
}
