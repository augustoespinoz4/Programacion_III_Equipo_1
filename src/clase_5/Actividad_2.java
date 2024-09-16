package clase_5;

import clase_5.implementacion.GrafoMatriz;
import clase_5.api.GrafoTDA;

public class Actividad_2 {
    public static void main(String[] args) {
        // Crear un grafo con 5 vértices
        GrafoTDA grafo = new GrafoMatriz(5);

        // Agregar algunas aristas
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(3, 4);

        // Mostrar la matriz de adyacencia
        System.out.println("Matriz de Adyacencia:");
        grafo.mostrarMatriz();

        // Verificar una arista
        System.out.println("\nVerificar arista entre 0 y 1: " + grafo.verificarArista(0, 1));
        System.out.println("Verificar arista entre 1 y 4: " + grafo.verificarArista(1, 4));

        // Listar los vértices adyacentes al vértice 0
        System.out.println("\nVértices adyacentes al vértice 0: " + grafo.listarAdyacentes(0));

        // Contar el grado de salida y entrada del vértice 3
        System.out.println("\nGrado de salida del vértice 3: " + grafo.contarGradoSalida(3));
        System.out.println("Grado de entrada del vértice 3: " + grafo.contarGradoEntrada(3));

        // Eliminar una arista y mostrar la matriz de nuevo
        grafo.eliminarArista(0, 1);
        System.out.println("\nMatriz de Adyacencia después de eliminar la arista (0, 1):");
        grafo.mostrarMatriz();
    }
}
