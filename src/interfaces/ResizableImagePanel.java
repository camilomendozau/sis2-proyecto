/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;


public class ResizableImagePanel extends JPanel {

    private boolean dragging = false;
    private boolean resizing = false;
    private int mouseX, mouseY;

    private Image image;

    public ResizableImagePanel(String imagePath) {
        image = Toolkit.getDefaultToolkit().getImage(imagePath); // Cargar la imagen

        // Establecer el tamaño preferido del panel
        setPreferredSize(new Dimension(200, 200));
        setBackground(Color.WHITE); // Color de fondo

        // Listener para detectar clics del mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isInResizeArea(e.getX(), e.getY())) {
                    resizing = true; // Iniciar redimensionamiento
                    mouseX = e.getX();
                    mouseY = e.getY();
                } else {
                    dragging = true; // Iniciar arrastre
                    mouseX = e.getX();
                    mouseY = e.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                dragging = false; // Detener arrastre
                resizing = false; // Detener redimensionamiento
            }
        });

        // Listener para detectar el movimiento del mouse
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (resizing) {
                    // Redimensionar el panel
                    int deltaX = e.getX() - mouseX;
                    int deltaY = e.getY() - mouseY;

                    Dimension newSize = new Dimension(
                        Math.max(getWidth() + deltaX, 50), // Mínimo tamaño 50x50
                        Math.max(getHeight() + deltaY, 50)
                    );
                    setPreferredSize(newSize);
                    revalidate();
                    mouseX = e.getX();
                    mouseY = e.getY();
                } else if (dragging) {
                    // Mover el panel
                    int deltaX = e.getX() - mouseX;
                    int deltaY = e.getY() - mouseY;

                    // Mover el panel
                    Point location = getLocation();
                    setLocation(location.x + deltaX, location.y + deltaY);
                }
                repaint(); // Repaint después de mover o redimensionar
            }
        });
    }

    private boolean isInResizeArea(int x, int y) {
        int edgePadding = 10; // Tamaño del área de redimensionamiento
        return x >= getWidth() - edgePadding && y >= getHeight() - edgePadding;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this); // Dibuja la imagen ajustada
        }
    }
}


