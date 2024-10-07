/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author USER
 */




import base_datos.ConexionBD;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Registro extends javax.swing.JFrame {

    /**
     * Creates new form Registro
     */
    Connection con = null;
    Statement stmt = null;
    public Registro() {
        initComponents();
        this.setTitle("REGISTRO DE USUARIOS ");
        this.setLocationRelativeTo(null);  // Centrar la ventana
        this.setResizable(false);  // Evitar maximización
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        contratext = new javax.swing.JTextField();
        apellidotext = new javax.swing.JTextField();
        nombretext = new javax.swing.JTextField();
        usuariotext = new javax.swing.JTextField();
        btnRegistro = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REGISTRARSE");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 200, 40));

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("NOMBRE");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 130, 30));

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("APELLIDO");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 150, 30));

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("USUARIO");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 140, 30));

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CONTRASEÑA");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 200, 30));

        contratext.setCaretColor(new java.awt.Color(255, 255, 255));
        contratext.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        contratext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contratextActionPerformed(evt);
            }
        });
        contratext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contratextKeyTyped(evt);
            }
        });
        getContentPane().add(contratext, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 220, 30));

        apellidotext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidotextKeyTyped(evt);
            }
        });
        getContentPane().add(apellidotext, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 220, 30));

        nombretext.setCaretColor(new java.awt.Color(255, 255, 255));
        nombretext.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        nombretext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombretextActionPerformed(evt);
            }
        });
        nombretext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombretextKeyTyped(evt);
            }
        });
        getContentPane().add(nombretext, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 220, 30));

        usuariotext.setCaretColor(new java.awt.Color(255, 255, 255));
        usuariotext.setSelectedTextColor(new java.awt.Color(255, 255, 255));
        usuariotext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuariotextActionPerformed(evt);
            }
        });
        usuariotext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usuariotextKeyTyped(evt);
            }
        });
        getContentPane().add(usuariotext, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, 220, 30));

        btnRegistro.setText("REGISTRAR");
        btnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistroActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Fondo2.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contratextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contratextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_contratextActionPerformed

    private void nombretextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombretextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombretextActionPerformed

    private void usuariotextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuariotextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usuariotextActionPerformed

    private void btnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistroActionPerformed
        String usuario = usuariotext.getText();
    String nombre = nombretext.getText();
    String apellido = apellidotext.getText();
    String contra = contratext.getText();
   
    if (nombre.isEmpty() || apellido.isEmpty() || usuario.isEmpty() || contra.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Debe llenar todos los campos", "AVISO!", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        nombretext.requestFocus();
        return;
    }

    // Intentamos hacer el registro en la base de datos
    try {
        // Conectamos a la base de datos usando la clase `ConexionBD`
        Connection con = ConexionBD.conectar();

        if (con != null) {
            // Preparamos la consulta de inserción de datos
            String sql = "INSERT INTO usuarios (nombre, apellido, usuario, contra) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            // Seteamos los valores en la consulta
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, usuario);
            stmt.setString(4, contra);

            // Ejecutamos la inserción en la base de datos
            stmt.executeUpdate();
            //System.out.println("Los valores han sido agregados a la base de datos.");

            // Cerramos la conexión
            stmt.close();
            con.close();
        }

        // Mensaje de confirmación
        javax.swing.JOptionPane.showMessageDialog(this, "Registro exitoso!", "AVISO!", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        // Limpiamos los campos de texto
        this.usuariotext.setText("");
        this.nombretext.setText("");
        this.apellidotext.setText("");
        this.contratext.setText("");

        // Redirigimos al login
        inicio_login r = new inicio_login();
        r.setVisible(true);
        this.dispose();

    } catch (SQLException e) {
        System.out.println("Error en la inserción: " + e.getMessage());
        javax.swing.JOptionPane.showMessageDialog(this, "Error en el registro", "ERROR!", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnRegistroActionPerformed

    private void nombretextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombretextKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    
        if ((c<'a' || c>'z') && (c<'A' || c>'Z') && (c<' '|| c>' ')) evt.consume();
        
    }//GEN-LAST:event_nombretextKeyTyped

    private void apellidotextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidotextKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
    
        if ((c<'a' || c>'z') && (c<'A' || c>'Z') && (c<' '|| c>' ')) evt.consume();
        
    }//GEN-LAST:event_apellidotextKeyTyped

    private void usuariotextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuariotextKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_usuariotextKeyTyped

    private void contratextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contratextKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_contratextKeyTyped

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
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidotext;
    private javax.swing.JButton btnRegistro;
    private javax.swing.JTextField contratext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField nombretext;
    private javax.swing.JTextField usuariotext;
    // End of variables declaration//GEN-END:variables
}
