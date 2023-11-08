package org.sandy.school_performance_evaluator.repository;

import org.sandy.school_performance_evaluator.model.StudentCharacterization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class StudentsCharacterizationUsingFileRepositoryImpl implements StudentsCharacterizationRepository{

    private static final Logger logger = LoggerFactory.getLogger( StudentsCharacterizationInMemoryRepositoryImpl.class);

    private List<StudentCharacterization> studentCharacterizationList;

    public StudentsCharacterizationUsingFileRepositoryImpl() {
        this.studentCharacterizationList = new ArrayList<>((LoadStudentsCharacterization()));//Al momento de construir el Repository se cargan los datos "quemados" en la clase
    }

    private List<StudentCharacterization> LoadStudentsCharacterization(){
        logger.info( "Cargando los datos predefinidos " );
        List<String> plainTextStudentCharacterizationList = readFileWithGrades();
        List<StudentCharacterization> studentCharacterizationsList = plainTextStudentCharacterizationList.stream().map( this::buildStudentCharacterization ).toList();
        return studentCharacterizationsList;
    }

    private List<String> readFileWithGrades(){
        //Este método lee el archivo y adiciona cada linea en una posicion de una Lista

        //Para el ejercicio se utiliza la ruta donde se encentra el archivo en el codigo fuente.
        //Este ruta es diferente al momento de empaquetar el proyecto

        Path path = Paths.get( "src/main/resources/StudentsCharacterization.txt");
        try (Stream<String> stream = Files.lines(path)) {
            return stream.toList();
        } catch (IOException x) {
            logger.error("IOException: {0}", x);
        }
        return Collections.emptyList();//Devuelve una lista vacía
    }

    private StudentCharacterization buildStudentCharacterization(String plainTextStudentCharacterizationList){
    /*Este metodo toma una linea del archivo para generar un vector
      y con dicho vector genera una nueva caracterización del estudiante nuevo.
    */
        String[] questionArray = plainTextStudentCharacterizationList.split(",");//En el archivo las notas vienen separadas por comas por ejemplo: UNIDAD 1,4.5D,2023-08-01

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
        this.studentCharacterizationList.add(newStudentCharacterization);

        return this.studentCharacterizationList.stream()
                .filter(isTheIdentificationNumbeOfTheStudent(newStudentCharacterization))//Busca al estudiante en la lista de estudiantes
                .findAny()
                .orElse(null);//Si no lo encuentra devuelve nulo
    }

    private Predicate<StudentCharacterization> isTheIdentificationNumbeOfTheStudent(StudentCharacterization newStudentCharacterization) {
        return p -> p.identificationNumber().equals(newStudentCharacterization.identificationNumber());
    }
}
