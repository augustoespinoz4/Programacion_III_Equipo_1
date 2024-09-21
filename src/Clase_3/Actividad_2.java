package clase_3;

public class Actividad_2 {
    public static void main(String[] args) {
        int[] numeros = {3, 9, 2, 7, 4, 8, 1, 5};
        int[] resultado = encontrarDosMayores(numeros, 0, numeros.length);

        System.out.println("Primer mayor: " + resultado[0]);
        System.out.println("Segundo mayor: " + resultado[1]);
    }

    private static int[] encontrarDosMayores(int[] numeros, int i, int f) {
        // Caso base: si la sublista tiene un solo elemento, devolvemos dos valores
        // iguales, ya que no tenemos un segundo valor para comparar.
        if (f - i == 1) {
            return new int[] { numeros[i], Integer.MIN_VALUE };
        }

        // Caso base: si la sublista tiene dos elementos, devolvemos ambos.
        if (f - i == 2) {
            int primero = numeros[i];
            int segundo = numeros[i + 1];
            if (primero > segundo) {
                return new int[] { primero, segundo };
            } else {
                return new int[] { segundo, primero };
            }
        }

        // Dividimos la lista a la mitad
        int mitad = (i + f) / 2;
        int[] izq = encontrarDosMayores(numeros, i, mitad);
        int[] der = encontrarDosMayores(numeros, mitad, f);

        // Combinamos los resultados de ambas mitades
        return combinarDosMayores(izq, der);
    }

    private static int[] combinarDosMayores(int[] izq, int[] der) {
        // Creamos un array para mantener los dos mayores valores combinados
        int[] resultado = new int[2];

        // Encontramos el mayor valor de los cuatro candidatos (izq[0], izq[1], der[0], der[1])
        if (izq[0] > der[0]) {
            resultado[0] = izq[0];
            resultado[1] = Math.max(izq[1], der[0]);
        } else {
            resultado[0] = der[0];
            resultado[1] = Math.max(der[1], izq[0]);
        }

        return resultado;
    }
}