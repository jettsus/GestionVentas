package DAO;

import DTO.ProductoDTO;
import Modelos.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // Insertar producto con DTO
    public boolean insertarProducto(ProductoDTO productoDTO) throws SQLException {
        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, productoDTO.getNombre());
            pstmt.setDouble(2, productoDTO.getPrecio());
            pstmt.setInt(3, productoDTO.getStock());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, stock = ? WHERE id = ?";
        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            pstmt.setDouble(2, producto.getPrecio());
            pstmt.setInt(3, producto.getStock());
            pstmt.setInt(4, producto.getId()); 

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Buscar producto por nombre
    public Producto buscarPor(String nombre) {
        String sql = "SELECT * FROM productos WHERE nombre = ?";
        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("stock")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Obtener un producto por ID
    public Producto obtenerProductoPorId(int id) {
        String sql = "SELECT * FROM productos WHERE id = ?";
        Producto producto = null;

        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("stock")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    // Obtener todos los productos con paginación
    public List<Producto> obtenerProductosPaginados(int pagina) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos ORDER BY id ASC LIMIT 10 OFFSET ?";

        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pagina * 10);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("stock")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    // Eliminar un producto por ID
    public boolean eliminarProducto(String nombre) {
        String sql = "DELETE FROM productos WHERE nombre = ?";
        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para filtrar productos con paginación
    public List<Producto> filtrarProductos(String nombre, Double precioMaximo, Integer stockMaximo, int pagina) {
        List<Producto> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM productos WHERE 1=1");

        if (nombre != null && !nombre.isEmpty()) {
            sql.append(" AND nombre LIKE ?");
        }
        if (precioMaximo != null) {
            sql.append(" AND precio <= ?");
        }
        if (stockMaximo != null) {
            sql.append(" AND stock <= ?");
        }
        sql.append(" ORDER BY id ASC LIMIT 10 OFFSET ?");

        try (Connection conn = ConexionBDD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (nombre != null && !nombre.isEmpty()) {
                pstmt.setString(paramIndex++, "%" + nombre + "%");
            }
            if (precioMaximo != null) {
                pstmt.setDouble(paramIndex++, precioMaximo);
            }
            if (stockMaximo != null) {
                pstmt.setInt(paramIndex++, stockMaximo);
            }
            pstmt.setInt(paramIndex, pagina * 10);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Producto(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("stock")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

}
