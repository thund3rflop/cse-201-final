import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

public class Tank extends Enemies implements MouseMotionListener {

    private int x, y; // position of tank
    private int turretAngle; // angle of turret in degrees
    private Color tankColor, turretColor;

    public Tank(int x, int y, int height, int width, double enemySpeed, Color tankColor, Color turretColor) {
        super(x, y, height, width, enemySpeed);
        this.tankColor = tankColor;
        this.turretColor = turretColor;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(tankColor);
        g2d.fillRect(getX(), getY(), getWidth(), getHeight());
        g2d.rotate(Math.toRadians(turretAngle), getX() + getWidth() / 2, getY() + getHeight() / 2);
        g2d.setColor(turretColor);
        g2d.fillRect(getX() + getWidth() / 2 - 5, getY(), 10, getHeight());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Do nothing
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point mousePos = e.getPoint();
        int dx = getX() + getWidth() / 2 - mousePos.x;
        int dy = getY() + getHeight() / 2 - mousePos.y;
        turretAngle = (int) Math.toDegrees(Math.atan2(dy, dx));
        repaint();
    }

}
