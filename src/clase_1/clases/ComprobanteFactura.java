package clase_1.clases;

public class ComprobanteFactura {
    private int idFactura;
    private int idCliente;
    private double importe;

    public ComprobanteFactura(int idFactura, int idCliente, double importe) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.importe = importe;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public double getImporte() {
        return importe;
    }
}
