package org.sandy.school_performance_evaluator.service;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;
import org.sandy.school_performance_evaluator.repository.GradeBySubjectRepositoryImpl;
import org.sandy.school_performance_evaluator.repository.StudentsCharacterizationUsingFileRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CourseGradeAverageCalculatorServiceImpl implements CourseGradeAverageCalculatorService{
    private static final Logger logger = LoggerFactory.getLogger(CourseGradeAverageCalculatorServiceImpl.class);
    DecimalFormat decimalFormat = new DecimalFormat("#.#");
    private StudentGradeAverageCalculatorServiceImpl studentGradeAverageCalculatorService = null;
    private GradeBySubjectRepositoryImpl gradeSubjectsRepository = null;
    private StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository = null;
    public CourseGradeAverageCalculatorServiceImpl(
            StudentGradeAverageCalculatorServiceImpl studentGradeAverageCalculatorService,
            GradeBySubjectRepositoryImpl gradeSubjectsRepository,
            StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository)
    {
        this.studentGradeAverageCalculatorService = studentGradeAverageCalculatorService;
        this.gradeSubjectsRepository = gradeSubjectsRepository;
        this.studentsCharacterizationRepository = studentsCharacterizationRepository;
    }
    @Override
    public double calculateCourseAverage() {
        int studentsNumber = 0;
        double summationGrades = 0.0;
        double courseAverage = 0.0;

        for (StudentCharacterization studentCharacterization: studentsCharacterizationRepository.findAllStudentsCharacterization()) {
            studentsNumber++;
            summationGrades += studentGradeAverageCalculatorService.calculateAverage(studentCharacterization);
        }
        courseAverage = summationGrades / studentsNumber;
        return Double.valueOf(decimalFormat.format(courseAverage).replace(',', '.'));
    }

    @Override
    public List<String> classifyStudentsWhoPassed(StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository) {
        String average;
        List<String> studentsWhoPassed = new ArrayList<>();
        double averageNum;
        int index = 0;
        for (StudentCharacterization studentCharacterization : studentsCharacterizationRepository.findAllStudentsCharacterization()) {
            average = decimalFormat.format(studentGradeAverageCalculatorService.calculateAverage(studentCharacterization)).replace(',', '.');
            averageNum = Double.valueOf(average);
            String studentInfo = null;
            if (averageNum >= 3.0) {
                index++;
                studentInfo = studentCharacterization.name() + " - Promedio: " + average;
            }
            studentsWhoPassed.add(studentInfo);
        }
        return studentsWhoPassed;
    }

    @Override
    public List<String> classifyStudentsWhoFailed(StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository) {
        String average;
        List<String> studentsWhoFailed = new ArrayList<>();
        double averageNum;
        int index = 0;
        for (StudentCharacterization studentCharacterization : studentsCharacterizationRepository.findAllStudentsCharacterization()) {
            average = decimalFormat.format(studentGradeAverageCalculatorService.calculateAverage(studentCharacterization)).replace(',','.');
            averageNum = Double.valueOf(average);
            if(averageNum < 3.0) {
                index++;
                String studentInfo = studentCharacterization.name() + " - Promedio: " + average;

                studentsWhoFailed.add(studentInfo);
            }
        }
        return studentsWhoFailed;
    }

    @Override
    public String findSubjectWithHighestGrade(StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository) {
        double highestGrade = 0.0;
        String subjectName = "";
        String studentName = "";
        int index = 0;
        for (StudentCharacterization studentCharacterization : studentsCharacterizationRepository.findAllStudentsCharacterization()) {
            double[] studentsGrade = new double[8];
            studentsGrade[0] = studentCharacterization.calculusGrade();
            studentsGrade[1] = studentCharacterization.spanishGrade();
            studentsGrade[2] = studentCharacterization.socialGrade();
            studentsGrade[3] = studentCharacterization.physicsGrade();
            studentsGrade[4] = studentCharacterization.chemistryGrade();
            studentsGrade[5] = studentCharacterization.citizenEducationGrade();
            studentsGrade[6] = studentCharacterization.philosophyGrade();
            studentsGrade[7] = studentCharacterization.sportsGrade();

            for (double grade: studentsGrade) {
                index++;
                if (highestGrade<grade){
                    highestGrade = grade;
                    studentName = studentCharacterization.name();
                    switch (index){
                        case 1:
                            subjectName = "Cálculo";
                            break;
                        case 2:
                            subjectName = "Español";
                            break;
                        case 3:
                            subjectName = "Ciencias Sociales";
                            break;
                        case 4:
                            subjectName = "Física";
                            break;
                        case 5:
                            subjectName = "Química";
                            break;
                        case 6:
                            subjectName = "Formación Ciudadanda";
                            break;
                        case 7:
                            subjectName = "Filosofía";
                            break;
                        case 8:
                            subjectName = "Educación Física";
                            break;
                        default: subjectName = "default";
                    }
                }
            }
            index = 0;
        }

        String highestGradeSubject = subjectName+" "+decimalFormat.format(highestGrade)+" "+studentName;
        return highestGradeSubject;
    }

    @Override
    public String findStudentWithHighestAverage(StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository) {
        double average;
        double highestAverage = 0.0;
        String studentWithHighestAverage = "";

        for (StudentCharacterization studentCharacterization : studentsCharacterizationRepository.findAllStudentsCharacterization()) {
            average = studentGradeAverageCalculatorService.calculateAverage(studentCharacterization);
            if (highestAverage < average){
                highestAverage = average;
                studentWithHighestAverage = studentCharacterization.name();
            }
        }

        return studentWithHighestAverage+" "+decimalFormat.format(highestAverage);
    }

    @Override
    public String findStudentWithLowestAverage(StudentsCharacterizationUsingFileRepositoryImpl studentsCharacterizationRepository) {
        double average;
        double lowestAverage = 5.0;
        String studentWithLowestAverage = "";

        for (StudentCharacterization studentCharacterization : studentsCharacterizationRepository.findAllStudentsCharacterization()) {
            average = studentGradeAverageCalculatorService.calculateAverage(studentCharacterization);
            if (lowestAverage > average){
                lowestAverage = average;
                studentWithLowestAverage = studentCharacterization.name();
            }
        }

        return studentWithLowestAverage+" "+decimalFormat.format(lowestAverage);
    }
}
