package org.sandy.school_performance_evaluator.repository;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;
import org.sandy.school_performance_evaluator.model.GradeBySubject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GradeBySubjectInMemoryRepositoryImpl implements GradeBySubjectRepository {
    private StudentsCharacterizationRepository studentsCharacterizationRepository = null;
    public GradeBySubjectInMemoryRepositoryImpl(StudentsCharacterizationRepository studentsCharacterizationRepository){
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
    public List<GradeBySubject> subjectsSummationGrades() {
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

        List<GradeBySubject> subjectsSummationGrades = List.of(
                new GradeBySubject("Calculus",summationCalculus),
                new GradeBySubject("Spanish",summationSpanish),
                new GradeBySubject("Social",summationSocial),
                new GradeBySubject("Physics",summationPhysics),
                new GradeBySubject("Chemistry",summationChemistry),
                new GradeBySubject("Citizen Education",summationCitizenEducation),
                new GradeBySubject("Philosophy",summationPhilosophy),
                new GradeBySubject("Sports",summationSports));
        return subjectsSummationGrades;
    }

    @Override
    public Optional<GradeBySubject> getGradeBySubject(String name) {
        return Optional.empty();
    }

    @Override
    public GradeBySubject addGradeBySubject(String name, double grade) {
        return null;
    }
}
