import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
* A class that creates the projectiles to shoot
* the enemies.
*
* @author Chase Hollander
*/
public class Projectiles extends JPanel {
    private static final long serialVersionUID = 1L;
    private ArrayList<Projectiles> projectiles = new ArrayList<>();
    private int x;
    private int y;
    private Color color;
    private int width;
    private int speed;
    private int height;
    
    public Projectiles() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int x = getWidth() / 2 - 10;
                int y = getHeight() / 2 - 10;
                int speed = 10;
                
                Projectiles proj = new Projectiles(x, y, speed);
                projectiles.add(proj);
                repaint();
            }
        });
   }

   public Projectiles(int x, int y, int speed) {
       this.speed = speed;
       this.color = Color.RED;
       this.x = x;
       this.y = y;
       this.setBounds(x, y, width, height);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Projectiles pro: projectiles) {
            pro.drawProjectile(g);
        }
    }
    
    public void drawProjectile(Graphics g) {
        final Rectangle bounds = this.getBounds();
        g.setColor(color);
        g.fillOval(bounds.x, bounds.y, bounds.width, bounds.height); 
    }
    
    public void move(int width, int height, ArrayList<Projectiles> list,
                int projectile) {
        if (list.isEmpty() || projectile >= list.size()) {
            return;
        }
        
        int newHeight = list.get(projectile).getY() - speed;
        list.get(projectile).setBounds(list.get(projectile).getX(), newHeight,
                    list.get(projectile).getWidth(), list.get(projectile).getHeight());
        if (newHeight < 0) {
            list.remove(projectile);
        }
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public Color getColor() {
        return color;
    }
    
    public int getSpeed() {
        return speed;
    }
}

