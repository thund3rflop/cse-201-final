import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

    public class Projectiles {
        private int x;
        private int y;
        private int width;
        private int height;
        private Color color;
        private int speedX;
        private int speedY;

        public Projectiles(int x, int y, int width, int height, Color color, int speedX, int speedY) {
            this.x = x;
            this.y = y;
            this.width = 10;
            this.height = 10;
            this.color = color;
            this.speedX = speedX;
            this.speedY = speedY;
            
            double dist = Math.sqrt(Math.pow(width - height, 2) + Math.pow(x - y, 2));
            this.speedX = (int) ((height - x) / dist * speedX);
            this.speedY = (int) ((height - y) / dist * speedY);
        }

        public void move() {
            x += speedX;
            y += speedY;
        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, width, height);
        }

        public void paint(Graphics g) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }



