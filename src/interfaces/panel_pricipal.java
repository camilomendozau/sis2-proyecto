/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import metodos_uml.uml_metodos;
import javax.swing.JOptionPane;
import java.io.IOException;
import interfaces.ResizableImagePanel;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import verificador.RecuperarArchivo;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
/**
 *
 * @author HP
 */
public class panel_pricipal extends javax.swing.JFrame {

    /**
     * Creates new form panel_pricipal
     */ 
    ResizableImagePanel panelDiagramaR;
    private File umlFile;
    RecuperarArchivo cargarArbol = new RecuperarArchivo();
    
    public panel_pricipal() {
        initComponents();
        panelDiagramaR = new ResizableImagePanel(""); // Aquí se carga tu imagen UML
        panelDiagramaR.setPreferredSize(new Dimension(300, 300)); // Tamaño inicial
        getContentPane().add(panelDiagramaR, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 280, 360)); // Ajusta la posición y tamaño inicial
        //panelDiagrama.add(panelDiagramaR);
        this.setLocationRelativeTo(null);  // Centrar la ventana
        this.setResizable(false);  // Evitar maximización
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        panelCodigo = new javax.swing.JPanel();
        panelDiagrama = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("GENERADOR DE CODIGO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 350, 50));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("NombreProyecto");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Diagramas");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Codigos");
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jTree1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 150, 370));
        getContentPane().add(panelCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 50, 270, 360));
        getContentPane().add(panelDiagrama, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 390, 450));

        jButton1.setText("IDENTIFICAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 440, -1, -1));

        jButton2.setText("SALIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 440, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Fondo3.1.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 530));

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Crear proyecto");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Abrir proyecto");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Importar modelo");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Nuevo_proyecto n = new Nuevo_proyecto();
        n.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        try {
            // Abrimos el archivo UML que el usuario eligió
            umlFile = uml_metodos.cargarArchivoUML(this); 

            if (umlFile != null) {
                // Convertimos el UML a una imagen para darle una checadita
                File imageFile = uml_metodos.convertirUMLAImagen(umlFile);

                if (imageFile != null) {
                    // Mostramos la vista previa y si le da "Sí", seguimos adelante
                    int opcion = uml_metodos.mostrarVistaPrevia(umlFile, imageFile, this);

                    if (opcion == JOptionPane.YES_OPTION) {
                        // Si le gustó la vista previa, pues la ponemos en el panel
                        uml_metodos.mostrarImagenEnPanel(imageFile, panelDiagramaR);

                        // Limpiamos el panel viejo y le metemos el nuevo con la imagen
                        panelDiagrama.removeAll();
                        panelDiagrama.add(panelDiagramaR);
                        panelDiagrama.revalidate();
                        panelDiagrama.repaint();

                        // Metemos el archivo UML al árbol bajo el nodo "Diagramas"
                        agregarArchivoUML(umlFile.getName());
                    } else {
                        // Si dijo que no, pues nada
                        JOptionPane.showMessageDialog(this, "La imagen no fue seleccionada para mostrar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    // Si no se pudo hacer la imagen, mostramos el error
                    JOptionPane.showMessageDialog(this, "No se pudo generar la imagen del archivo UML.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException ex) {
            // Hubo un problema al procesar el archivo
            JOptionPane.showMessageDialog(this, "Ocurrió un error al procesar el archivo UML: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }       
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    
    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTree1ValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (umlFile != null) {
            try {
            // Llamar al método que identifica el tipo de diagrama UML
                uml_metodos.identificarTipoDeDiagrama(this, umlFile);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al identificar el diagrama: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se ha cargado ningún archivo UML.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        inicio_login i = new inicio_login();
        i.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JFileChooser elegirCarpeta = new JFileChooser();
        elegirCarpeta.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultado = elegirCarpeta.showOpenDialog(this);//elegimos la carpeta
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File ruta = elegirCarpeta.getSelectedFile();
            jTree1.setModel(cargarArbol.cargarModelo(ruta));//cargamos el modelo del arbol
            jTree1.addTreeSelectionListener(new TreeSelectionListener() {
                @Override
                public void valueChanged(TreeSelectionEvent e) {
                    DefaultMutableTreeNode nodo_seleccionado = 
                        (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
                    
                    if (nodo_seleccionado != null) {
                        File archivo_seleccionado = new File(ruta, nodo_seleccionado.toString());
                        
                        if (archivo_seleccionado.isFile()) {
                            try {
                                uml_metodos.mostrarImagenEnPanel(uml_metodos.convertirUMLAImagen(archivo_seleccionado), panelDiagramaR);
                                panelDiagrama.removeAll();
                                panelDiagrama.add(panelDiagramaR);
                                panelDiagrama.revalidate();
                                panelDiagrama.repaint();
                            } catch (IOException ex) {
                                Logger.getLogger(panel_pricipal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            });
        }    
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    // Método para actualizar el nombre del proyecto en el JTree
    public void actualizarNombreProyecto(String nombreProyecto) {
        DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.setUserObject(nombreProyecto);
        model.nodeChanged(root);
    }
    
    private void agregarArchivoUML(String nombreArchivo) {
        DefaultTreeModel model = (DefaultTreeModel) jTree1.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        DefaultMutableTreeNode diagramasNode = (DefaultMutableTreeNode) root.getChildAt(0); // Nodo "Diagramas"
        
        // Agregar el archivo UML como nuevo nodo
        diagramasNode.add(new DefaultMutableTreeNode(nombreArchivo));
        model.nodeStructureChanged(diagramasNode);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(panel_pricipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(panel_pricipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(panel_pricipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(panel_pricipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new panel_pricipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JPanel panelCodigo;
    private javax.swing.JPanel panelDiagrama;
    // End of variables declaration//GEN-END:variables
}
