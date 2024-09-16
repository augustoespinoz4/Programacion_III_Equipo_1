package clase_6.Actividad_4;

import java.util.*;

public class SeleccionPaquetesGreedy {

    // Complejidad Temporal: O(n log n) + O(n)


    static class Paquete {
        int costo;
        int ganancia;
        double ratio;

        Paquete(int costo, int ganancia) {
            this.costo = costo;
            this.ganancia = ganancia;
            this.ratio = (double) ganancia / costo;
        }
    }

    public static void main(String[] args) {
        int[] costos = {12, 20, 15, 25};
        int[] ganancias = {150, 200, 100, 300};
        int presupuesto = 35;

        List<Paquete> paquetes = new ArrayList<>();
        for (int i = 0; i < costos.length; i++) {
            paquetes.add(new Paquete(costos[i], ganancias[i]));
        }

        // Ordenar los paquetes por la relación ganancia/costo de forma descendente
        paquetes.sort((p1, p2) -> Double.compare(p2.ratio, p1.ratio));

        int costoTotal = 0;
        int gananciaTotal = 0;
        List<Integer> seleccionados = new ArrayList<>();

        for (Paquete paquete : paquetes) {
            if (costoTotal + paquete.costo <= presupuesto) {
                costoTotal += paquete.costo;
                gananciaTotal += paquete.ganancia;
                seleccionados.add(paquete.ganancia);
            }
        }

        System.out.println("Ganancia máxima (Greedy): " + gananciaTotal);
        System.out.println("Paquetes seleccionados (Greedy): " + seleccionados);
    }
}
