/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificador;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import verificador.verificador_corregido;
/**
 *
 * @author Rover
 */
public class RecuperarArchivo {
   
    private verificador_corregido verificar = new verificador_corregido();
    
    public DefaultTreeModel cargarModelo(File rootDirectory) {
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(rootDirectory.getName());
        loadFiles(rootDirectory, raiz);
        return new DefaultTreeModel(raiz);
    }
    
    private void loadFiles(File directory, DefaultMutableTreeNode node) {
    File[] files = directory.listFiles(file -> file.isDirectory() || verificar.esExtensionValida(file));
    
    if (files != null) {
        for (File file : files) {
            DefaultMutableTreeNode archivoUml = new DefaultMutableTreeNode(file.getName());
            node.add(archivoUml);
            
            if (file.isDirectory()) {
                loadFiles(file, archivoUml); // Llamada recursiva
            }
        }
    }
}

}
