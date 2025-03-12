package Service;

import DAO.ProductoDAO;
import DTO.ProductoDTO;
import Modelos.Producto;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoService {

    private ProductoDAO productoDAO;

    public ProductoService() {
        this.productoDAO = new ProductoDAO();
    }

    public boolean registrarProducto(ProductoDTO productoDTO) {
        try {
            return productoDAO.insertarProducto(productoDTO);
        } catch (SQLException e) {
            return false;
        }
    }

    public ProductoDTO obtenerProductoPorNombre(String nombre) {
        Producto producto = productoDAO.buscarPor(nombre);
        return (producto != null) ? new ProductoDTO(producto.getNombre(), producto.getPrecio(), producto.getStock()) : null;
    }

    public List<ProductoDTO> obtenerProductosPaginados(int pagina) {
        return productoDAO.obtenerProductosPaginados(pagina).stream()
                .map(p -> new ProductoDTO(p.getNombre(), p.getPrecio(), p.getStock()))
                .collect(Collectors.toList());
    }

    public boolean actualizarProducto(String nombreOriginal, ProductoDTO productoActualizado) {
        // Buscar el producto original por nombre
        Producto productoOriginal = productoDAO.buscarPor(nombreOriginal);
        if (productoOriginal == null) {
            return false; // El producto no existe
        }

        // Actualizar los datos del producto, preservando el id
        productoOriginal.setNombre(productoActualizado.getNombre());
        productoOriginal.setPrecio(productoActualizado.getPrecio());
        productoOriginal.setStock(productoActualizado.getStock());

        // Guardar los cambios en la base de datos usando el id
        return productoDAO.actualizarProducto(productoOriginal);
    }

    public boolean eliminarProducto(String nombre) {
        return productoDAO.eliminarProducto(nombre);
    }

    public List<ProductoDTO> filtrarProductos(String nombre, Double precioMaximo, Integer stockMaximo, int pagina) {
        return productoDAO.filtrarProductos(nombre, precioMaximo, stockMaximo, pagina).stream()
                .map(p -> new ProductoDTO(p.getNombre(), p.getPrecio(), p.getStock()))
                .collect(Collectors.toList());
    }
}
