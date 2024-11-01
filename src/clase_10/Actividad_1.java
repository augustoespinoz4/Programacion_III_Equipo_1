package clase_10;

public class Actividad_1 {

    static int N = 4;  // Tamaño del tablero 4x4
    static char[][] tablero = new char[N][N];  // Tablero 4x4
    static int soluciones = 0;

    public static void main(String[] args) {
        // Inicializamos el tablero vacío
        inicializarTablero();
        // Iniciamos el backtracking
        colocarReinas(0, 0, 0);
        System.out.println("Total de configuraciones válidas: " + soluciones);
    }

    // Inicializamos el tablero con '.'
    static void inicializarTablero() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tablero[i][j] = '.';
            }
        }
    }

    // Función para colocar las reinas en el tablero
    static void colocarReinas(int fila, int col, int numReinas) {
        // Si ya hemos colocado las dos reinas, imprimimos la configuración
        if (numReinas == 2) {
            imprimirTablero();
            soluciones++;
            return;
        }

        // Recorrer todas las posiciones a partir de la posición actual
        for (int i = fila; i < N; i++) {
            for (int j = (i == fila ? col : 0); j < N; j++) {
                if (esSeguro(i, j)) {
                    tablero[i][j] = 'R';  // Colocamos una reina
                    colocarReinas(i, j + 1, numReinas + 1);  // Intentamos colocar la siguiente reina
                    tablero[i][j] = '.';  // Backtracking: quitamos la reina
                }
            }
        }
    }

    // Función para verificar si una posición es segura
    static boolean esSeguro(int fila, int col) {
        // Revisamos si hay otra reina en la misma columna o diagonal
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tablero[i][j] == 'R') {
                    // Mismas columnas, filas y diagonales
                    if (i == fila || j == col || Math.abs(i - fila) == Math.abs(j - col)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Función para imprimir el tablero
    static void imprimirTablero() {
        System.out.println("Solución " + (soluciones + 1) + ":");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}


