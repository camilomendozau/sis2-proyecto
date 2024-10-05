/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author HP
 */
public class Nuevo_proyecto extends javax.swing.JFrame {

    /**
     * Creates new form Nuevo_proyecto
     */
    public Nuevo_proyecto() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        nombreProyecto_txt = new javax.swing.JTextField();
        directorio_txt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NUEVO PROYECTO");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, -1, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NOMBRE PROYECTO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("DIRECTORIO");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 160, 30));

        btnCrear.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnCrear.setText("CREAR");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, -1, -1));

        nombreProyecto_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreProyecto_txtActionPerformed(evt);
            }
        });
        getContentPane().add(nombreProyecto_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 260, 30));

        directorio_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                directorio_txtActionPerformed(evt);
            }
        });
        getContentPane().add(directorio_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 260, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Fondo2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 340));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // TODO add your handling code here:
        String nombre = "";
        String direc = "";
        JFileChooser chooser;
        String fileID;
        
        nombre = nombreProyecto_txt.getText();
        direc = directorio_txt.getText();
        
        if (evt.getSource() == btnCrear) {
                chooser = new JFileChooser("\\"); // Directory as default
                chooser.setDialogTitle("Select Location");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setAcceptAllFileFilterUsed(false);

                // Mostrar el cuadro de diálogo para seleccionar la ubicación
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                // Obtener la ruta seleccionada
                fileID = chooser.getSelectedFile().getPath();
                directorio_txt.setText(fileID); // Mostrar la ruta seleccionada en el JTextField

                // Concatenar el nombre del proyecto con la ruta seleccionada
                File folder = new File(fileID + "\\" + nombre);

                // Verificar si la carpeta existe y crearla si no
                if (!folder.exists()) {
                    boolean success = folder.mkdirs(); // Crear directorios necesarios

                    if (success) {
                    JOptionPane.showMessageDialog(null, "Carpeta '" + nombre + "' creada con éxito en " + fileID,
                            "CARPETA", JOptionPane.INFORMATION_MESSAGE);
                    panel_pricipal p = new panel_pricipal();
                    p.setVisible(true);
                    this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR: No se pudo crear la carpeta '" + nombre + "'",
                            "CARPETA", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                JOptionPane.showMessageDialog(null, "ERROR: La carpeta '" + nombre + "' ya existe en " + fileID,
                        "CARPETA", JOptionPane.ERROR_MESSAGE);
                }
            }
        } 
    }//GEN-LAST:event_btnCrearActionPerformed

    private void directorio_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_directorio_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_directorio_txtActionPerformed

    private void nombreProyecto_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreProyecto_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreProyecto_txtActionPerformed

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
            java.util.logging.Logger.getLogger(Nuevo_proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nuevo_proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nuevo_proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nuevo_proyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Nuevo_proyecto().setVisible(true);
              
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JTextField directorio_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField nombreProyecto_txt;
    // End of variables declaration//GEN-END:variables
}
