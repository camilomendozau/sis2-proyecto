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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.Timer;
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
    
   private Timer autoSaveTimer;

    public panel_pricipal() {
        initComponents();
        agregarPopupMenu();
        
        notificationLabel = new JLabel("Autoguardado realizado", JLabel.RIGHT);
        notificationLabel.setForeground(Color.WHITE);
        notificationLabel.setVisible(false);

        // Ajustar el tamaño y la posición de la etiqueta de notificación
        notificationLabel.setBounds(5, 5, 150, 30); // Cambia las coordenadas según lo necesites
        //add(notificationLabel); // Añadir la etiqueta al JFrame

        panelDiagramaR = new ResizableImagePanel(""); // Aquí se carga tu imagen UML
        panelDiagramaR.setPreferredSize(new Dimension(300, 300)); // Tamaño inicial
        getContentPane().add(panelDiagramaR, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 280, 360)); // Ajusta la posición y tamaño inicial
        //panelDiagrama.add(panelDiagramaR);
        this.setLocationRelativeTo(null);  // Centrar la ventana
        this.setResizable(false);  // Evitar maximización

        // Timer para el autoguardado cada 10 minutos (600000 milisegundos)
        autoSaveTimer = new Timer(10000, e -> autoSave());
        autoSaveTimer.start(); // Iniciar el timer

        setVisible(true); // Mostrar la ventana
    }

    private void autoSave() {
        try {
            // Ruta del archivo donde se guardará el texto
            File file = new File("autoguardado.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(jTextArea1.getText());
                showNotification("Autoguardado realizado");
            }
            System.out.println("Autoguardado realizado en: " + file.getAbsolutePath()); // Mensaje en la terminal
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showNotification(String message) {
        notificationLabel.setText(message);
        notificationLabel.setVisible(true);
        System.out.println(message); // Mensaje en la terminal

        // Hacer que la notificación desaparezca después de 2 segundos
        Timer timer = new Timer(2000, e -> notificationLabel.setVisible(false));
        timer.setRepeats(false);
        timer.start();
    }
    private void agregarPopupMenu() {

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem agregarComentario = new JMenuItem("Agregar Comentario");

        agregarComentario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Point mousePosition = panelDiagrama.getMousePosition();

                String comentario = JOptionPane.showInputDialog("Escribe tu comentario:");

                if (comentario != null && !comentario.isEmpty()) {

                    JLabel comentarioLabel = new JLabel(comentario);

                    // Definir la fuente personalizada
                    Font font = new Font("MingLiu-ExtB", Font.BOLD, 14);
                    comentarioLabel.setFont(font);

                    comentarioLabel.setOpaque(false);  // Asegurarse que el fondo sea transparente

                    comentarioLabel.setForeground(new Color(0, 0, 0));

                    comentarioLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                    // Hacer que el comentario sea arrastrable
                    hacerArrastrable(comentarioLabel);

                    // Agregar un menú contextual para borrar el comentario
                    agregarMenuBorrar(comentarioLabel);

                    // Si no hay posición del mouse, colocar en el centro del panel
                    if (mousePosition == null) {
                        mousePosition = new Point(panelDiagrama.getWidth() / 2, panelDiagrama.getHeight() / 2);
                    }

                    // Ubicar el comentario en la posición del mouse
                    comentarioLabel.setBounds(mousePosition.x, mousePosition.y, comentario.length() * 10, 30);

                    // Agregar el comentario al panel
                    panelDiagrama.add(comentarioLabel);
                    panelDiagrama.repaint();
                }
            }
        });

        // Agregar el item "Agregar Comentario" al popup menu
        popupMenu.add(agregarComentario);

        panelDiagrama.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mostrarPopupMenu(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mostrarPopupMenu(e);
            }

            private void mostrarPopupMenu(MouseEvent e) {
                if (e.isPopupTrigger()) {  // Detectar clic derecho
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    // Método para hacer que un JLabel sea arrastrable
    private void hacerArrastrable(JLabel label) {
        final Point[] puntoInicial = {null};

        // Detectar cuando se presiona el mouse en el JLabel
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                puntoInicial[0] = e.getPoint(); // Guardar la posición inicial del clic
            }
        });

        label.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {

                Point puntoLabel = label.getLocation();
                int x = puntoLabel.x + e.getX() - puntoInicial[0].x;
                int y = puntoLabel.y + e.getY() - puntoInicial[0].y;
                label.setLocation(x, y); // Actualizar la posición del JLabel
            }
        });
    }

    private void agregarMenuBorrar(JLabel label) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem borrarComentario = new JMenuItem("Borrar Comentario");

        // Acción al seleccionar "Borrar Comentario"
        borrarComentario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelDiagrama.remove(label);  // Eliminar el comentario del panel
                panelDiagrama.repaint();  // Refrescar el panel
            }
        });

        // Agregar el item "Borrar Comentario" al popup menu
        popupMenu.add(borrarComentario);

        // Agregar mouse listener para detectar clic derecho en el comentario (label)
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mostrarPopupMenu(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mostrarPopupMenu(e);
            }

            private void mostrarPopupMenu(MouseEvent e) {
                if (e.isPopupTrigger()) {  // Detectar clic derecho
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        panelDiagrama = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        notificationLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("GENERADOR DE CODIGO");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 350, 50));

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

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("\n\n\n\n\n\n\n\n\n\n");
        jTextArea1.setMargin(new java.awt.Insets(28, 28, 200, 6));
        jScrollPane2.setViewportView(jTextArea1);

        panelCodigo.add(jScrollPane2);

        getContentPane().add(panelCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 270, 360));
        getContentPane().add(panelDiagrama, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 390, 450));

        jButton1.setText("IDENTIFICAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 470, -1, -1));

        jButton2.setText("SALIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 470, -1, -1));

        jButton3.setText("GENERAR TXT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, -1));

        notificationLabel.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(notificationLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 240, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Fondo3.1.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 530));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/file.png"))); // NOI18N
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

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/engranaje1.png"))); // NOI18N
        jMenu2.setText("SELECCIONAR LENGUAJE");

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/java1.png"))); // NOI18N
        jMenuItem4.setText("JAVA");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/java2.png"))); // NOI18N
        jMenuItem5.setText("PYTHON");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/c++.png"))); // NOI18N
        jMenuItem6.setText("C++");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/comem.png"))); // NOI18N
        jMenu3.setText("AGREGAR COMENTARIOS");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

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
                        
                        agregarArchivoUML(umlFile.getName());
                    } else {
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
                                
                                umlFile = archivo_seleccionado;
                            } catch (IOException ex) {
                                Logger.getLogger(panel_pricipal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            });
        }    
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Deseas confirmar los cambios?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {

            System.out.println("Cambios guardados.");
        } else {

            System.out.println("Acción cancelada.");
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Deseas confirmar los cambios?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {

            System.out.println("Cambios guardados.");
        } else {

            System.out.println("Acción cancelada.");
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // TODO add your handling code here:
         // Obtener posición del mouse para el comentario flotante
        Point mousePosition = panelDiagrama.getMousePosition();

// Solicitar el comentario
        String comentario = JOptionPane.showInputDialog("Escribe tu comentario:");

        if (comentario != null && !comentario.isEmpty()) {
            // Crear un JLabel con el comentario y ubicarlo donde se hizo clic
            JLabel comentarioLabel = new JLabel(comentario);

            // Definir la fuente personalizada
            Font font = new Font("MingLiu-ExtB", Font.BOLD, 14);
            comentarioLabel.setFont(font);

            // Configurar el fondo completamente transparente
            comentarioLabel.setOpaque(false);  // Asegurarse que el fondo sea transparente

            // Configurar el color del texto a cian
            comentarioLabel.setForeground(new Color(0, 0, 0));

            // Establecer un borde negro alrededor del comentario
            comentarioLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // Hacer que el comentario sea arrastrable
            hacerArrastrable(comentarioLabel);

            // Agregar un menú contextual para borrar el comentario
            agregarMenuBorrar(comentarioLabel);

            // Si no hay posición del mouse, colocar en el centro del panel
            if (mousePosition == null) {
                mousePosition = new Point(panelDiagrama.getWidth() / 2, panelDiagrama.getHeight() / 2);
            }

            // Ubicar el comentario en la posición del mouse
            comentarioLabel.setBounds(mousePosition.x, mousePosition.y, comentario.length() * 10, 30);

            // Agregar el comentario al panel
            panelDiagrama.add(comentarioLabel);
            panelDiagrama.repaint();
        }
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        jTextArea1.setText("Este es un ejemplo que se ve ya que no se genera codigo en este sprint");
            
            // Redibujar el panel para que refleje los cambios
            panelCodigo.revalidate();
            panelCodigo.repaint();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Deseas confirmar los cambios?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {

            System.out.println("Cambios guardados.");
        } else {

            System.out.println("Acción cancelada.");
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed
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
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel notificationLabel;
    private javax.swing.JPanel panelCodigo;
    private javax.swing.JPanel panelDiagrama;
    // End of variables declaration//GEN-END:variables
}
