package clase_4;

import java.util.Arrays;

public class Actividad_3 {
    static class Mercancia {
        private String nombre;
        private double peso;
        private double valor;

        public Mercancia(String nombre, double peso, double valor) {
            this.nombre = nombre;
            this.peso = peso;
            this.valor = valor;
        }

        public double getPeso() {
            return peso;
        }

        public double getValor() {
            return valor;
        }

        public double valorPorPeso() {
            return valor / peso;
        }

        @Override
        public String toString() {
            return nombre + " (peso: " + peso + ", valor: " + valor + ")";
        }
    }

    public static void maximizarValorCargado(double capacidadCamion, Mercancia[] mercancias) {
        // Ordenar las mercancías por valor/peso en orden descendente
        Arrays.sort(mercancias, (a, b) -> Double.compare(b.valorPorPeso(), a.valorPorPeso())); // O(n log n)

        double valorTotal = 0.0; // 1
        double pesoRestante = capacidadCamion; // 1

        System.out.println("Elementos a cargar en el camión:"); // 1

        // Iterar sobre las mercancías para maximizar el valor
        for (Mercancia mercancia : mercancias) { // n
            if (pesoRestante == 0) { // 1
                break; // 1
            }

            // Si la mercancía cabe completa, añadirla
            if (mercancia.getPeso() <= pesoRestante) { // 1
                pesoRestante -= mercancia.getPeso(); // 1
                valorTotal += mercancia.getValor(); // 1
                System.out.println(mercancia); // 1
            } else {
                // Si no cabe completa, añadir solo una fracción
                double fraccion = pesoRestante / mercancia.getPeso(); // 1
                valorTotal += mercancia.getValor() * fraccion; // 1
                System.out.println(mercancia.nombre + " (fraccionada: " + fraccion * 100 + "%)"); // 1
                pesoRestante = 0; // 1
            }
        }

        // Imprimir el valor total cargado
        System.out.println("Valor total cargado en el camión: " + valorTotal); // 1
    }


    public static void main(String[] args) {
        Mercancia[] mercancias = {
                new Mercancia("Mercancía 1", 10, 60),
                new Mercancia("Mercancía 2", 20, 100),
                new Mercancia("Mercancía 3", 30, 120),
                new Mercancia("Mercancía 4", 15, 90)
        };

        double capacidadCamion = 50;

        maximizarValorCargado(capacidadCamion, mercancias);
    }
}
