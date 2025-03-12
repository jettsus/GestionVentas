package Controller;

import DTO.VentaDTO;
import Service.VentaService;
import java.util.List;

public class VentaController {
    private final VentaService ventaService;

    public VentaController() {
        this.ventaService = new VentaService();
    }

    public List<VentaDTO> obtenerVentasFiltradas(int pagina, String filtroVendedor, String filtroProducto, Double filtroPrecioMax) {
        return ventaService.obtenerVentasFiltradas(pagina, filtroVendedor, filtroProducto, filtroPrecioMax);
    }

    public void agregarVenta(int usuarioId, int productoId, double total, String fecha, int cantidad) {
        ventaService.agregarVenta(usuarioId, productoId, total, fecha, cantidad);
    }

    public void actualizarVenta(int id, int usuarioId, int productoId, double total, String fecha, int cantidad, int oldProductoId) {
        ventaService.actualizarVenta(id, usuarioId, productoId, total, fecha, cantidad, oldProductoId);
    }

    public void eliminarVenta(int id) {
        ventaService.eliminarVenta(id);
    }

    public List<String> obtenerVendedores() {
        return ventaService.obtenerVendedores();
    }

    public List<String> obtenerProductos() {
        return ventaService.obtenerProductos();
    }

    public int getUsuarioIdPorNombre(String nombre) {
        return ventaService.getUsuarioIdPorNombre(nombre);
    }

    public int getProductoIdPorNombre(String nombre) {
        return ventaService.getProductoIdPorNombre(nombre);
    }

    public double getPrecioProductoPorNombre(String nombre) {
        return ventaService.getPrecioProductoPorNombre(nombre);
    }
    
    public int obtenerStockProducto(int productoId) {
        return ventaService.obtenerStockProducto(productoId);
    }
}