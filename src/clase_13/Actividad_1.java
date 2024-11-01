package clase_13;

public class Actividad_1 {
    /*
    ¿Cómo funciona el backtracking en este problema?
    El backtracking en este problema se utiliza para colocar una reina en cada fila del tablero.
    Si se coloca una reina de forma segura en una posición, el algoritmo procede a la siguiente fila;
    si no puede encontrar una posición segura, "retrocede" a la fila anterior para intentar otra colocación.

    ¿Qué pasa cuando el algoritmo encuentra una solución?
    Cuando encuentra una solución (es decir, todas las reinas están colocadas en el tablero sin amenazarse),
    el algoritmo retorna `true` y se imprime el estado del tablero, mostrando la ubicación de cada reina.

    ¿Qué ocurre cuando no puede colocar más reinas?
    Si no puede colocar una reina en ninguna posición de una fila dada, significa que la configuración actual no es válida,
    por lo que retrocede a la fila anterior para probar otras posiciones posibles. Si agota todas las opciones posibles
    y no encuentra una solución, concluye que no hay ninguna para el tablero.

    ¿Qué sucede en el código cuando el algoritmo "retrocede"? ¿Cómo se visualiza en Python Tutor?
    Cuando el algoritmo retrocede, descoloca la última reina colocada, volviendo el valor de la posición de `1` a `0`.
    En Python Tutor, esto se vería como un paso atrás, visualizando que el algoritmo deshace su último movimiento y prueba una nueva opción.

    ¿Qué modificaciones harías para aumentar N a 8? ¿Cómo crees que cambiaría el tiempo de ejecución?
    Para aumentar el valor de `N` a 8, simplemente cambiaría `private static final int N = 4;` por `private static final int N = 8;`.
    El tiempo de ejecución aumentaría considerablemente porque el número de combinaciones crece exponencialmente con el tamaño del tablero.

    ¿Por qué el método isSafe es crucial en este algoritmo?
    El método `isSafe` es crucial porque garantiza que ninguna reina esté en amenaza antes de colocar otra.
    Comprueba la columna y las diagonales para cada posición, evitando configuraciones incorrectas y reduciendo la cantidad de combinaciones a explorar.
    */

    private static final int N = 8;

    public static void main(String[] args) {
        int[][] board = new int[N][N];
        if (solve(board, 0)) {
            printBoard(board);
        } else {
            System.out.println("No hay solución.");
        }
    }

    public static boolean solve(int[][] board, int row) {
        if (row == N) { // Si todas las reinas están colocadas
            return true;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;  // Coloca la reina

                if (solve(board, row + 1)) {  // Recurre para colocar el resto
                    return true;
                }

                board[row][col] = 0;  // Si falla, descoloca la reina (backtracking)
            }
        }

        return false;  // No hay solución posible
    }

    public static boolean isSafe(int[][] board, int row, int col) {
        // Verificar columna
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        // Verificar diagonal superior izquierda
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Verificar diagonal superior derecha
        for (int i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
