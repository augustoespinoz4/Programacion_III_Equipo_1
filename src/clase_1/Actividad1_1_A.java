package clase_1;

public class Actividad1_1_A {
    public static void main (String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        // Obtener el máximo
        int maximo = obtenerMaximo(array);
        System.out.println("El mayor valor del array es: " + maximo);
    }

    public static int obtenerMaximo(int[] array) { // O(n)
        int maximo = array[0]; // 1
        for (int i = 1; i < array.length; i++) { // 1 + (n+1) + (n-1) = 2n + 1
            if (array[i] > maximo) { // 1n
                maximo = array[i]; // 1n
            }
        }
        return maximo; // 1
        // numero total de operaciones:
        //                            1 + 2n + 1 + 1n + 1n + 1 =  4n + 3
    }
}


