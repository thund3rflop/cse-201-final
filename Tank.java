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

/**
* A class that creates the tank.
*
* @author Chanakya Pandya
*/ 
public class Tank extends JPanel {

    // The fixed position of the tank. 
    public final int[] xPoints = { 440, 390, 390, 440 };
    public final int[] yPoints = { 310, 310, 290, 290 };
    public final int numPoints = 4;
    
    // Where the tank rotates around. 
    public final Point rotationPoint = new Point(400, 300);
    
    // The angle of rotation of the tank that follows the 
    // movement of the mouse. 
    public double angle = 0;
    
    /**
    * A constructor that creates the tank.
    */ 
    public Tank() {
      // Mouse motion listener that tracks the movement of the mouse
      // to move the turret. 
      addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseMoved(MouseEvent e) {
          angle = Math.atan2(e.getY() - rotationPoint.getY(), e.getX() - rotationPoint.getX());
          repaint();
        }
      });
    }
    
    /**
    * Paints the tank.
    */ 
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;
      g2d.setColor(Color.red);
      g2d.translate(rotationPoint.getX(), rotationPoint.getY());
      g2d.rotate(angle);
      g2d.translate(-rotationPoint.getX(), -rotationPoint.getY());
      g2d.drawPolygon(xPoints, yPoints, numPoints);
      g2d.fillPolygon(xPoints, yPoints, numPoints);
      g2d.drawOval(380, 280, 40, 40);
      g2d.setColor(Color.orange);
      g2d.fillOval(380, 280, 40, 40);
          
    }
}
