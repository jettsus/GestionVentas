package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class MetodosValidacion {

    // Validar que el correo tenga formato correcto
    public static boolean validarCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) return false;
        return Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.com$", correo);
    }

    // Validar que el precio sea un número positivo
    public static boolean validarPrecio(double precio) {
        return precio >= 0;
    }

    // Validar que un nombre solo contenga letras y espacios
    public static boolean validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) return false;
        return Pattern.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", nombre);
    }

    // Validar que un número entero sea positivo
    public static boolean validarEnteroPositivo(int numero) {
        return numero >= 0;
    }

    // Validar que un stock sea positivo o cero
    public static boolean validarStock(int stock) {
        return stock >= 0;
    }

    // Validar que la fecha sea válida y no futura
    public static boolean validarFecha(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) return false;
        try {
            LocalDate fechaIngresada = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return !fechaIngresada.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Validar que un string no esté vacío o nulo
    public static boolean validarStringNoVacio(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }

    // Validar que una contraseña tenga mínimo 6 caracteres y contenga al menos un número
    public static boolean validarContraseña(String contraseña) {
        if (contraseña == null || contraseña.length() < 6) return false;
        return Pattern.matches("^(?=.*[0-9])[A-Za-z0-9@#$%^&+=]{6,}$", contraseña);
    }
    
    public static boolean validarNombreProductos(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Nombre inválido: null o vacío");
            return false;
        }
        String trimmedNombre = nombre.trim();
        
        String regex = "^[A-Za-zÁÉÍÓÚáéíóúÑñ0-9\" -]+$";
        boolean isValid = Pattern.matches(regex, trimmedNombre);
        if (!isValid) {
            System.out.println("Nombre inválido: '" + trimmedNombre + "' no cumple con el patrón");
        }
        return isValid;
    }
}
