
package base_datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    // Datos de conexión
    private static String url = "jdbc:mysql://localhost:3306/proyecto_sisinf2";
    private static String usuario = "root";
    private static String contrasena = "";

    public static Connection conectar() {
        Connection conexion = null;
        
        try {
            // Cargar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión establecida");
        } catch (ClassNotFoundException e) {
            //System.out.println("Error:no encontrado");
            e.printStackTrace();
        } catch (SQLException e) {
            //System.out.println("Error alestablecer la conex " + e.getMessage());
            e.printStackTrace();
        }
        
        return conexion;
    }
    public static void main(String[] args) {
        // Llamamos al método conectar para probar la conexión
        Connection conexion = ConexionBD.conectar();
        
        if (conexion != null) {
            System.out.println("La conexión fue exitosa.");
        } else {
            System.out.println("La conexión falló.");
        }
    }

}

