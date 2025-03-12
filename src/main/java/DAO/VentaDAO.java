
package DAO;

import DTO.VentaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VentaDAO {

    public void actualizarVenta(int id, int usuarioId, int productoId, double total, String fecha, int cantidad, int oldProductoId) {
        String query = "UPDATE ventas SET usuario_id = ?, producto_id = ?, total = ?, fecha = ?, cantidad = ? WHERE id = ?";
        Connection conn = null;
        try {
            conn = ConexionBDD.conectar();
            conn.setAutoCommit(false); // Iniciar transacción

            // Actualizar la venta
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, usuarioId);
                pstmt.setInt(2, productoId);
                pstmt.setDouble(3, total);
                pstmt.setString(4, fecha);
                pstmt.setInt(5, cantidad);
                pstmt.setInt(6, id);
                pstmt.executeUpdate();
            }

            // Restaurar stock del producto antiguo y actualizar el nuevo
            actualizarStock(oldProductoId, cantidad, conn); // Restaurar stock del producto anterior
            actualizarStock(productoId, -cantidad, conn);    // Restar stock del nuevo producto

            conn.commit(); // Confirmar transacción
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Revertir transacción en caso de error
                } catch (SQLException ex) {
                    System.err.println("Error al revertir transacción: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
            System.err.println("Error al actualizar venta: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // Restaurar auto-commit
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar conexión: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    private void actualizarStock(int productoId, int cantidad, Connection conn) throws SQLException {
        String query = "UPDATE productos SET stock = stock + ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, cantidad);
            pstmt.setInt(2, productoId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                System.err.println("No se encontró el producto con ID: " + productoId + " para actualizar el stock.");
            }
        }
    }

    // Otros métodos existentes como obtenerStockProducto, obtenerVendedores, etc...
    public int obtenerStockProducto(int productoId) {
        String query = "SELECT stock FROM productos WHERE id = ?";
        try (Connection conn = ConexionBDD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("stock");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener stock de producto: " + e.getMessage());
            e.printStackTrace();
        }
        return 0; // Valor por defecto si no se encuentra el producto o hay un error
    }

    public List<VentaDTO> obtenerVentasFiltradas(int pagina, String filtroVendedor, String filtroProducto, Double filtroPrecioMax) {
        List<VentaDTO> ventas = new ArrayList<>();
        StringBuilder query = new StringBuilder(
            "SELECT v.id, u.nombre AS nombre_vendedor, p.nombre AS nombre_producto, v.fecha, v.total, v.cantidad " +
            "FROM ventas v " +
            "JOIN usuarios u ON v.usuario_id = u.id " +
            "JOIN productos p ON v.producto_id = p.id " +
            "WHERE 1=1"
        );

        List<Object> parameters = new ArrayList<>();

        if (filtroVendedor != null && !filtroVendedor.trim().isEmpty()) {
            query.append(" AND u.nombre LIKE ?");
            parameters.add("%" + filtroVendedor + "%");
        }

        if (filtroProducto != null && !filtroProducto.trim().isEmpty()) {
            query.append(" AND p.nombre LIKE ?");
            parameters.add("%" + filtroProducto + "%");
        }

        if (filtroPrecioMax != null) {
            query.append(" AND v.total <= ?");
            parameters.add(filtroPrecioMax);
        }

        query.append(" ORDER BY v.id ASC LIMIT 10 OFFSET ?");
        parameters.add(pagina * 10);

        System.out.println("SQL Query: " + query.toString());
        System.out.println("Parameters: " + parameters);

        try (Connection conn = ConexionBDD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(query.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    VentaDTO venta = new VentaDTO(
                        rs.getInt("id"),
                        rs.getString("nombre_vendedor"),
                        rs.getString("nombre_producto"),
                        rs.getString("fecha"),
                        rs.getDouble("total"),
                        rs.getInt("cantidad") // Añadido campo cantidad
                    );
                    ventas.add(venta);
                }
            }
            System.out.println("Ventas filtradas obtenidas: " + ventas.size());
        } catch (SQLException e) {
            System.err.println("Error al obtener ventas filtradas: " + e.getMessage());
            e.printStackTrace();
        }
        return ventas;
    }

    public void agregarVenta(int usuarioId, int productoId, double total, String fecha, int cantidad) {
        String query = "INSERT INTO ventas (usuario_id, producto_id, total, fecha, cantidad) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBDD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, usuarioId);
            pstmt.setInt(2, productoId);
            pstmt.setDouble(3, total);
            pstmt.setString(4, fecha);
            pstmt.setInt(5, cantidad);
            pstmt.executeUpdate();

            // Actualizar stock (restar cantidad)
            actualizarStock(productoId, -cantidad, conn);
        } catch (SQLException e) {
            System.err.println("Error al agregar venta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    

    public void eliminarVenta(int id) {
        String query = "SELECT producto_id, cantidad FROM ventas WHERE id = ?";
        try (Connection conn = ConexionBDD.conectar();
             PreparedStatement pstmtSelect = conn.prepareStatement(query)) {
            pstmtSelect.setInt(1, id);
            try (ResultSet rs = pstmtSelect.executeQuery()) {
                if (rs.next()) {
                    int productoId = rs.getInt("producto_id");
                    int cantidad = rs.getInt("cantidad");

                    // Eliminar la venta
                    String deleteQuery = "DELETE FROM ventas WHERE id = ?";
                    try (PreparedStatement pstmtDelete = conn.prepareStatement(deleteQuery)) {
                        pstmtDelete.setInt(1, id);
                        pstmtDelete.executeUpdate();
                    }

                    // Restaurar stock (sumar cantidad)
                    actualizarStock(productoId, cantidad, conn);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar venta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    

    public List<String> obtenerVendedores() {
        List<String> vendedores = new ArrayList<>();
        String query = "SELECT nombre FROM usuarios WHERE tipo_usuario = 'vendedor'"; // Filtramos por rol
        try (Connection conn = ConexionBDD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                vendedores.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener vendedores: " + e.getMessage());
            e.printStackTrace();
        }
        return vendedores;
    }

    public List<String> obtenerProductos() {
        List<String> productos = new ArrayList<>();
        String query = "SELECT nombre FROM productos";
        try (Connection conn = ConexionBDD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                productos.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener productos: " + e.getMessage());
            e.printStackTrace();
        }
        return productos;
    }

    public int getUsuarioIdPorNombre(String nombre) {
        String query = "SELECT id FROM usuarios WHERE nombre = ?";
        try (Connection conn = ConexionBDD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nombre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ID de usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public int getProductoIdPorNombre(String nombre) {
        String query = "SELECT id FROM productos WHERE nombre = ?";
        try (Connection conn = ConexionBDD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nombre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ID de producto: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public double getPrecioProductoPorNombre(String nombre) {
        String query = "SELECT precio FROM productos WHERE nombre = ?";
        try (Connection conn = ConexionBDD.conectar();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nombre);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("precio");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener precio de producto: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }
    
    
    
}
