package clase_1.clases;

public class ResultadoDto {
    private int idCliente;
    private double totalImporteFacturas;

    public ResultadoDto(int idCliente, double totalImporteFacturas) {
        this.idCliente = idCliente;
        this.totalImporteFacturas = totalImporteFacturas;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public double getTotalImporteFacturas() {
        return totalImporteFacturas;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setTotalImporteFacturas(double totalImporteFacturas) {
        this.totalImporteFacturas = totalImporteFacturas;
    }

    @Override
    public String toString() {
        return "ID Cliente: " + idCliente
                + "\nTotal: " + totalImporteFacturas;
    }
}
