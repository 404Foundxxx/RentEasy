package com.renteasy.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gmart
 */
// Clase para manejar la conexión a la base de datos usando el patrón Singleton
public class ConexionBD {

    private static Connection conexion = null;

    public static Connection obtenerConexion() throws FileNotFoundException, SQLException {
        if (conexion != null && !conexion.isClosed()) {
            return conexion;
        } else {
            try {
                // Obtener la ruta del directorio actual
                String directorioActual = System.getProperty("user.dir");

                // Construir la ruta completa del archivo
                String rutaArchivo = directorioActual + File.separator + "src\\main\\java\\com\\renteasy\\utils\\configuracion.txt";
                // Abrir el archivo
                File archivo = new File(rutaArchivo);
                FileReader lector = new FileReader(archivo);
                String url;
                String usuario;
                String contraseña;

                // Leer el contenido del archivo y almacenarlo en variables
                BufferedReader buffer;
                buffer = new BufferedReader(lector);
                // Leer el contenido del archivo y almacenarlo en variables
                url = buffer.readLine();
                usuario = buffer.readLine();
                contraseña = buffer.readLine();
                // Cerrar el buffer de lectura
                buffer.close();
                conexion = DriverManager.getConnection(url, usuario, contraseña);
                System.out.println("Se a conectado la base de datos con exito!");
                return conexion;

            } catch (IOException e) {
                System.out.println("Mensaje (conexion):" + e.getMessage());
                return null;
            }
        }
    }

    public static void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
    }
}
