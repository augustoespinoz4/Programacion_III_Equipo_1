package clase_6;

import java.util.*;

public class Actividad_1 {

    public static void main(String[] args) {
        // Ejecutar fuerza bruta
        System.out.println("Ejecución de la Fuerza Bruta:");
        MochilaFuerzaBruta ejecutarFuerzaBruta = new MochilaFuerzaBruta();
        ejecutarFuerzaBruta.ejecutar();

        // Ejecutar programación dinámica
        System.out.println("\nEjecución de Programación Dinámica:");
        MochilaProgramacionDinamica ejecutarProgramacionDinamica = new MochilaProgramacionDinamica();
        ejecutarProgramacionDinamica.ejecutar();
    }

    static class MochilaFuerzaBruta {

        static int capacidadMochila = 6;
        static int[][] objetos = {
                {2, 3},  // {peso, valor}
                {3, 4},
                {4, 5},
                {5, 8}
        };

        void ejecutar() {
            int n = objetos.length;
            int maxValor = 0;
            List<Integer> mejorCombinacion = null;

            // Probar todas las combinaciones posibles de objetos
            for (int i = 0; i < (1 << n); i++) {
                int pesoTotal = 0;
                int valorTotal = 0;
                List<Integer> combinacionActual = new ArrayList<>();

                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) > 0) {
                        pesoTotal += objetos[j][0];
                        valorTotal += objetos[j][1];
                        combinacionActual.add(j);
                    }
                }

                if (pesoTotal <= capacidadMochila && valorTotal > maxValor) {
                    maxValor = valorTotal;
                    mejorCombinacion = combinacionActual;
                }
            }

            System.out.println("Valor máximo (Fuerza Bruta): " + maxValor);
            System.out.println("Combinación óptima (Fuerza Bruta): " + mejorCombinacion);
        }
    }

    static class MochilaProgramacionDinamica {

        static int capacidadMochila = 6;
        static int[][] objetos = {
                {2, 3},  // {peso, valor}
                {3, 4},
                {4, 5},
                {5, 8}
        };

        void ejecutar() {
            int n = objetos.length;
            int[][] dp = new int[n + 1][capacidadMochila + 1];

            // Construir la tabla dp
            for (int i = 1; i <= n; i++) {
                int peso = objetos[i - 1][0];
                int valor = objetos[i - 1][1];

                for (int w = 0; w <= capacidadMochila; w++) {
                    if (peso <= w) {
                        dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - peso] + valor);
                    } else {
                        dp[i][w] = dp[i - 1][w];
                    }
                }
            }

            // La última celda contiene el valor máximo
            System.out.println("Valor máximo (Programación Dinámica): " + dp[n][capacidadMochila]);

            // Rastrear la combinación óptima
            List<Integer> combinacionOptima = new ArrayList<>();
            int w = capacidadMochila;
            for (int i = n; i > 0 && w > 0; i--) {
                if (dp[i][w] != dp[i - 1][w]) {
                    combinacionOptima.add(i - 1);
                    w -= objetos[i - 1][0];
                }
            }

            Collections.reverse(combinacionOptima);
            System.out.println("Combinación óptima (Programación Dinámica): " + combinacionOptima);
        }
    }
}
