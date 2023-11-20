package org.sandy.school_performance_evaluator.repository;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Repository
public class StudentsCharacterizationUsingFileRepositoryImpl implements StudentsCharacterizationRepository{

    private static final Logger logger = LoggerFactory.getLogger( StudentsCharacterizationInMemoryRepositoryImpl.class);

    private List<StudentCharacterization> studentCharacterizationList;

    public StudentsCharacterizationUsingFileRepositoryImpl() {
        this.studentCharacterizationList = new ArrayList<>((LoadStudentsCharacterization()));
    }

    private List<StudentCharacterization> LoadStudentsCharacterization(){
        logger.info( "Cargando los datos predefinidos " );
        List<String> plainTextStudentCharacterizationList = readFileWithGrades();
        List<StudentCharacterization> studentCharacterizationsList = plainTextStudentCharacterizationList.stream().map( this::buildStudentCharacterization ).toList();
        return studentCharacterizationsList;
    }

    private List<String> readFileWithGrades(){

        Path path = Paths.get( "src/main/java/resources/StudentsCharacterization.txt");
        try (Stream<String> stream = Files.lines(path)) {
            return stream.toList();
        } catch (IOException x) {
            logger.error("IOException: {0}", x);
        }
        return Collections.emptyList();
    }

    private StudentCharacterization buildStudentCharacterization(String plainTextStudentCharacterizationList){
        String[] questionArray = plainTextStudentCharacterizationList.split(",");

        StudentCharacterization StudentCharacterization = new StudentCharacterization(questionArray[0], questionArray[1], Double.valueOf(questionArray[2]),Double.valueOf(questionArray[3]), Double.valueOf(questionArray[4]),
                Double.valueOf(questionArray[5]), Double.valueOf(questionArray[6]), Double.valueOf(questionArray[7]), Double.valueOf(questionArray[8]), Double.valueOf(questionArray[9]));

        return StudentCharacterization;
    }

    @Override
    public List<StudentCharacterization> findAllStudentsCharacterization() {
        return studentCharacterizationList;
    }

    @Override
    public Optional<StudentCharacterization> getStudentCharacterization(String identificationNumber) {
        return this.studentCharacterizationList.stream().filter(p->p.identificationNumber().equals(identificationNumber)).findAny();
    }

    @Override
    public StudentCharacterization addStudentCharacterization(StudentCharacterization newStudentCharacterization) {
        try {
            this.studentCharacterizationList.add(newStudentCharacterization);
            return newStudentCharacterization;
        } catch (Exception e) {
            logger.error("Error adding student characterization: {}", e.getMessage());
            return null; // or throw an exception if appropriate
        }
    }

    private Predicate<StudentCharacterization> isTheIdentificationNumbeOfTheStudent(StudentCharacterization newStudentCharacterization) {
        return p -> {
            String identificationNumber = p.identificationNumber();
            return identificationNumber != null && identificationNumber.equals(newStudentCharacterization.identificationNumber());
        };
    }
}
