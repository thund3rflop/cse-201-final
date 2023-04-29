import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;
import javax.swing.JPanel;

    public class Projectiles extends JPanel {
        
    
        private static final long serialVersionUID = 1L;
        private ArrayList<Projectiles> projectiles = new ArrayList<>();
        private int x;
        private int y;
        private Color color;
        private int width;
        private int height;
        
        
        public Projectiles() {
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    int x = getWidth() / 2 - 10;
                    int y = getHeight() / 2 - 10;
                    
                    Projectiles proj = new Projectiles(x, y, Color.RED);
                    
                    projectiles.add(proj);
                    repaint();
                }
            });
        }

        public Projectiles(int x, int y, Color color) {
            
            this.color = color;
            this.x = x;
            this.y = y;
            this.setBounds(x, y, width, height);
  
        }


        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            for (Projectiles pro: projectiles) {
                g.setColor(pro.getColor());
                g.fillRect(pro.getX(), pro.getY(), 20, 20);
            }
            g.setColor(color);
            g.fillRect(getX(), getY(), getWidth(), getHeight());
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
    }

