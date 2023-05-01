import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;
import javax.swing.JPanel;

    public class Projectiles extends JPanel implements MouseListener{
        
    
        private static final long serialVersionUID = 1L;
        private ArrayList<Projectiles> projectiles = new ArrayList<>();
        private int x;
        private int y;
        private Color color;
        private int width;
        private int speed;
        private int height;
        private final static Point rotationPoint = new Point(400, 300);
        
        private static double angle = 0; 
        
       public Projectiles() {
           addMouseListener(new MouseAdapter() {
       
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                int speed = 10;
                double angle = Math.atan2(e.getY() - rotationPoint.getY(), e.getX() - rotationPoint.getX());
                
                Projectiles proj = new Projectiles(x, y, width, height, speed, angle);
                
                projectiles.add(proj);
                moveProjectile();
                repaint();
            }
        });
       }
       
            
        public Projectiles(int x, int y, int width, int height,int speed, double angle) {
            this.speed = speed;
            this.color = Color.RED;
            this.width = width;
            this.height = height;
            this.x = x;
            this.y = y;
            this.angle = angle;
            this.setBounds(x, y, width, height);
  
        }
        
        public void moveProjectile() {
            double dx = speed * Math.cos(angle);
            double dy = speed * Math.sin(angle);
            
            x += dx;
            y += dy;
            
            this.setBounds(x, y, width, height);
        }
        
        public void movement() {
            for (Projectiles projectile : projectiles) {
                projectile.moveProjectile();
            }
            repaint();
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


    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int speed = 10;
        double angle = Math.atan2(e.getY() - rotationPoint.getY(), e.getX() - rotationPoint.getX());
        
        Projectiles proj = new Projectiles(x, y, speed, width, height, angle);
        
        projectiles.add(proj);
        repaint();
    }


        
    


    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    }

