package clase_11;

import java.util.*;

public class Actividad_2 {
    // Mapa para representar la lista de adyacencia
    static Map<Integer, List<Integer>> grafo = new HashMap<>();

    public static void main(String[] args) {
        // Inicializar el grafo
        inicializarGrafo();

        // Realizar el recorrido BFS a partir del nodo 0
        System.out.print("Recorrido BFS desde el nodo 0: ");
        bfs(0);
    }

    // Método para inicializar el grafo
    static void inicializarGrafo() {
        // Agregar aristas al grafo
        agregarArista(0, 1);
        agregarArista(1, 3);
        agregarArista(1, 4);
        agregarArista(0, 2);
        agregarArista(2, 5);
        agregarArista(3, 6);
        agregarArista(4, 7);
        agregarArista(4, 8);
    }

    // Método para agregar una arista al grafo
    static void agregarArista(int origen, int destino) {
        grafo.putIfAbsent(origen, new ArrayList<>());
        grafo.putIfAbsent(destino, new ArrayList<>());
        grafo.get(origen).add(destino);
        grafo.get(destino).add(origen); // Grafo no dirigido
    }

    // Método para realizar el recorrido BFS
    static void bfs(int nodoInicial) {
        // Conjunto para llevar el registro de nodos visitados
        Set<Integer> visitados = new HashSet<>();
        // Cola para los nodos a visitar
        Queue<Integer> cola = new LinkedList<>();

        // Añadir el nodo inicial a la cola y marcarlo como visitado
        cola.add(nodoInicial);
        visitados.add(nodoInicial);

        while (!cola.isEmpty()) {
            // Sacar el nodo de la cola
            int nodoActual = cola.poll();
            System.out.print(nodoActual + " ");

            // Recorrer los nodos adyacentes
            for (int vecino : grafo.getOrDefault(nodoActual, new ArrayList<>())) {
                if (!visitados.contains(vecino)) {
                    // Marcar el nodo adyacente como visitado y añadirlo a la cola
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }
    }
}

