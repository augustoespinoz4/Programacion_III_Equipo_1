package clase_6.Actividad_3;

import java.util.*;

public class SeleccionOptimaProyectosGreedy {

    // Complejidad Temporal: O(n log n) (para ordenar) + O(n) (para seleccionar)

    static class Proyecto {
        int costo;
        int beneficio;
        double ratio;

        Proyecto(int costo, int beneficio) {
            this.costo = costo;
            this.beneficio = beneficio;
            this.ratio = (double) beneficio / costo;
        }
    }

    public static void main(String[] args) {
        int[] costos = {10, 15, 20, 25};
        int[] beneficios = {100, 200, 150, 300};
        int presupuesto = 50;

        List<Proyecto> proyectos = new ArrayList<>();
        for (int i = 0; i < costos.length; i++) {
            proyectos.add(new Proyecto(costos[i], beneficios[i]));
        }

        // Ordenar proyectos por la relación beneficio/costo en orden descendente
        proyectos.sort((p1, p2) -> Double.compare(p2.ratio, p1.ratio));

        int costoTotal = 0;
        int beneficioTotal = 0;
        List<Integer> proyectosSeleccionados = new ArrayList<>();

        for (Proyecto proyecto : proyectos) {
            if (costoTotal + proyecto.costo <= presupuesto) {
                costoTotal += proyecto.costo;
                beneficioTotal += proyecto.beneficio;
                proyectosSeleccionados.add(proyecto.beneficio);
            }
        }

        System.out.println("Beneficio máximo (Greedy): " + beneficioTotal);
        System.out.println("Proyectos seleccionados (Greedy): " + proyectosSeleccionados);
    }
}
