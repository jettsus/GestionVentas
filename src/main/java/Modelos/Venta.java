package Modelos;

import java.time.LocalDateTime;

public class Venta {
    private int id;
    private LocalDateTime fecha; 
    private double total;
    private int vendedorId; 
    private int productoId; 

    // Default constructor
    public Venta() {
    }

    // Full constructor
    public Venta(int id, LocalDateTime fecha, double total, int vendedorId, int productoId) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.vendedorId = vendedorId;
        this.productoId = productoId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", total=" + total +
                ", vendedorId=" + vendedorId +
                ", productoId=" + productoId +
                '}';
    }
}