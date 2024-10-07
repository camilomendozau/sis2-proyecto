 
package verificador;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class verificador_corregido {
    // Método para validar la extensión del archivo
    public boolean esExtensionValida(File archivo) {
        String nombreArchivo = archivo.getName().toLowerCase();
        return nombreArchivo.endsWith(".mdj") || 
           nombreArchivo.endsWith(".xmi") || 
           nombreArchivo.endsWith(".uml") || 
       nombreArchivo.endsWith(".txt") || 
           nombreArchivo.endsWith(".xml");
    }

    // Método para validar el formato del archivo UML
    public boolean esFormatoValido(File archivo) {
        try {
            // Leer el contenido del archivo
            String contenido = new String(Files.readAllBytes(Paths.get(archivo.getAbsolutePath())));
            // Verificar si el contenido comienza con un @startuml
            return contenido.trim().startsWith("@startuml");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return false;
        }
    }
}

