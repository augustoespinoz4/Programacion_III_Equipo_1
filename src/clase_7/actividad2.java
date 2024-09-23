package clase_7;

import java.util.Scanner;

public class actividad2 {
    final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        actividad2 floyd = new actividad2();
        int[][] graph = {
                {0, 2, INF, 5},
                {INF, 0, INF, 4},
                {INF, INF, 0, INF},
                {INF, INF, 2, INF}
        };
        int V = graph.length;
        floyd.floydWarshall(graph, V);

        // Ejemplo de cómo obtener el camino más corto entre dos nodos
        System.out.println("Inserte un nodo de inicio: ");
        Scanner entrada = new Scanner(System.in);
        int desde = entrada.nextInt();
        System.out.println("Inserte un nodo hasta el que quiera llegar: ");
        entrada = new Scanner(System.in);
        int hasta = entrada.nextInt();

        floyd.imprimirCamino(desde, hasta, graph);
    }

    void floydWarshall(int[][] grafo, int V) {
        int[][] dist = new int[V][V];
        int[][] predecesor = new int[V][V];

        // Inicializar la matriz de distancias y predecesores
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = grafo[i][j];
                predecesor[i][j] = (grafo[i][j] != INF && i != j) ? i : -1;
            }
        }

        // Aplicar el algoritmo de Floyd-Warshall
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        predecesor[i][j] = predecesor[k][j]; // Actualizar predecesor
                    }
                }
            }
        }

        imprimir(dist, V);
        this.predecesor = predecesor; // Guardar predecesores en un atributo de clase
    }

    int[][] predecesor; // Para almacenar los predecesores

    void imprimir(int[][] dist, int V) {
        System.out.println("Matriz de distancias más cortas entre cada par de vértices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "   ");
            }
            System.out.println();
        }
    }

    void imprimirCamino(int start, int end, int[][]graph) {
        System.out.print("Camino más corto desde " + start + " hasta " + end + ": ");
        if (predecesor[start][end] == -1) {
            System.out.println("No hay camino.");
            return;
        }

        StringBuilder camino = new StringBuilder();
        int suma = 0;
        int at = end;

        // Reconstruir el camino
        while (at != start) {
            if (at == -1) {
                System.out.println("No hay camino.");
                return;
            }
            camino.insert(0, at + " -> ");
            int prev = predecesor[start][at];
            suma += (prev != -1) ? graph[prev][at] : 0; // Sumar el peso de la arista
            at = prev;
        }
        camino.insert(0, start + " -> "); // Agregar el nodo de inicio
        System.out.println(camino);
        System.out.println("Suma de los valores del camino: " + suma);
    }
}
