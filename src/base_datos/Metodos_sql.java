package base_datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Metodos_sql {
    
    private Connection conexion;
    private PreparedStatement sentenciaPreparada;
    private ResultSet resultado;
    
    public boolean buscarUsuarioRepetidoBD(String usuario) {
        boolean usuarioRepetido = false;
        try {
            conexion = ConexionBD.conectar();
            
            // Verificar si el usuario ya existe en la base de datos
            String consulta = "SELECT * FROM usuarios WHERE usuario = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, usuario);
            
            resultado = sentenciaPreparada.executeQuery();

            if (resultado.next()) {
                // El usuario está registrado en la BD
                usuarioRepetido = true; 
            } else {
                // El usuario no está registrado en la BD
                usuarioRepetido = false; 
            }

            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }

        System.out.println("Usuario repetido: " + usuarioRepetido);
        return usuarioRepetido;
    }
    
    public boolean buscarUsuarioInicioSesion(String usuario, String contrasena) {
        boolean iniciarSesion = false;

        try {
            conexion = ConexionBD.conectar();
            
            // Consulta para verificar las credenciales de inicio de sesión
            String consulta = "SELECT * FROM usuarios WHERE usuario = ? AND contra = ?";
            sentenciaPreparada = conexion.prepareStatement(consulta);
            sentenciaPreparada.setString(1, usuario);
            sentenciaPreparada.setString(2, contrasena); 
            
            resultado = sentenciaPreparada.executeQuery();
            
            if (resultado.next()) {
                // El usuario y la contraseña coinciden
                System.out.println("Usuario encontrado. Iniciando sesión.");
                iniciarSesion = true;
            } else {
                // Usuario o contraseña incorrectos
                System.out.println("Usuario o contraseña incorrectos.");
                iniciarSesion = false;
            }
            
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e);
            }
        }

        System.out.println("¿Puede iniciar sesión? " + iniciarSesion);
        return iniciarSesion;
    }
}
