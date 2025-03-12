package DAO;

import Modelos.Usuario;
import DTO.UsuarioDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    // Insertar usuario con DTO (sin ID)
    public boolean insertarUsuario(UsuarioDTO usuarioDTO) {
        String sql = "INSERT INTO usuarios (nombre, correo, contraseña, tipo_usuario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuarioDTO.getNombre());
            pstmt.setString(2, usuarioDTO.getCorreo());
            pstmt.setString(3, usuarioDTO.getContraseña());
            pstmt.setString(4, usuarioDTO.getTipoUsuario());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Buscar usuario por correo (para obtener ID antes de actualizar)
    public Usuario buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM usuarios WHERE correo = ?";

        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("contraseña"),
                            rs.getString("tipo_usuario")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Actualizar usuario (busca por correo y luego modifica)
    public boolean actualizarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = buscarPorCorreo(usuarioDTO.getCorreo());
        if (usuario == null) {
            return false;
        }

        String sql = "UPDATE usuarios SET nombre = ?, contraseña = ?, tipo_usuario = ? WHERE id = ?";
        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, usuarioDTO.getNombre());
            pstmt.setString(2, usuarioDTO.getContraseña());
            pstmt.setString(3, usuarioDTO.getTipoUsuario());
            pstmt.setInt(4, usuario.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para verificar si el correo ya existe
    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";
        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Autenticación de usuario
    public boolean autenticarUsuario(String correo, String contraseña) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND contraseña = ?";
        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, correo);
            pstmt.setString(2, contraseña);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Obtener usuarios paginados
    public List<Usuario> obtenerPaginado(int pagina) {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios ORDER BY id ASC LIMIT 10 OFFSET ?";

        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pagina * 10);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("contraseña"),
                            rs.getString("tipo_usuario")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    // Eliminar usuario por ID
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Obtener usuario por ID
    public Usuario encontrarUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("contraseña"),
                            rs.getString("tipo_usuario")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Método para obtener usuarios filtrados
    public List<Usuario> obtenerFiltrado(int pagina, String nombre, String tipoUsuario) {
        List<Usuario> lista = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM usuarios WHERE 1=1 ");
        List<Object> parametros = new ArrayList<>();

        if (nombre != null && !nombre.trim().isEmpty()) {
            query.append("AND nombre LIKE ? ");
            parametros.add("%" + nombre.trim() + "%");
        }
        if (tipoUsuario != null && !tipoUsuario.trim().isEmpty()) {
            query.append("AND tipo_usuario = ? ");
            parametros.add(tipoUsuario.trim());
        }

        query.append("ORDER BY id ASC LIMIT 10 OFFSET ?");
        parametros.add(pagina * 10);

        try (Connection conn = ConexionBDD.conectar(); PreparedStatement pstmt = conn.prepareStatement(query.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                pstmt.setObject(i + 1, parametros.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Usuario(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("correo"),
                            rs.getString("contraseña"),
                            rs.getString("tipo_usuario")
                    ));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener usuarios filtrados: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
    
    
}
