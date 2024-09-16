package clase_6.Actividad_4;

import java.util.ArrayList;
import java.util.List;

public class SeleccionPaquetesProgramacionDinamica {

    // Complejidad Temporal: O(n * P)

    static int[] costos = {12, 20, 15, 25};
    static int[] ganancias = {150, 200, 100, 300};
    static int presupuesto = 35;

    public static void main(String[] args) {
        int n = costos.length;
        int[][] dp = new int[n + 1][presupuesto + 1];

        // Llenar la tabla dp
        for (int i = 1; i <= n; i++) {
            int costo = costos[i - 1];
            int ganancia = ganancias[i - 1];

            for (int w = 0; w <= presupuesto; w++) {
                if (costo <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - costo] + ganancia);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // El valor máximo estará en la última celda
        System.out.println("Ganancia máxima (Programación Dinámica): " + dp[n][presupuesto]);

        // Rastrear los paquetes seleccionados
        List<Integer> paquetesSeleccionados = new ArrayList<>();
        int w = presupuesto;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                paquetesSeleccionados.add(i - 1);
                w -= costos[i - 1];
            }
        }

        System.out.println("Paquetes seleccionados (Programación Dinámica): " + paquetesSeleccionados);
    }
}
