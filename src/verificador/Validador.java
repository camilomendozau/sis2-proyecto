/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificador;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;//clase para convertir de xml a DOM
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
/**
 *
 * @author Rover
 */
public class Validador {
    
    protected boolean esExtensionValida(File diagrama) {
        String nombre_archivo = diagrama.getName().toLowerCase();
        return nombre_archivo.endsWith(".xmi");
    }
    protected boolean esFormatoValido(File diagrama){
        try {
            DocumentBuilderFactory instancia = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = instancia.newDocumentBuilder();
            Document doc = constructor.parse(diagrama);
            doc.getDocumentElement().normalize();
            if(!doc.getDocumentElement().getNodeName().contains("uml"))
                return false;
        } catch(Exception e) {
            return false;
        }
        return true;
    }
}
