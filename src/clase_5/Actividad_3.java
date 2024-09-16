package clase_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Actividad_3 {

    // Método para crear e inicializar el grafo
    private static List<List<int[]>> crearGrafo(int numVertices) {
        List<List<int[]>> grafo = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            grafo.add(new ArrayList<>());
        }

        agregarArista(grafo, 0, 1, 10);
        agregarArista(grafo, 0, 2, 6);
        agregarArista(grafo, 0, 3, 5);
        agregarArista(grafo, 1, 3, 15);
        agregarArista(grafo, 2, 3, 4);

        return grafo;
    }

    // Método auxiliar para agregar una arista al grafo
    private static void agregarArista(List<List<int[]>> grafo, int u, int v, int peso) {
        grafo.get(u).add(new int[] {v, peso});
        grafo.get(v).add(new int[] {u, peso});
    }

    public static void main(String[] args) {
        int numVertices = 4;  // Número de vértices en el grafo
        List<List<int[]>> grafo = crearGrafo(numVertices);
        
        System.out.println("Árbol de Recubrimiento Mínimo (MST):");
        AlgoritmoPrim.primMST(numVertices, grafo);
    }
}

class AlgoritmoPrim {
    private static final int INF = Integer.MAX_VALUE;

    public static void primMST(int numVertices, List<List<int[]>> grafo) {
        int[] clave = new int[numVertices];  // Array para almacenar el peso mínimo de la arista a incluir en el MST
        int[] padre = new int[numVertices];  // Array para almacenar el MST resultante
        boolean[] enMST = new boolean[numVertices];  // Array para rastrear los vértices incluidos en el MST

        Arrays.fill(clave, INF);  // Inicializar los valores de las claves como infinitos
        clave[0] = 0;  // Comenzar desde el primer vértice
        padre[0] = -1;  // El primer nodo siempre es la raíz del MST

        for (int conteo = 0; conteo < numVertices - 1; conteo++) {
            int u = minClave(numVertices, clave, enMST);  // Elegir el vértice con la clave mínima que aún no está incluido en el MST
            enMST[u] = true;  // Incluir u en el MST

            // Actualizar la clave y el índice del padre de los vértices adyacentes al vértice elegido
            for (int[] vecino : grafo.get(u)) {
                int v = vecino[0];
                int peso = vecino[1];

                // Actualizar la clave solo si v no está en el MST y el peso de (u, v) es menor que la clave actual de v
                if (!enMST[v] && peso < clave[v]) {
                    clave[v] = peso;
                    padre[v] = u;
                }
            }
        }

        imprimirMST(padre, numVertices, grafo);
    }

    private static int minClave(int numVertices, int[] clave, boolean[] enMST) {
        int min = INF, minIndice = -1;

        for (int v = 0; v < numVertices; v++) {
            if (!enMST[v] && clave[v] < min) {
                min = clave[v];
                minIndice = v;
            }
        }

        return minIndice;
    }

    private static void imprimirMST(int[] padre, int numVertices, List<List<int[]>> grafo) {
        System.out.println("Arista \tPeso");
        int costoTotal = 0;
        for (int i = 1; i < numVertices; i++) {
            for (int[] vecino : grafo.get(i)) {
                if (vecino[0] == padre[i]) {
                    System.out.println(padre[i] + " - " + i + "\t" + vecino[1]);
                    costoTotal += vecino[1];
                }
            }
        }
        System.out.println("Costo Total del MST: " + costoTotal);
    }
}
