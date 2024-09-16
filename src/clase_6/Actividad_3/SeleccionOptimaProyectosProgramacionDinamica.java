package clase_6.Actividad_3;

import java.util.ArrayList;
import java.util.List;

public class SeleccionOptimaProyectosProgramacionDinamica {

    // Complejidad Temporal: O(n * P)

    static int[] costos = {10, 15, 20, 25};
    static int[] beneficios = {100, 200, 150, 300};
    static int presupuesto = 50;

    public static void main(String[] args) {
        int n = costos.length;
        int[][] dp = new int[n + 1][presupuesto + 1];

        // Construir la tabla dp
        for (int i = 1; i <= n; i++) {
            int costo = costos[i - 1];
            int beneficio = beneficios[i - 1];

            for (int w = 0; w <= presupuesto; w++) {
                if (costo <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - costo] + beneficio);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // El valor máximo estará en la última celda
        System.out.println("Beneficio máximo (Programación Dinámica): " + dp[n][presupuesto]);

        // Rastrear los proyectos seleccionados
        List<Integer> proyectosSeleccionados = new ArrayList<>();
        int w = presupuesto;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                proyectosSeleccionados.add(i - 1);
                w -= costos[i - 1];
            }
        }

        System.out.println("Proyectos seleccionados (Programación Dinámica): " + proyectosSeleccionados);
    }
}