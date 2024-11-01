package clase_11;

import java.util.*;

class Almacen {
    private String id;
    private String nombre;

    public Almacen(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Almacen{id='" + id + "', nombre='" + nombre + "'}";
    }
}

class Grafo {
    Map<Almacen, List<Almacen>> listaAdyacencia;

    public Grafo() {
        listaAdyacencia = new HashMap<>();
    }

    // Método para agregar un almacén
    public void agregarAlmacen(Almacen almacen) {
        listaAdyacencia.putIfAbsent(almacen, new ArrayList<>());
    }

    // Método para conectar dos almacenes
    public void conectarAlmacenes(Almacen origen, Almacen destino) {
        listaAdyacencia.putIfAbsent(origen, new ArrayList<>());
        listaAdyacencia.putIfAbsent(destino, new ArrayList<>());
        listaAdyacencia.get(origen).add(destino);
        listaAdyacencia.get(destino).add(origen); // Grafo no dirigido
    }

    // Método DFS
    public void dfs(Almacen almacenInicio) {
        Set<Almacen> visitados = new HashSet<>();
        System.out.print("Recorrido DFS desde " + almacenInicio + ": ");
        dfsRecursivo(almacenInicio, visitados);
        System.out.println();
    }

    private void dfsRecursivo(Almacen almacen, Set<Almacen> visitados) {
        visitados.add(almacen);
        System.out.print(almacen + " ");
        for (Almacen vecino : listaAdyacencia.getOrDefault(almacen, new ArrayList<>())) {
            if (!visitados.contains(vecino)) {
                dfsRecursivo(vecino, visitados);
            }
        }
    }

    // Método BFS
    public void bfs(Almacen almacenInicio) {
        Set<Almacen> visitados = new HashSet<>();
        Queue<Almacen> cola = new LinkedList<>();
        cola.add(almacenInicio);
        visitados.add(almacenInicio);

        System.out.print("Recorrido BFS desde " + almacenInicio + ": ");
        while (!cola.isEmpty()) {
            Almacen almacenActual = cola.poll();
            System.out.print(almacenActual + " ");

            for (Almacen vecino : listaAdyacencia.getOrDefault(almacenActual, new ArrayList<>())) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                }
            }
        }
        System.out.println();
    }
}

public class Actividad_3 {
    public static void main(String[] args) {
        Grafo redAlmacenes = new Grafo();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nOpciones:");
            System.out.println("1. Agregar Almacen");
            System.out.println("2. Conectar Almacenes");
            System.out.println("3. Realizar recorrido DFS");
            System.out.println("4. Realizar recorrido BFS");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Agregar Almacen
                    System.out.print("Ingrese el ID del almacén: ");
                    String id = scanner.nextLine();
                    System.out.print("Ingrese el nombre del almacén: ");
                    String nombre = scanner.nextLine();
                    Almacen almacen = new Almacen(id, nombre);
                    redAlmacenes.agregarAlmacen(almacen);
                    System.out.println("Almacén agregado: " + almacen);
                    break;

                case 2:
                    // Conectar Almacenes
                    System.out.print("Ingrese el ID del almacén de origen: ");
                    String idOrigen = scanner.nextLine();
                    System.out.print("Ingrese el ID del almacén de destino: ");
                    String idDestino = scanner.nextLine();

                    Almacen origen = buscarAlmacen(redAlmacenes, idOrigen);
                    Almacen destino = buscarAlmacen(redAlmacenes, idDestino);

                    if (origen != null && destino != null) {
                        redAlmacenes.conectarAlmacenes(origen, destino);
                        System.out.println("Conexión establecida entre " + origen + " y " + destino);
                    } else {
                        System.out.println("Almacenes no encontrados. Asegúrese de haberlos agregado antes.");
                    }
                    break;

                case 3:
                    // Realizar recorrido DFS
                    System.out.print("Ingrese el ID del almacén de inicio para DFS: ");
                    String idInicioDFS = scanner.nextLine();
                    Almacen inicioDFS = buscarAlmacen(redAlmacenes, idInicioDFS);
                    if (inicioDFS != null) {
                        redAlmacenes.dfs(inicioDFS);
                    } else {
                        System.out.println("Almacén no encontrado.");
                    }
                    break;

                case 4:
                    // Realizar recorrido BFS
                    System.out.print("Ingrese el ID del almacén de inicio para BFS: ");
                    String idInicioBFS = scanner.nextLine();
                    Almacen inicioBFS = buscarAlmacen(redAlmacenes, idInicioBFS);
                    if (inicioBFS != null) {
                        redAlmacenes.bfs(inicioBFS);
                    } else {
                        System.out.println("Almacén no encontrado.");
                    }
                    break;

                case 5:
                    // Salir
                    continuar = false;
                    System.out.println("Saliendo de la aplicación.");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    }

    private static Almacen buscarAlmacen(Grafo grafo, String id) {
        for (Almacen almacen : grafo.listaAdyacencia.keySet()) {
            if (almacen.getId().equals(id)) {
                return almacen;
            }
        }
        return null;
    }
}

