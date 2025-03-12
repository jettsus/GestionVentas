/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gestionventas;

import DAO.ConexionBDD;
import java.sql.Connection;

/**
 *
 * @author JETTSUSHD
 */
public class GestionVentas {

    public static void main(String[] args) {
Connection conn = ConexionBDD.conectar();
if (conn != null) {
    System.out.println("Conectado a la base de datos.");
    ConexionBDD.cerrarConexion(conn); // Cerrar conexión cuando termines
}
    }
}
