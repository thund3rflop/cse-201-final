import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;

    public class Projectiles extends JComponent {
        
        private Color color;
        private int speedX;
        private int speedY;

        public Projectiles(int x, int y, int width, int height, Color color, int speedX, int speedY) {
            
            this.color = color;
            this.speedX = speedX;
            this.speedY = speedY;
            this.setBounds(x, y, width, height);
  
        }

        public void move() {
            int x1 = getX() + speedX;
            int y1 = getY() + speedY;
            
            setBounds(x1, y1, getWidth(), getHeight());
        }

        public void paintComponent(Graphics g) {
            g.setColor(color);
            g.fillRect(getX(), getY(), getWidth(), getHeight());
        }
    }



