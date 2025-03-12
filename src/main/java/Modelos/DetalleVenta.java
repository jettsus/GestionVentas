package Modelos;

public class DetalleVenta {
    private int id;
    private int venta_id;
    private int producto_id;
    private int cantidad;
    private double subtotal;

    public DetalleVenta() {
    }

    public DetalleVenta(int id, int venta_id, int producto_id, int cantidad, double subtotal) {
        this.id = id;
        this.venta_id = venta_id;
        this.producto_id = producto_id;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVenta_id() {
        return venta_id;
    }

    public void setVenta_id(int venta_id) {
        this.venta_id = venta_id;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "id=" + id +
                ", venta_id=" + venta_id +
                ", producto_id=" + producto_id +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }
}
