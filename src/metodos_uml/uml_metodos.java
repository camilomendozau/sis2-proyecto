
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
import java.nio.file.Files;

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
    public static void mostrarImagenEnPanel(File imageFile, JPanel panelDiagrama) {
        // Crear un ImageIcon a partir del archivo
        ImageIcon originalImage = new ImageIcon(imageFile.getAbsolutePath());

        // Escalar la imagen al tamaño del JPanel
        Image scaledImage = originalImage.getImage().getScaledInstance(panelDiagrama.getWidth(), panelDiagrama.getHeight(), Image.SCALE_SMOOTH);

        // Crear un nuevo ImageIcon a partir de la imagen escalada
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        // Crear un JLabel que contenga la imagen
        JLabel labelImagen = new JLabel(scaledImageIcon);

        // Eliminar cualquier componente previo en el panel
        panelDiagrama.removeAll();

        // Añadir el JLabel al panel
        panelDiagrama.add(labelImagen);

        // Actualizar el panel para mostrar la nueva imagen
        panelDiagrama.revalidate();
        panelDiagrama.repaint();
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
    public static void identificarTipoDeDiagrama(JFrame parentFrame,File umlFile) throws IOException {
        // Leer el contenido del archivo UML
        List<String> lineasUML = Files.readAllLines(umlFile.toPath());
        
        // Criterios para identificar tipos de diagramas
        boolean esClase = lineasUML.stream().anyMatch(linea -> linea.contains("class"));
        boolean esSecuencia = lineasUML.stream().anyMatch(linea -> linea.contains("participant"));
        boolean esActividad = lineasUML.stream().anyMatch(linea -> linea.contains("activity"));
        boolean esCasosDeUso = lineasUML.stream().anyMatch(linea -> linea.contains("usecase") || linea.contains("actor"));
        boolean esEstado = lineasUML.stream().anyMatch(linea -> linea.contains("state") || linea.contains("initial") || linea.contains("final"));
        boolean esPaquete = lineasUML.stream().anyMatch(linea -> linea.contains("package"));
        
        // Identificar el tipo de diagrama basado en las líneas analizadas
        if (esClase) {
            JOptionPane.showMessageDialog(parentFrame, "El tipo del diagrama es: DIAGRAMA DE CLASES", "Identificacion exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else if (esSecuencia) {
                JOptionPane.showMessageDialog(parentFrame, "El tipo del diagrama es: DIAGRAMA DE SECUENCIA", "Identificacion exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else if (esActividad) {
                JOptionPane.showMessageDialog(parentFrame, "El tipo del diagrama es: DIAGRAMA DE ACTIVIDADES", "Identificacion exitosa", JOptionPane.INFORMATION_MESSAGE);
            } else if(esCasosDeUso){
                JOptionPane.showMessageDialog(parentFrame, "El tipo del diagrama es: DIAGRAMA DE CASOS DE USO", "Identificacion exitosa", JOptionPane.INFORMATION_MESSAGE);
            }else if(esPaquete){
                JOptionPane.showMessageDialog(parentFrame, "El tipo del diagrama es: DIAGRAMA DE PAQUETES", "Identificacion exitosa", JOptionPane.INFORMATION_MESSAGE);
            }else if(esEstado){
                JOptionPane.showMessageDialog(parentFrame, "El tipo del diagrama es: DIAGRAMA DE ESTADOS", "Identificacion exitosa", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(parentFrame, "No se puede indentificar el diagrama.", "Error", JOptionPane.WARNING_MESSAGE);
            }
         }
}


