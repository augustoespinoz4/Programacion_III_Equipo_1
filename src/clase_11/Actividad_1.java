package clase_11;

import java.util.*;

public class Actividad_1 {
    // Mapa para representar la lista de adyacencia
    static Map<Integer, List<Integer>> grafo = new HashMap<>();

    public static void main(String[] args) {
        // Inicializar el grafo
        inicializarGrafo();

        // Conjunto para llevar el registro de nodos visitados
        Set<Integer> visitados = new HashSet<>();

        // Realizar el recorrido DFS a partir del nodo 0
        System.out.print("Recorrido DFS desde el nodo 0: ");
        dfs(0, visitados);
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

    // Método para realizar el recorrido DFS
    static void dfs(int nodo, Set<Integer> visitados) {
        // Marcar el nodo como visitado
        visitados.add(nodo);
        System.out.print(nodo + " ");

        // Recorrer los nodos adyacentes
        for (int vecino : grafo.getOrDefault(nodo, new ArrayList<>())) {
            if (!visitados.contains(vecino)) {
                dfs(vecino, visitados);
            }
        }
    }
}

