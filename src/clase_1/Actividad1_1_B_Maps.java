package clase_1;

import clase_1.clases.SistemaFacturacionMaps;

public class Actividad1_1_B_Maps {
    public static void main (String[] args) {
        SistemaFacturacionMaps sistema = new SistemaFacturacionMaps();

        // Agregar clientes
        sistema.agregarCliente(1, "Augusto");
        sistema.agregarCliente(2, "Nicolás");
        sistema.agregarCliente(3, "Marco");
        sistema.agregarCliente(4, "Thom");

        // Agregar comprobantes
        sistema.agregarComprobante(1, 1, 1400);
        sistema.agregarComprobante(2, 1, 600);
        sistema.agregarComprobante(3, 3, 450);
        sistema.agregarComprobante(4, 2, 1000);
        sistema.agregarComprobante(5, 2, 2200);

        // Obtener comprobantes
        sistema.imprimirResultados();
    }
}
