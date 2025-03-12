package Controller;

import DTO.ProductoDTO;
import Service.ProductoService;
import java.util.List;

public class ProductoController {

    private final ProductoService productoService;

    public ProductoController() {
        this.productoService = new ProductoService();
    }

    // Registrar producto
    public String registrarProducto(String nombre, double precio, int stock) {
        if (!MetodosValidacion.validarNombre(nombre)) {
            return "Error: Nombre inválido.";
        }
        if (!MetodosValidacion.validarPrecio(precio)) {
            return "Error: Precio inválido.";
        }
        if (!MetodosValidacion.validarStock(stock)) {
            return "Error: Stock inválido.";
        }

        ProductoDTO productoDTO = new ProductoDTO(nombre, precio, stock);
        boolean registrado = productoService.registrarProducto(productoDTO);

        return registrado ? "Producto registrado correctamente." : "Error: No se pudo registrar el producto.";
    }

    // Obtener producto por nombre
    public ProductoDTO obtenerProductoPorNombre(String nombre) {
        if (!MetodosValidacion.validarNombreProductos(nombre)) { 
            System.out.println("Nombre inválido en obtenerProductoPorNombre: " + nombre); 
            return null;
        }
        ProductoDTO dto = productoService.obtenerProductoPorNombre(nombre);
        return dto; // This now includes the id
    }

    // Obtener productos paginados
    public List<ProductoDTO> obtenerProductos(int pagina) {
        return productoService.obtenerProductosPaginados(pagina);
    }

    // Actualizar producto
    public String actualizarProducto(String nombreOriginal, ProductoDTO productoActualizado) {
    if (!MetodosValidacion.validarNombreProductos(productoActualizado.getNombre())) {
        return "Error: Nombre inválido.";
    }
    if (!MetodosValidacion.validarPrecio(productoActualizado.getPrecio())) {
        return "Error: Precio inválido.";
    }
    if (!MetodosValidacion.validarStock(productoActualizado.getStock())) {
        return "Error: Stock inválido.";
    }

    boolean actualizado = productoService.actualizarProducto(nombreOriginal, productoActualizado);
    return actualizado ? "Producto actualizado correctamente." : "Error: No se pudo actualizar el producto.";
}

    // Eliminar producto
    public String eliminarProducto(String nombre) {
        
        boolean eliminado = productoService.eliminarProducto(nombre);
        return eliminado ? "Producto eliminado correctamente." : "Error: No se pudo eliminar.";
    }

    public List<ProductoDTO> filtrarProductos(String nombre, Double precioMaximo, Integer stockMaximo, int pagina) {
        return productoService.filtrarProductos(nombre, precioMaximo, stockMaximo, pagina);
    }
}
