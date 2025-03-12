package DTO;

public class VentaDTO {
    private int id;
    private String nombreVendedor;
    private String nombreProducto;
    private String fecha;
    private double total;
    private int cantidad; // Nueva propiedad para la cantidad

    public VentaDTO(int id, String nombreVendedor, String nombreProducto, String fecha, double total, int cantidad) {
        this.id = id;
        this.nombreVendedor = nombreVendedor;
        this.nombreProducto = nombreProducto;
        this.fecha = fecha;
        this.total = total;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "VentaDTO{" +
                "id=" + id +
                ", nombreVendedor='" + nombreVendedor + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", fecha='" + fecha + '\'' +
                ", total=" + total +
                ", cantidad=" + cantidad +
                '}';
    }
}