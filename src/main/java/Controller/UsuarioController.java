package Controller;

import DTO.UsuarioDTO;
import Service.UsuarioService;
import java.util.List;

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    // Registrar usuario
    public String registrarUsuario(String nombre, String correo, String contraseña, String tipoUsuario) {
        if (!MetodosValidacion.validarNombre(nombre)) {
            return "Error: Nombre inválido. No debe contener números ni caracteres especiales.";
        }
        if (!MetodosValidacion.validarCorreo(correo)) {
            return "Error: Correo inválido. Debe contener @ y terminar en .com.";
        }
        if (!MetodosValidacion.validarContraseña(contraseña)) {
            return "Error: La contraseña debe tener al menos 6 caracteres y un número.";
        }
        if (!MetodosValidacion.validarStringNoVacio(tipoUsuario)) {
            return "Error: El tipo de usuario no puede estar vacío.";
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO(nombre, correo, contraseña, tipoUsuario);
        boolean registrado = usuarioService.registrarUsuario(usuarioDTO);

        return registrado ? "Usuario registrado exitosamente." : "Error: No se pudo registrar el usuario.";
    }

    // Autenticar usuario
    public String autenticarUsuario(String correo, String contraseña) {
        if (correo == null || correo.trim().isEmpty()) {
            return "Error: El correo no puede estar vacío.";
        }
        if (contraseña == null || contraseña.trim().isEmpty()) {
            return "Error: La contraseña no puede estar vacía.";
        }

        // Log inputs for debugging
        System.out.println("Controller - Correo: '" + correo + "', Contraseña: '" + contraseña + "'");

        // Trim inputs to avoid whitespace issues
        correo = correo.trim();
        contraseña = contraseña.trim();

        boolean autenticado = usuarioService.autenticarUsuario(correo, contraseña);
        System.out.println("Controller - Autenticado: " + autenticado);
        return autenticado ? "Inicio de sesión exitoso." : "Error: Credenciales incorrectas.";
    }

    // Obtener usuarios paginados
    public List<UsuarioDTO> obtenerUsuarios(int pagina) {
        return usuarioService.obtenerUsuariosPaginados(pagina);
    }

    // Actualizar usuario (busca por correo)
    public String actualizarUsuario(String correo, String nombre, String contraseña, String tipoUsuario) {
        if (!MetodosValidacion.validarCorreo(correo)) {
            return "Error: Correo inválido.";
        }
        if (!MetodosValidacion.validarNombre(nombre)) {
            return "Error: Nombre inválido.";
        }
        if (!MetodosValidacion.validarContraseña(contraseña)) {
            return "Error: Contraseña inválida.";
        }
        if (!MetodosValidacion.validarStringNoVacio(tipoUsuario)) {
            return "Error: El tipo de usuario no puede estar vacío.";
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO(nombre, correo, contraseña, tipoUsuario);
        boolean actualizado = usuarioService.actualizarUsuario(usuarioDTO);

        return actualizado ? "Usuario actualizado correctamente." : "Error: No se pudo actualizar el usuario.";
    }

    // Eliminar usuario
    public String eliminarUsuario(int id) {
        if (!MetodosValidacion.validarEnteroPositivo(id)) {
            return "Error: ID inválido.";
        }

        boolean eliminado = usuarioService.eliminarUsuario(id);
        return eliminado ? "Usuario eliminado correctamente." : "Error: No se pudo eliminar el usuario.";
    }

    // Obtener usuario por correo
    public UsuarioDTO obtenerUsuarioPorCorreo(String correo) {
        if (!MetodosValidacion.validarCorreo(correo)) {
            return null;
        }
        return usuarioService.encontrarUsuarioPorCorreo(correo);
    }

    // Obtener usuarios filtrados con un objeto Filtro
    public List<UsuarioDTO> obtenerUsuariosFiltrados(int pagina, String nombre, String tipoUsuario) {
        System.out.println("Controller - obtenerUsuariosFiltrados - Pagina: " + pagina + ", Nombre: " + nombre + ", Tipo: " + tipoUsuario);
        if (!MetodosValidacion.validarEnteroPositivo(pagina)) {
            System.out.println("Controller - pagina invalida: " + pagina);
            return null;
        }
        if (nombre != null && !nombre.trim().isEmpty() && !MetodosValidacion.validarNombre(nombre)) {
            System.out.println("Controller - nombre invalido: " + nombre);
            return null;
        }
        if (tipoUsuario != null && !tipoUsuario.trim().isEmpty() && !MetodosValidacion.validarStringNoVacio(tipoUsuario)) {
            System.out.println("Controller - tipousuario invalido: " + tipoUsuario);
            return null;
        }

        List<UsuarioDTO> result = usuarioService.obtenerUsuariosFiltrados(pagina, nombre, tipoUsuario);
        System.out.println("Controller - Resultado de filtro: " + (result != null ? "No null" : "null"));
        return result;
    }
}
