package clase_5.api;

import java.util.List;

public interface GrafoTDA {
    // Método para agregar una arista entre dos vértices
    void agregarArista(int origen, int destino);
    
    // Método para eliminar una arista entre dos vértices
    void eliminarArista(int origen, int destino);
    
    // Método para verificar si existe una arista entre dos vértices
    boolean verificarArista(int origen, int destino);
    
    // Método para listar los vértices adyacentes a un vértice dado
    List<Integer> listarAdyacentes(int vertice);
    
    // Método para contar el grado de salida de un vértice dado
    int contarGradoSalida(int vertice);
    
    // Método para contar el grado de entrada de un vértice dado
    int contarGradoEntrada(int vertice);
    
    // Método para mostrar la matriz de adyacencia (puede ser útil para la depuración)
    void mostrarMatriz();
}
