package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionBDD {
    private static final String URL = "jdbc:sqlite:src/main/resources/database/ventas.db";

    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(URL);
            System.out.println("✅ Conexión establecida con SQLite.");
            return conexion;
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar con SQLite.");
            e.printStackTrace();
            return null;
        }
    }

    


    // Método para cerrar la conexión
    public static void cerrarConexion(Connection conexion) {
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("🔌 Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al cerrar la conexión.");
            e.printStackTrace();
        }
    }
    
    public static void cerrarPreparedStatement(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
                System.out.println("PreparedStatement cerrado correctamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar PreparedStatement: " + e);
            }
        } else {
            System.out.println("No hay PreparedStatement abierto para cerrar");
        }
    }

    public static void cerrarResulset(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                System.out.println("Resulset cerrado correctamente");
            } catch (SQLException e) {
                System.err.println("Error al cerrar Resulset: " + e);
            }
        } else {
            System.out.println("No hay Resulset abierto para cerrar");
        }
    }
}
