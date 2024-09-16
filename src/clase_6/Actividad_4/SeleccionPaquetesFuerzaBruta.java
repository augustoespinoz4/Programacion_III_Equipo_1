package clase_6.Actividad_4;

import java.util.ArrayList;
import java.util.List;

public class SeleccionPaquetesFuerzaBruta {

    // Complejidad Temporal: O(2^n)

    static int[] costos = {12, 20, 15, 25};
    static int[] ganancias = {150, 200, 100, 300};
    static int presupuesto = 35;

    public static void main(String[] args) {
        int n = costos.length;
        int maxGanancia = 0;
        List<Integer> mejorCombinacion = null;

        // Probar todas las combinaciones posibles de paquetes
        for (int i = 0; i < (1 << n); i++) {
            int costoTotal = 0;
            int gananciaTotal = 0;
            List<Integer> combinacionActual = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    costoTotal += costos[j];
                    gananciaTotal += ganancias[j];
                    combinacionActual.add(j);
                }
            }

            if (costoTotal <= presupuesto && gananciaTotal > maxGanancia) {
                maxGanancia = gananciaTotal;
                mejorCombinacion = combinacionActual;
            }
        }

        System.out.println("Ganancia máxima (Fuerza Bruta): " + maxGanancia);
        System.out.println("Combinación óptima (Fuerza Bruta): " + mejorCombinacion);
    }
}