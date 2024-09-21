import java.util.Arrays;

public class actividad_4 {

    // Función principal para encontrar los N números más grandes
    public static int[] encontrarNMayores(int[] arr, int N) {
        if (arr.length <= N) {
            return arr; // Si el array tiene menos o igual cantidad de elementos que N, retorna el mismo array
        }
        return encontrarNMayoresRecursivo(arr, N);
    }

    // Función recursiva para encontrar los N números más grandes
    private static int[] encontrarNMayoresRecursivo(int[] arr, int N) {
        if (arr.length <= N) {
            return arr;
        }

        int mitad = arr.length / 2;
        int[] izquierda = Arrays.copyOfRange(arr, 0, mitad);
        int[] derecha = Arrays.copyOfRange(arr, mitad, arr.length);

        // Encontrar los N números más grandes en ambas mitades
        int[] mayoresIzquierda = encontrarNMayoresRecursivo(izquierda, N);
        int[] mayoresDerecha = encontrarNMayoresRecursivo(derecha, N);

        // Combinar los resultados de las dos mitades
        int[] combinado = new int[mayoresIzquierda.length + mayoresDerecha.length];
        System.arraycopy(mayoresIzquierda, 0, combinado, 0, mayoresIzquierda.length);
        System.arraycopy(mayoresDerecha, 0, combinado, mayoresIzquierda.length, mayoresDerecha.length);

        // Ordenar el array combinado y tomar los N números más grandes
        Arrays.sort(combinado);
        int[] resultado = new int[N];
        System.arraycopy(combinado, combinado.length - N, resultado, 0, N);

        return resultado;
    }

    public static void main(String[] args) {
        int[] arr = {10, 45, 20, 8, 15, 25, 30, 35};
        int N = 4;
        int[] mayores = encontrarNMayores(arr, N);
        System.out.println("Los " + N + " números más grandes son: " + Arrays.toString(mayores));
    }
}
