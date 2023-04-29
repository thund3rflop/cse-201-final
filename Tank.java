import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;

public class Tank extends JPanel {

    // Define the initial shape to be rotated
    private final int[] xPoints = { 440, 390, 390, 440 };
    private final int[] yPoints = { 310, 310, 290, 290 };
    private final int numPoints = 4;
    
    // Define the rotation point
    private final Point rotationPoint = new Point(400, 300);
    
    // Define the current angle of rotation
    private double angle = 0;
    
    public Tank() {
      // Add a mouse motion listener to track the mouse movement
      addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseMoved(MouseEvent e) {
          // Calculate the new angle of rotation based on the mouse position
          angle = Math.atan2(e.getY() - rotationPoint.getY(), e.getX() - rotationPoint.getX());
          // Repaint the panel with the new rotation
          repaint();
        }
      });
    }
    
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;
      g2d.setColor(Color.blue);
      // Translate the graphics context to the rotation point
      g2d.translate(rotationPoint.getX(), rotationPoint.getY());
      // Rotate the graphics context by the current angle
      g2d.rotate(angle);
      // Translate the graphics context back to the original position
      g2d.translate(-rotationPoint.getX(), -rotationPoint.getY());
      // Draw the rotated shape
      g2d.drawPolygon(xPoints, yPoints, numPoints);
      g2d.fillPolygon(xPoints, yPoints, numPoints);
      g2d.drawOval(380, 280, 40, 40);
      g2d.setColor(Color.orange);
      g2d.fillOval(380, 280, 40, 40);
      
    }

}