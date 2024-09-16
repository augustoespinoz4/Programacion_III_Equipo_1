package clase_5.implementacion;

import java.util.ArrayList;
import java.util.List;

import clase_5.api.GrafoTDA;

public class GrafoMatriz implements GrafoTDA {
    private int[][] matrizAdyacencia;
    private int numVertices;

    // Constructor: Inicialización del Grafo
    public GrafoMatriz(int numVertices) {
        this.numVertices = numVertices;
        matrizAdyacencia = new int[numVertices][numVertices];
    }

    // Método para agregar una arista
    public void agregarArista(int origen, int destino) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            matrizAdyacencia[origen][destino] = 1; // Asigna un 1 en la posición (origen, destino) para indicar una arista
        } else {
            System.out.println("Vértice fuera de rango");
        }
    }

    // Método para eliminar una arista
    public void eliminarArista(int origen, int destino) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            matrizAdyacencia[origen][destino] = 0; // Asigna un 0 en la posición (origen, destino) para eliminar la arista
        } else {
            System.out.println("Vértice fuera de rango");
        }
    }

    // Método para verificar si existe una arista
    public boolean verificarArista(int origen, int destino) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            return matrizAdyacencia[origen][destino] == 1;
        } else {
            System.out.println("Vértice fuera de rango");
            return false;
        }
    }

    // Método para listar los vértices adyacentes de un vértice dado
    public List<Integer> listarAdyacentes(int vertice) {
        List<Integer> adyacentes = new ArrayList<>();
        if (vertice >= 0 && vertice < numVertices) {
            for (int i = 0; i < numVertices; i++) {
                if (matrizAdyacencia[vertice][i] == 1) {
                    adyacentes.add(i);
                }
            }
        } else {
            System.out.println("Vértice fuera de rango");
        }
        return adyacentes;
    }

    // Método para contar el grado de salida de un vértice dado
    public int contarGradoSalida(int vertice) {
        int gradoSalida = 0;
        if (vertice >= 0 && vertice < numVertices) {
            for (int i = 0; i < numVertices; i++) {
                if (matrizAdyacencia[vertice][i] == 1) {
                    gradoSalida++;
                }
            }
        } else {
            System.out.println("Vértice fuera de rango");
        }
        return gradoSalida;
    }

    // Método para contar el grado de entrada de un vértice dado
    public int contarGradoEntrada(int vertice) {
        int gradoEntrada = 0;
        if (vertice >= 0 && vertice < numVertices) {
            for (int i = 0; i < numVertices; i++) {
                if (matrizAdyacencia[i][vertice] == 1) {
                    gradoEntrada++;
                }
            }
        } else {
            System.out.println("Vértice fuera de rango");
        }
        return gradoEntrada;
    }

    // Método para mostrar la matriz de adyacencia
    public void mostrarMatriz() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                System.out.print(matrizAdyacencia[i][j] + " ");
            }
            System.out.println();
        }
    }
}
