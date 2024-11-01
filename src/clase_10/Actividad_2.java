package clase_10;

import java.util.ArrayList;
import java.util.List;

public class Actividad_2 {
    static int N = 4;  // Tamaño del tablero 4x4
    static int soluciones = 0;  // Contador de soluciones válidas

    public static void main(String[] args) {
        // Generar todas las configuraciones válidas
        List<List<Integer>> configuraciones = new ArrayList<>();
        colocarElementos(new int[N], 0, configuraciones);

        // Mostrar todas las configuraciones válidas
        for (List<Integer> configuracion : configuraciones) {
            imprimirConfiguracion(configuracion);
        }

        System.out.println("Total de configuraciones válidas: " + configuraciones.size());
    }

    // Método para colocar escritorios y sillas
    static void colocarElementos(int[] posiciones, int fila, List<List<Integer>> configuraciones) {
        if (fila == N) {
            // Si se ha llegado a la última fila, guardar la configuración
            List<Integer> configuracion = new ArrayList<>();
            for (int col : posiciones) {
                configuracion.add(col);
            }
            configuraciones.add(configuracion);
            soluciones++;  // Contar una solución válida
            return;
        }

        for (int col = 0; col < N; col++) {
            // Verificar si la posición actual está ocupada
            if (!isOccupied(posiciones, fila, col)) {
                posiciones[fila] = col;  // Colocar escritorio en la posición
                colocarElementos(posiciones, fila + 1, configuraciones);  // Llamada recursiva para la siguiente fila
            }
        }
    }

    // Método para verificar si una posición está ocupada
    static boolean isOccupied(int[] posiciones, int fila, int col) {
        for (int i = 0; i < fila; i++) {
            if (posiciones[i] == col) {  // Verificar columna
                return true;
            }
        }
        return false;
    }

    // Método para imprimir la configuración en el tablero
    static void imprimirConfiguracion(List<Integer> configuracion) {
        char[][] tablero = new char[N][N];

        // Inicializar el tablero
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tablero[i][j] = '.';  // Puntos para posiciones vacías
            }
        }

        // Colocar escritorios (E) en las posiciones dadas
        for (int fila = 0; fila < N; fila++) {
            int col = configuracion.get(fila);
            tablero[fila][col] = 'E';  // E para escritorio
        }

        // Imprimir el tablero
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();  // Línea en blanco entre configuraciones
    }
}
