package clase_2;

class MergeSort {

    // Método principal que ordena el arreglo
    public static void mergeSort(int[] arreglo) {
        if (arreglo.length < 2) {
            return; // Si el arreglo tiene 0 o 1 elementos, ya está ordenado
        }
        int medio = arreglo.length / 2;

        // Dividir el arreglo en dos mitades
        int[] izquierda = new int[medio];
        int[] derecha = new int[arreglo.length - medio];

        // Copiar los elementos a las mitades
        for (int i = 0; i < medio; i++) {
            izquierda[i] = arreglo[i];
        }
        for (int i = medio; i < arreglo.length; i++) {
            derecha[i - medio] = arreglo[i];
        }

        // Llamadas recursivas para ordenar las mitades
        mergeSort(izquierda);
        mergeSort(derecha);

        // Mezclar las mitades ordenadas
        merge(arreglo, izquierda, derecha);
    }

    // Método para mezclar dos mitades ordenadas
    private static void merge(int[] arreglo, int[] izquierda, int[] derecha) {
        int i = 0, j = 0, k = 0;

        // Mezclar los elementos en orden
        while (i < izquierda.length && j < derecha.length) {
            if (izquierda[i] <= derecha[j]) {
                arreglo[k++] = izquierda[i++];
            } else {
                arreglo[k++] = derecha[j++];
            }
        }

        // Copiar los elementos restantes de la mitad izquierda
        while (i < izquierda.length) {
            arreglo[k++] = izquierda[i++];
        }

        // Copiar los elementos restantes de la mitad derecha
        while (j < derecha.length) {
            arreglo[k++] = derecha[j++];
        }
    }

    public static void imprimirVector(int[] vector) {
        StringBuilder resultado = new StringBuilder("[");
        for (int i = 0; i < vector.length; i++) {
            resultado.append(vector[i]);
            if (i < vector.length - 1) {
                resultado.append(", ");
            }
        }
        resultado.append("]");
        System.out.println(resultado);
    }
}

public class Actividad_4 {
    public static void main (String [] args) {
        int[] vector = {14, 21, 7, 8, 10, 3, 24, 12, 1, 19};

        System.out.println("Vector Original: ");
        MergeSort.imprimirVector(vector);

        MergeSort.mergeSort(vector);

        System.out.println("\nVector Ordenado: ");
        MergeSort.imprimirVector(vector);
    }
}
