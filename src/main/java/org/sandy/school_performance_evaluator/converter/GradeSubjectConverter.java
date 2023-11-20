package org.sandy.school_performance_evaluator.converter;

import org.sandy.school_performance_evaluator.repository.GradeSubjectRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GradeSubjectConverter implements Converter<String, GradeSubjectRepositoryImpl> {

    private final GradeSubjectRepositoryImpl gradeSubjectRepository;

    @Autowired
    public GradeSubjectConverter(GradeSubjectRepositoryImpl gradeSubjectRepository) {
        this.gradeSubjectRepository = gradeSubjectRepository;
    }

    @Override
    public GradeSubjectRepositoryImpl convert(String subjectName) {
        return gradeSubjectRepository;
    }
}