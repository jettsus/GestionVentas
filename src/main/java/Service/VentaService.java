package Service;

import DAO.VentaDAO;
import DTO.VentaDTO;
import java.util.List;

public class VentaService {
    private final VentaDAO ventaDAO;

    public VentaService() {
        this.ventaDAO = new VentaDAO();
    }

    public List<VentaDTO> obtenerVentasFiltradas(int pagina, String filtroVendedor, String filtroProducto, Double filtroPrecioMax) {
        return ventaDAO.obtenerVentasFiltradas(pagina, filtroVendedor, filtroProducto, filtroPrecioMax);
    }

    public void agregarVenta(int usuarioId, int productoId, double total, String fecha, int cantidad) {
        ventaDAO.agregarVenta(usuarioId, productoId, total, fecha, cantidad);
    }

    public void actualizarVenta(int id, int usuarioId, int productoId, double total, String fecha, int cantidad, int oldProductoId) {
        ventaDAO.actualizarVenta(id, usuarioId, productoId, total, fecha, cantidad, oldProductoId);
    }

    public void eliminarVenta(int id) {
        ventaDAO.eliminarVenta(id);
    }

    public List<String> obtenerVendedores() {
        return ventaDAO.obtenerVendedores();
    }

    public List<String> obtenerProductos() {
        return ventaDAO.obtenerProductos();
    }

    public int getUsuarioIdPorNombre(String nombre) {
        return ventaDAO.getUsuarioIdPorNombre(nombre);
    }

    public int getProductoIdPorNombre(String nombre) {
        return ventaDAO.getProductoIdPorNombre(nombre);
    }

    public double getPrecioProductoPorNombre(String nombre) {
        return ventaDAO.getPrecioProductoPorNombre(nombre);
    }
    
    public int obtenerStockProducto(int productoId) {
        return ventaDAO.obtenerStockProducto(productoId);
    }
}