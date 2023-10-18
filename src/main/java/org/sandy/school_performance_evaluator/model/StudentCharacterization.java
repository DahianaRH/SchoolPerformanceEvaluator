package org.sandy.school_performance_evaluator.model;
public record StudentCharacterization(String IdentificationNumber,
                                      String name,
                                      double calculusGrade,
                                      double spanishGrade,
                                      double socialGrade,
                                      double physicsGrade,
                                      double chemistryGrade,
                                      double citizenEducationGrade,
                                      double philosophyGrade,
                                      double sportsGrade) {
}
