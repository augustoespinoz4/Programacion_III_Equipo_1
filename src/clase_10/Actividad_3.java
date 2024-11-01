package clase_10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Actividad_3 {
    static final int N = 4;  // Tamaño del tablero 4x4
    static int totalConfiguraciones = 0;  // Contador de configuraciones válidas

    public static void main(String[] args) {
        // Arreglos para las posiciones de computadoras e impresoras
        int[] computadoras = new int[N];  // Posiciones de computadoras
        int[] impresoras = new int[N];     // Posiciones de impresoras

        // Lista para almacenar todas las configuraciones válidas
        List<List<String>> configuracionesValidas = new ArrayList<>();
        backtrack(computadoras, impresoras, 0, configuracionesValidas, new HashSet<>(), new HashSet<>());

        // Mostrar todas las configuraciones válidas
        for (List<String> configuracion : configuracionesValidas) {
            imprimirConfiguracion(configuracion);
        }

        System.out.println("Total de configuraciones válidas: " + configuracionesValidas.size());
    }

    // Método de backtracking para colocar computadoras e impresoras
    static void backtrack(int[] computadoras, int[] impresoras, int fila, List<List<String>> configuraciones,
                          Set<Integer> ocupadasComputadoras, Set<Integer> ocupadasImpresoras) {
        if (fila == N) {
            // Si se ha llegado a la última fila, guardar la configuración
            List<String> configuracion = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < N; j++) {
                    if (computadoras[i] == j) {
                        sb.append("C ");  // C para Computadora
                    } else if (impresoras[i] == j) {
                        sb.append("P ");  // P para Impresora
                    } else {
                        sb.append(". ");  // . para espacio vacío
                    }
                }
                configuracion.add(sb.toString());
            }
            configuraciones.add(configuracion);
            totalConfiguraciones++;  // Contar una configuración válida
            return;
        }

        // Colocar computadoras en la fila actual
        for (int colC = 0; colC < N; colC++) {
            if (!ocupadasComputadoras.contains(colC)) {
                computadoras[fila] = colC;  // Colocar computadora en la posición
                ocupadasComputadoras.add(colC);  // Marcar columna como ocupada

                // Colocar impresoras en las filas restantes
                for (int colP = 0; colP < N; colP++) {
                    if (!ocupadasImpresoras.contains(colP) && colP != colC) {  // Verificar si la columna no está ocupada y no coincide con la computadora
                        impresoras[fila] = colP;  // Colocar impresora en la posición
                        ocupadasImpresoras.add(colP);  // Marcar columna como ocupada

                        // Llamar al siguiente nivel de backtracking
                        backtrack(computadoras, impresoras, fila + 1, configuraciones, ocupadasComputadoras, ocupadasImpresoras);

                        // Deshacer la colocación para el backtracking
                        ocupadasImpresoras.remove(colP);
                    }
                }
                // Deshacer la colocación de la computadora
                ocupadasComputadoras.remove(colC);
            }
        }
    }

    // Método para imprimir la configuración en el tablero
    static void imprimirConfiguracion(List<String> configuracion) {
        for (String fila : configuracion) {
            System.out.println(fila);
        }
        System.out.println();  // Línea en blanco entre configuraciones
    }
}
