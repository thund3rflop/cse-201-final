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
 * 
 * @author Chanakya Pandya
 *
 */
public class Projectiles extends JComponent {

    private int x;
    private int y;
    private Color color = Color.RED;
    private int width = 15;
    private int speed = 15;
    private int height = 15;
    private double angle;

    public Projectiles(double angle) {
        this.angle = angle;
        this.x = 400;
        this.y = 300;
        setBounds(x, y, width, height);
    }

    public void move() {
        x += (int) (speed * Math.cos(angle));
        y += (int) (speed * Math.sin(angle));
        setLocation(x, y);
    }

    public void move(double angle) {
        int moveX = (int) (x + speed * Math.cos(angle));
        int moveY = (int) (y + speed * Math.sin(angle));
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
}
