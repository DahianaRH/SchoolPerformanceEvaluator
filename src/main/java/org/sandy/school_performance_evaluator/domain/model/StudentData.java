package org.sandy.school_performance_evaluator.domain.model;
/**
 * Teniendo en cuenta que las materias tienen los siguientes créditos:
 * Cálculo           2 créditos
 * Español           2 créditos
 * Sociales          2 créditos
 * Fisica            2 créditos
 * Química           2 créditos
 * F. ciudadana      1 crédito
 * Filosofía         1 crédito
 * Ed. Física        1 crédito
 * Para un total de  13 créditos
 */
public record StudentData(String numeroIdentificacion,
                          String nombreCompleto,
                          double notaCalculo,
                          double notaEspanol,
                          double notaSociales,
                          double notaFisica,
                          double notaQuimica,
                          double notaFormacionCiudadana,
                          double notaFilosofia,
                          double notaEducacionFisica) {
    public record Estudiante (String numeroIdentificacion,
                              String nombreCompleto,
                              double notaCalculo,
                              double notaEspanol,
                              double notaSociales,
                              double notaFisica,
                              double notaQuimica,
                              double notaFormacionCiudadana,
                              double notaFilosofia,
                              double notaEducacionFisica) {


        /*public double calcularPromedioEstudiante() {
            double sumatoria = (notaCalculo * 2) + (notaEspanol * 2) + (notaSociales * 2) +
                    (notaFisica * 2) + (notaQuimica * 2) + (notaFormacionCiudadana * 1) +
                    (notaFilosofia * 1) + (notaEducacionFisica * 1);

            double promedio = (float) (sumatoria) / 13F;
            return promedio;
        }*/
    }
}
