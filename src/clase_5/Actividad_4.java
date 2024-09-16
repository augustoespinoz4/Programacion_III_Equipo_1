package clase_5;

import java.util.*;

public class Actividad_4 {

    // Clase para representar una arista en el grafo
    static class Arista {
        int destino;
        int peso;

        Arista(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    // Clase para representar el grafo
    static class Grafo {
        int vertices;
        List<List<Arista>> listaAdyacencia;

        Grafo(int vertices) {
            this.vertices = vertices;
            listaAdyacencia = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                listaAdyacencia.add(new ArrayList<>());
            }
        }

        // Método para agregar una arista al grafo
        void agregarArista(int origen, int destino, int peso) {
            listaAdyacencia.get(origen).add(new Arista(destino, peso));
        }

        // Método para aplicar el algoritmo de Dijkstra
        void dijkstra(int verticeInicio) {
            int[] distancias = new int[vertices];
            Arrays.fill(distancias, Integer.MAX_VALUE);
            distancias[verticeInicio] = 0;

            PriorityQueue<Arista> colaPrioridad = new PriorityQueue<>(vertices, Comparator.comparingInt(a -> a.peso));
            colaPrioridad.add(new Arista(verticeInicio, 0));

            boolean[] visitado = new boolean[vertices];

            while (!colaPrioridad.isEmpty()) {
                int u = colaPrioridad.poll().destino;

                if (visitado[u]) continue;
                visitado[u] = true;

                for (Arista arista : listaAdyacencia.get(u)) {
                    int v = arista.destino;
                    int peso = arista.peso;

                    if (!visitado[v] && distancias[u] + peso < distancias[v]) {
                        distancias[v] = distancias[u] + peso;
                        colaPrioridad.add(new Arista(v, distancias[v]));
                    }
                }
            }

            imprimirSolucion(distancias, verticeInicio);
        }

        // Método para imprimir las distancias desde el vértice de inicio
        void imprimirSolucion(int[] distancias, int verticeInicio) {
            System.out.println("Distancia desde el centro de distribución " + verticeInicio);
            for (int i = 0; i < vertices; i++) {
                if (distancias[i] == Integer.MAX_VALUE) {
                    System.out.println("Hasta el centro " + i + " es inalcanzable");
                } else {
                    System.out.println("Hasta el centro " + i + " es " + distancias[i] + " horas");
                }
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 6;  // Número de centros de distribución
        Grafo grafo = new Grafo(vertices);
        
        // Añadir carreteras (aristas) al grafo
        grafo.agregarArista(0, 1, 4);  // Desde el centro 0 al centro 1 con 4 horas de viaje
        grafo.agregarArista(0, 2, 1);  // Desde el centro 0 al centro 2 con 1 horas de viaje
        grafo.agregarArista(2, 1, 2);  // Desde el centro 2 al centro 1 con 2 horas de viaje
        grafo.agregarArista(1, 3, 1);  // Desde el centro 1 al centro 3 con 1 horas de viaje
        grafo.agregarArista(2, 3, 5);  // Desde el centro 2 al centro 3 con 5 horas de viaje
        grafo.agregarArista(3, 4, 3);  // Desde el centro 3 al centro 4 con 3 horas de viaje
        grafo.agregarArista(4, 5, 1);  // Desde el centro 4 al centro 5 con 1 horas de viaje
        grafo.agregarArista(3, 5, 2);  // Desde el centro 3 al centro 5 con 2 horas de viaje

        grafo.dijkstra(0);  // Ejecutar Dijkstra desde el centro de distribución principal (0)
    }
}
