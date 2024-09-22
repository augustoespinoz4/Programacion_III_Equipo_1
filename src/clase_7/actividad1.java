package clase_7;

public class actividad1 {
    final static int INF = 1000000000;

    public static void main(String[] args) {
        actividad1 floyd = new actividad1();
        int graph[][] = {
                {0, 2, INF, 5},
                {INF, 0, INF, 4},
                {INF, INF, 0, INF},
                {INF, INF, 2, INF}
        };
        int V = graph.length;
        floyd.floydWarshall(graph, V);
    }





    void floydWarshall(int[][] grafo, int V) {
        int[][] dist = crearGrafo(grafo, V);
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        imprimir(dist, V);
    }
    int[][] crearGrafo(int[][] grafo,int v ){
        int[][] dist = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                dist[i][j] = grafo[i][j];
            }
        }
        return dist;
    }

    void imprimir(int dist[][], int V) {
        System.out.println("Matriz de distancias más cortas entre cada par de vértices:");
        for (int i = 0; i < V; i++) {
            System.out.println("A partir del vertice " + (i+1));
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i][j] + "   ");
            }
            System.out.println();
        }
    }
}