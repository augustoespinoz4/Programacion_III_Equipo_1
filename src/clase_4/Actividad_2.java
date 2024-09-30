package clase_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Actividad_2 {
    static class Comprobante {
        private String tipo;
        private int valor;

        public Comprobante(String tipo, int valor) {
            this.tipo = tipo;
            this.valor = valor;
        }

        public int getValor() {
            return valor;
        }

        @Override
        public String toString() {
            return tipo + " (" + valor + ")";
        }
    }

    public static List<Comprobante> minimizarComprobantes(int cantidad, Comprobante[] comprobantes) {
        // Ordenar los comprobantes en orden descendente de valor
        Arrays.sort(comprobantes, (a, b) -> b.getValor() - a.getValor()); // O(n log n)

        // Lista para almacenar los comprobantes utilizados
        List<Comprobante> resultado = new ArrayList<>(); // 1

        // Iterar sobre los comprobantes en orden descendente
        for (Comprobante comprobante : comprobantes) { // n
            // Usar el comprobante mientras sea posible
            while (cantidad >= comprobante.getValor()) { // n
                cantidad -= comprobante.getValor(); // 1
                resultado.add(comprobante); // 1
            }

            // Si la cantidad se reduce a 0, hemos terminado
            if (cantidad == 0) { // 1
                break; // 1
            }
        }

        // Retornar la lista de comprobantes usados
        return resultado; // 1
    }



    public static void main(String[] args) {
        Comprobante[] comprobantes = {
                new Comprobante("Moneda de 100", 100),
                new Comprobante("Cheque de 50", 50),
                new Comprobante("Bono de 20", 20),
                new Comprobante("Moneda de 5", 5),
                new Comprobante("Moneda de 1", 1)
        };

        int cantidad = 276;

        List<Comprobante> comprobantesUsados = minimizarComprobantes(cantidad, comprobantes);

        System.out.println("Comprobantes utilizados para alcanzar la cantidad de 276:");
        for (Comprobante comprobante : comprobantesUsados) {
            System.out.println(comprobante);
        }
    }
}
