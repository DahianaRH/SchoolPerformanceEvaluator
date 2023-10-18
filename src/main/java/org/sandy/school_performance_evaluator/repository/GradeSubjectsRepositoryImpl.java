package org.sandy.school_performance_evaluator.repository;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;
import org.sandy.school_performance_evaluator.model.GradeSubjects;

import java.util.ArrayList;
import java.util.List;

public class GradeSubjectsRepositoryImpl implements GradeSubjectsRepository{
    private StudentsCharacterizationRepository studentsCharacterizationRepository = null;
    public GradeSubjectsRepositoryImpl(StudentsCharacterizationRepository studentsCharacterizationRepository){
        this.studentsCharacterizationRepository = studentsCharacterizationRepository;
    }

    @Override
    public List<List<Double>> findAllGradeBySubjects() {
        List<List<Double>> allSubjectsGrades = new ArrayList<>();

        List<Double> allCalculusGrades = new ArrayList<>();
        List<Double> allSpanishGrades = new ArrayList<>();
        List<Double> allSocialGrades = new ArrayList<>();
        List<Double> allPhysicsGrades = new ArrayList<>();
        List<Double> allChemistryGrades = new ArrayList<>();
        List<Double> allCitizenEducationGrades = new ArrayList<>();
        List<Double> allPhilosophyGrades = new ArrayList<>();
        List<Double> allSportsGrades = new ArrayList<>();

        for (StudentCharacterization studentsCharacterizationRepository : studentsCharacterizationRepository.findAllStudentsCharacterization()){
            allCalculusGrades.add(studentsCharacterizationRepository.calculusGrade());
            allSpanishGrades.add(studentsCharacterizationRepository.spanishGrade());
            allSocialGrades.add(studentsCharacterizationRepository.socialGrade());
            allPhysicsGrades.add(studentsCharacterizationRepository.physicsGrade());
            allChemistryGrades.add(studentsCharacterizationRepository.chemistryGrade());
            allCitizenEducationGrades.add(studentsCharacterizationRepository.citizenEducationGrade());
            allPhilosophyGrades.add(studentsCharacterizationRepository.philosophyGrade());
            allSportsGrades.add(studentsCharacterizationRepository.sportsGrade());
        }

        allSubjectsGrades.add(allCalculusGrades);
        allSubjectsGrades.add(allSpanishGrades);
        allSubjectsGrades.add(allSocialGrades);
        allSubjectsGrades.add(allPhysicsGrades);
        allSubjectsGrades.add(allChemistryGrades);
        allSubjectsGrades.add(allCitizenEducationGrades);
        allSubjectsGrades.add(allPhilosophyGrades);
        allSubjectsGrades.add(allSportsGrades);
        return allSubjectsGrades;
    }

    @Override
    public List<GradeSubjects> subjectsSummationGrades() {
        float summationCalculus = 0;
        float summationSpanish = 0;
        float summationSocial = 0;
        float summationPhysics = 0;
        float summationChemistry = 0;
        float summationCitizenEducation = 0;
        float summationPhilosophy = 0;
        float summationSports = 0;

        for (StudentCharacterization studentsCharacterizationRepository : studentsCharacterizationRepository.findAllStudentsCharacterization()) {
            summationCalculus += studentsCharacterizationRepository.calculusGrade();
            summationSpanish += studentsCharacterizationRepository.spanishGrade();
            summationSocial += studentsCharacterizationRepository.socialGrade();
            summationPhysics += studentsCharacterizationRepository.physicsGrade();
            summationChemistry += studentsCharacterizationRepository.chemistryGrade();
            summationCitizenEducation += studentsCharacterizationRepository.citizenEducationGrade();
            summationPhilosophy += studentsCharacterizationRepository.chemistryGrade();
            summationSports += studentsCharacterizationRepository.sportsGrade();
        }

        List<GradeSubjects> subjectsSummationGrades = List.of(
                new GradeSubjects("Calculus",summationCalculus),
                new GradeSubjects("Spanish",summationSpanish),
                new GradeSubjects("Social",summationSocial),
                new GradeSubjects("Physics",summationPhysics),
                new GradeSubjects("Chemistry",summationChemistry),
                new GradeSubjects("Citizen Education",summationCitizenEducation),
                new GradeSubjects("Philosophy",summationPhilosophy),
                new GradeSubjects("Sports",summationSports));
        return subjectsSummationGrades;
    }


}
