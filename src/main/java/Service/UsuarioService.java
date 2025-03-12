package Service;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import Modelos.Usuario;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public boolean registrarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioDAO.existeCorreo(usuarioDTO.getCorreo())) {
            return false;
        }
        return usuarioDAO.insertarUsuario(usuarioDTO);
    }

    public boolean autenticarUsuario(String correo, String contraseña) {
        return usuarioDAO.autenticarUsuario(correo, contraseña);
    }

    public List<UsuarioDTO> obtenerUsuariosPaginados(int pagina) {
        return usuarioDAO.obtenerPaginado(pagina).stream()
                .map(u -> new UsuarioDTO(u.getNombre(), u.getCorreo(), u.getContraseña(), u.getTipoUsuario()))
                .collect(Collectors.toList());
    }

    public boolean actualizarUsuario(UsuarioDTO usuarioDTO) {
        return usuarioDAO.actualizarUsuario(usuarioDTO);
    }

    public boolean eliminarUsuario(int id) {
        return usuarioDAO.eliminarUsuario(id);
    }

    public UsuarioDTO encontrarUsuarioPorCorreo(String correo) {
        Usuario usuario = usuarioDAO.buscarPorCorreo(correo);
        return (usuario != null) ? new UsuarioDTO(usuario.getNombre(), usuario.getCorreo(), usuario.getContraseña(), usuario.getTipoUsuario()) : null;
    }

    // Obtener usuarios filtrados
    public List<UsuarioDTO> obtenerUsuariosFiltrados(int pagina, String nombre, String tipoUsuario) {
        return usuarioDAO.obtenerFiltrado(pagina, nombre, tipoUsuario).stream()
                .map(u -> new UsuarioDTO(u.getNombre(), u.getCorreo(), u.getContraseña(), u.getTipoUsuario()))
                .collect(Collectors.toList());
    }
}
