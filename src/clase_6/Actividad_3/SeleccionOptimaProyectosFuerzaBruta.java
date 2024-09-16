package clase_6.Actividad_3;

import java.util.*;

public class SeleccionOptimaProyectosFuerzaBruta {

    // Complejidad Temporal: O(2^n)

    static int[] costos = {10, 15, 20, 25};
    static int[] beneficios = {100, 200, 150, 300};
    static int presupuesto = 50;

    public static void main(String[] args) {
        int n = costos.length;
        int maxBeneficio = 0;
        List<Integer> mejorCombinacion = null;

        // Probar todas las combinaciones posibles de proyectos
        for (int i = 0; i < (1 << n); i++) {
            int costoTotal = 0;
            int beneficioTotal = 0;
            List<Integer> combinacionActual = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    costoTotal += costos[j];
                    beneficioTotal += beneficios[j];
                    combinacionActual.add(j);
                }
            }

            if (costoTotal <= presupuesto && beneficioTotal > maxBeneficio) {
                maxBeneficio = beneficioTotal;
                mejorCombinacion = combinacionActual;
            }
        }

        System.out.println("Beneficio máximo (Fuerza Bruta): " + maxBeneficio);
        System.out.println("Combinación óptima (Fuerza Bruta): " + mejorCombinacion);
    }
}
