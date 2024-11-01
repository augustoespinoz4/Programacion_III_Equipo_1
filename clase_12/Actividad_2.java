package clase_12;

import java.util.*;

class Destino {
    String nombre;

    public Destino(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}

class Vuelo {
    Destino destino;
    int costo;

    public Vuelo(Destino destino, int costo) {
        this.destino = destino;
        this.costo = costo;
    }

    public Destino getDestino() {
        return destino;
    }

    public int getCosto() {
        return costo;
    }
}

class SistemaViajes {
    private Map<Destino, List<Vuelo>> grafo = new HashMap<>();

    // Método para agregar un destino
    public void agregarDestino(Destino destino) {
        grafo.putIfAbsent(destino, new ArrayList<>());
    }

    // Método para agregar un vuelo entre dos destinos con un costo
    public void agregarVuelo(Destino origen, Destino destino, int costo) {
        grafo.get(origen).add(new Vuelo(destino, costo));
    }

    // Método para encontrar el camino más barato utilizando Dijkstra
    public List<Destino> caminoMasBarato(Destino origen, Destino destino) {
        Map<Destino, Integer> costos = new HashMap<>();
        Map<Destino, Destino> predecesores = new HashMap<>();
        PriorityQueue<Destino> cola = new PriorityQueue<>(Comparator.comparingInt(costos::get));

        for (Destino d : grafo.keySet()) {
            costos.put(d, Integer.MAX_VALUE);
        }
        costos.put(origen, 0);
        cola.add(origen);

        while (!cola.isEmpty()) {
            Destino actual = cola.poll();
            if (actual.equals(destino)) break;

            for (Vuelo vuelo : grafo.get(actual)) {
                Destino vecino = vuelo.getDestino();
                int nuevoCosto = costos.get(actual) + vuelo.getCosto();

                if (nuevoCosto < costos.get(vecino)) {
                    costos.put(vecino, nuevoCosto);
                    predecesores.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }

        return reconstruirCamino(predecesores, origen, destino);
    }

    // Método para reconstruir el camino usando el mapa de predecesores
    private List<Destino> reconstruirCamino(Map<Destino, Destino> predecesores, Destino origen, Destino destino) {
        List<Destino> camino = new LinkedList<>();
        Destino paso = destino;

        if (predecesores.get(paso) == null && !paso.equals(origen)) {
            System.out.println("No hay camino disponible");
            return camino;
        }

        while (paso != null) {
            camino.add(0, paso);
            paso = predecesores.get(paso);
        }
        return camino;
    }
}

public class Actividad_2 {
    public static void main(String[] args) {
        // Crear sistema de viajes
        SistemaViajes sistema = new SistemaViajes();

        // Crear destinos
        Destino a = new Destino("A");
        Destino b = new Destino("B");
        Destino c = new Destino("C");
        Destino d = new Destino("D");
        Destino e = new Destino("E");

        // Agregar destinos al sistema
        sistema.agregarDestino(a);
        sistema.agregarDestino(b);
        sistema.agregarDestino(c);
        sistema.agregarDestino(d);
        sistema.agregarDestino(e);

        // Agregar vuelos con costos
        sistema.agregarVuelo(a, b, 100);
        sistema.agregarVuelo(a, c, 300);
        sistema.agregarVuelo(b, d, 200);
        sistema.agregarVuelo(c, d, 100);
        sistema.agregarVuelo(d, e, 50);
        sistema.agregarVuelo(b, e, 500);

        // Buscar el camino más barato de A a E
        List<Destino> camino = sistema.caminoMasBarato(a, e);

        // Mostrar resultado
        System.out.println("Camino más barato de A a E:");
        for (Destino destino : camino) {
            System.out.print(destino + " ");
        }
    }
}
