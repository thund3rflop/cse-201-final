import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * My mess to clean up. 
 * @author Chanakya Pandya
 *
 */
public class Projectiles extends Enemies {
    
    public static int x = 400;
    public static int y = 300;
    public static Color color = Color.RED;
    public static int width = 15;
    public static int speed = 15;
    public static int height = 15;
    public static double angle = 0;
    

    public Projectiles(double angle) {
        super(x, y, height, width, speed);
        this.angle = angle;
    }
    
    public void move(double angle) {
        int moveX = (int)(x + speed*Math.cos(angle));
        int moveY = (int)(y + speed*Math.sin(angle));
        int newX = (int) (getBounds().x + moveX);
        int newY = (int) (getBounds().y + moveY);
        setLocation(newX, newY);
        repaint();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.drawOval(x, y, width, height);
        g.fillOval(x, y, width, height);
    }

//    private static final long serialVersionUID = 1L;
//    public static int x = 400;
//    public static int y = 300;
//    public static Color color = Color.RED;
//    public static int width = 15;
//    public static int speed = 15;
//    public static int height = 15;
//    public final static Point rotationPoint = new Point(x, y);
//
//    public static double angle = 0;
//
//    public Projectiles(double angle) {
//        this.angle = angle;
//        this.setBounds(x, y, width, height);
//
//    }
//
//    public void moveProjectile() {
//        double dx = speed * Math.cos(angle);
//        double dy = speed * Math.sin(angle);
//
//        x += dx;
//        y += dy;
//
//        this.setBounds(x, y, width, height);
//    }
//
//    public void paintComponent(Graphics g) {
//        moveProjectile();
//        super.paintComponent(g);
//        drawProjectile(g);
//
//    }
//
//    public void drawProjectile(Graphics g) {
//        final Rectangle bounds = this.getBounds();
//        g.setColor(color);
//        g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height);
//    }
//
////        public void move(int width, int height, ArrayList<Projectiles> list,
////                int projectile) {
////            if (list.isEmpty() || projectile >= list.size()) {
////                return;
////            }
////
////            int newHeight = list.get(projectile).getY() - speed;
////            list.get(projectile).setBounds(list.get(projectile).getX(), newHeight,
////                    list.get(projectile).getWidth(), list.get(projectile).getHeight());
////
////            if (newHeight < 0) {
////                list.remove(projectile);
////            }
////
////        }

}
