
package metodos_uml;

/**
 *
 * @author JHOCELINE
 *
 */
import java.awt.Image;
import net.sourceforge.plantuml.SourceFileReader;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import net.sourceforge.plantuml.GeneratedImage;
import verificador.verificador_corregido;

public class uml_metodos {
    
    private static verificador_corregido validador = new verificador_corregido();

    
    public static File cargarArchivoUML(JFrame parentFrame) {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(parentFrame);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // pa validar la extion utilizamos el met de la class varificador_corregido
            if (!validador.esExtensionValida(selectedFile)) {
                JOptionPane.showMessageDialog(parentFrame, "Por favor selecciona un archivo UML válido (.uml.xml .mdj .xmi).", "Error de archivo", JOptionPane.ERROR_MESSAGE);
                return null; // No es un archivo valido
            }
            // Validar formato UML
            if (!validador.esFormatoValido(selectedFile)) {
                JOptionPane.showMessageDialog(parentFrame, "El archivo no contiene un formato UML válido.", "Error de archivo", JOptionPane.ERROR_MESSAGE);
                return null; // No valido
            }
            return selectedFile; // Archivo valido
        }
        return null; // no selecciono un archivo
    }

    public static File convertirUMLAImagen(File umlFile) throws IOException {
        // Crear un nuevo archivo .png
        File outputFile = new File(umlFile.getAbsolutePath() + ".png");
        // Usamos la librería PlantUML para convertir el archivo UML a PNG
        SourceFileReader reader = new SourceFileReader(umlFile);
        List<GeneratedImage> images = reader.getGeneratedImages();
        // Verificar si hay imágenes generadas
        if (images.isEmpty()) {
            System.out.println("No se generaron imágenes.");
            return null; // No hay imágenes generadas
        }
        // Suponiendo que hay al menos una imagen generada
        GeneratedImage image = images.get(0); // Primera imagen generada
        File pngFile = image.getPngFile(); // Archivo PNG generado
        // Copiar el archivo PNG al destino usando FileInputStream y FileOutputStream
        try (FileInputStream fis = new FileInputStream(pngFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {
            byte[] buffer = new byte[1024]; // Tamaño del buffer
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length); 
            }
        } catch (IOException e) {
            System.err.println("Error al copiar el archivo: " + e.getMessage());
            return null; 
        }

        // Eliminar el archivo temporal generado por PlantUML
        if (pngFile.exists()) {
            if (pngFile.delete()) {
                System.out.println("Archivo temporal eliminado: " + pngFile.getAbsolutePath());
            } else {
                System.err.println("No se pudo eliminar el archivo temporal: " + pngFile.getAbsolutePath());
            }
        }

        return outputFile; 
    }

    public static void mostrarImagen(File imageFile, JLabel labelImagen) {
        // Crear un ImageIcon a partir del archivo
        ImageIcon originalImage = new ImageIcon(imageFile.getAbsolutePath());

        // Escalar la imagen al tamaño del JLabel
        Image scaledImage = originalImage.getImage().getScaledInstance(labelImagen.getWidth(), labelImagen.getHeight(), Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen escalada
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        // Asignar la imagen escalada al JLabel
        labelImagen.setIcon(scaledImageIcon);
        labelImagen.revalidate();  // Refresca el JLabel para mostrar la imagen
        labelImagen.repaint(); 
    }
    
    public static boolean eliminarArchivoGenerado(File imagenGenerada, JLabel labelImagen) {
        if (imagenGenerada != null && imagenGenerada.exists()) {
            // Limpiar el JLabel
            labelImagen.setIcon(null);
            labelImagen.revalidate();
            labelImagen.repaint();

            // Intentar eliminar el archivo del sistema
            boolean deleted = imagenGenerada.delete();

            if (!deleted) {
                System.err.println("No se pudo eliminar la imagen del archivo: " + imagenGenerada.getAbsolutePath());
            }
            return deleted;
        }
        return false;
    }
    
    // Metodo para seleccionar archivo
    public static File seleccionarArchivoParaEliminar(JFrame parentFrame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo de imagen para eliminar");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes PNG y JPG", "png", "jpg"));
        
        int option = fileChooser.showOpenDialog(parentFrame);
        if (option == JFileChooser.APPROVE_OPTION) {
                return fileChooser.getSelectedFile();
            }
            return null; // Si el usuario cancela
        }
    
    // Metodo para eliminar el archivo seleccionado
    public static void eliminarArchivo(JFrame parentFrame, File archivoAEliminar, JLabel labelImagen) {
        if (archivoAEliminar != null) {
            if (eliminarArchivoGenerado(archivoAEliminar, labelImagen)) {
                JOptionPane.showMessageDialog(parentFrame, "Imagen eliminada correctamente.", "Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(parentFrame, "No se pudo eliminar la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(parentFrame, "No se seleccionó ningún archivo.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}


