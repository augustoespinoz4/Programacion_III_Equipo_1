package clase_1.clases;

import java.util.ArrayList;
import java.util.HashMap;

public class SistemaFacturacionMaps {

    private HashMap<Integer, Cliente> clientes;
    private ArrayList<ComprobanteFactura> comprobantesFacturas;

    public SistemaFacturacionMaps() {
        clientes = new HashMap<>();
        comprobantesFacturas = new ArrayList<>();
    }

    public void agregarCliente(int idCliente, String nombre) {
        Cliente cliente = new Cliente(idCliente, nombre);
        clientes.put(idCliente, cliente);
    }

    public void eliminarCliente(int idCliente) {
        clientes.remove(idCliente);
    }

    public void agregarComprobante(int idFactura, int idCliente, double importe) {
        if (clientes.containsKey(idCliente)) {
            ComprobanteFactura comprobanteFactura = new ComprobanteFactura(idFactura, idCliente, importe);
            comprobantesFacturas.add(comprobanteFactura);
        }
    }

    public void eliminarComprobante(int idFactura) {
        comprobantesFacturas.removeIf(comprobante -> comprobante.getIdFactura() == idFactura);
    }

    public HashMap<Integer, ResultadoDto> sumaImportesClientes() {
        HashMap<Integer, ResultadoDto> resultados = new HashMap<>(); // 1

        // Inicializa todos los clientes con importe 0
        for (Integer idCliente : clientes.keySet()) { // n
            resultados.put(idCliente, new ResultadoDto(idCliente, 0.0)); // 2
        }

        // Suma los importes de los comprobantes al HashMap
        for (ComprobanteFactura comprobante : this.comprobantesFacturas) { // n
            int idCliente = comprobante.getIdCliente(); // 1
            double importe = comprobante.getImporte(); // 1

            if (resultados.containsKey(idCliente)) { // 1
                ResultadoDto resultadoActual = resultados.get(idCliente); // 1
                resultadoActual.setTotalImporteFacturas(resultadoActual.getTotalImporteFacturas() + importe); // 1
            }
        }

        return resultados; // 1

        // f(n) = 1 + n (2) + n (1 + 1 + 1 + 1 + 1) + 1
        //      = 1 + 2n + n (5) + 1
        //      = 1 + 2n + 5n + 1
        // f(n) = 7n + 2
    }

    public void imprimirResultados() {
        HashMap<Integer, ResultadoDto> resultados = sumaImportesClientes();
        for (Integer id : resultados.keySet()) {
            System.out.println(resultados.get(id) + "\n");
        }
    }
}
