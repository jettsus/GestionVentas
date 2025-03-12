/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author JETTSUSHD
 */
public class DetalleVentaDTO {
    private int venta_id;
    private int producto_id;
    private int cantidad;
    private double subtotal;

    public DetalleVentaDTO() {
    }

    public DetalleVentaDTO(int venta_id, int producto_id, int cantidad, double subtotal) {
        this.venta_id = venta_id;
        this.producto_id = producto_id;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
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
                ", venta_id=" + venta_id +
                ", producto_id=" + producto_id +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }
}
