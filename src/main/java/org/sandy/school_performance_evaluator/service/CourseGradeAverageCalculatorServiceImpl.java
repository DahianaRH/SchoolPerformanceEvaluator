package org.sandy.school_performance_evaluator.service;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;
import org.sandy.school_performance_evaluator.repository.GradeSubjectsRepositoryImpl;
import org.sandy.school_performance_evaluator.repository.StudentsCharacterizationRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.util.List;

public class CourseGradeAverageCalculatorServiceImpl implements CourseGradeAverageCalculatorService{
    private static final Logger logger = LoggerFactory.getLogger(CourseGradeAverageCalculatorServiceImpl.class);
    DecimalFormat decimalFormat = new DecimalFormat("#.#");
    private StudentGradeAverageCalculatorServiceImpl studentGradeAverageCalculatorService = null;
    private GradeSubjectsRepositoryImpl gradeSubjectsRepository = null;
    private StudentsCharacterizationRepositoryImpl studentsCharacterizationRepository = null;
    public CourseGradeAverageCalculatorServiceImpl(
            StudentGradeAverageCalculatorServiceImpl studentGradeAverageCalculatorService,
            GradeSubjectsRepositoryImpl gradeSubjectsRepository,
            StudentsCharacterizationRepositoryImpl studentsCharacterizationRepository)
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
        return courseAverage;
    }

    @Override
    public List<String> classifyStudentsWhoPassed(StudentsCharacterizationRepositoryImpl studentsCharacterizationRepository) {
        String average;
        List<String> studentsWhoPassed = null;
        double averageNum;
        int index = 0;
        for (StudentCharacterization studentCharacterization : studentsCharacterizationRepository.findAllStudentsCharacterization()) {
            average = decimalFormat.format(studentGradeAverageCalculatorService.calculateAverage(studentCharacterization)).replace(',','.');
            averageNum = Double.valueOf(average);
            if(averageNum >= 3.0) {
                index++;
                String studentInfo = studentCharacterization.name() + " - Promedio: " + average;

                studentsWhoPassed.add(studentInfo);
            }
        }
        return studentsWhoPassed;
    }

    @Override
    public List<String> classifyStudentsWhoFailed(StudentsCharacterizationRepositoryImpl studentsCharacterizationRepository) {
        String average;
        List<String> studentsWhoFailed = null;
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
    public String findSubjectWithHighestGrade(StudentsCharacterizationRepositoryImpl studentsCharacterizationRepository) {
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

        String highestGradeSubject = subjectName+" "+highestGrade+" "+studentName;
        return highestGradeSubject;
    }

    @Override
    public String findStudentWithHighestAverage(StudentsCharacterizationRepositoryImpl studentsCharacterizationRepository) {
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

        return studentWithHighestAverage+" "+highestAverage;
    }

    @Override
    public String findStudentWithLowestAverage(StudentsCharacterizationRepositoryImpl studentsCharacterizationRepository) {
        double average;
        double lowestAverage = 0.0;
        String studentWithLowestAverage = "";

        for (StudentCharacterization studentCharacterization : studentsCharacterizationRepository.findAllStudentsCharacterization()) {
            average = studentGradeAverageCalculatorService.calculateAverage(studentCharacterization);
            if (lowestAverage > average){
                lowestAverage = average;
                studentWithLowestAverage = studentCharacterization.name();
            }
        }

        return studentWithLowestAverage+" "+lowestAverage;
    }
}