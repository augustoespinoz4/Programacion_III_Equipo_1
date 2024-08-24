package clase_1.clases;

import java.util.ArrayList;

public class SistemaFacturacion{

    private ArrayList<Cliente> clientes;
    private ArrayList<ComprobanteFactura> comprobantesFacturas;

    public SistemaFacturacion() {
        clientes = new ArrayList<>();
        comprobantesFacturas = new ArrayList<>();
    }

    public void agregarCliente(int idCliente, String nombre) {
        Cliente cliente = new Cliente(idCliente, nombre);
        clientes.add(cliente);
    }

    public void eliminarCliente(int idCliente) {
        clientes.remove(idCliente);
    }

    public void agregarComprobante(int idFactura, int idCliente, double importe) {
        ComprobanteFactura comprobanteFactura = new ComprobanteFactura(idFactura, idCliente, importe);
        comprobantesFacturas.add(comprobanteFactura);
    }

    public void eliminarComprobante(int idFactura) {
        comprobantesFacturas.remove(idFactura);
    }

    public ArrayList<ResultadoDto> sumaImportesClientes() {
        ArrayList<ResultadoDto> resultado = new ArrayList<>(); // 2

        for (Cliente cliente : this.clientes) { // n
            double totalImportes = 0.0; // 1n
            for (ComprobanteFactura facturas : this.comprobantesFacturas) { // n
                if (cliente.getIdCliente() == facturas.getIdCliente()) { //1n
                    totalImportes += facturas.getImporte(); // 2n
                }
            }
            resultado.add(new ResultadoDto(cliente.getIdCliente(), totalImportes)); // 1n
        }
        return resultado; // 1

        // en clase:
        // f(n) = 2 + n (1n + n + 1n + 2n) + 1n + 1 =
        //        2 + n + n (5n)
        // f(n) = 5n^2 + n + 2

        // no sería???
        // f(n) = 2 + n (1 + n (1 + 2) + 1) + 1
        //        2 + n (1 + n (3) + 1) + 1
        //        2 + n (1 + 3n + 1) + 1
        //        2 + n (2 + 3n) + 1
        //        2 + 3n^2 + 2n + 1
        // f(n) = 3n^2 + 2n + 3
    }
}
