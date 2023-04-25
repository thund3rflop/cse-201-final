import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CustomEnemy extends Enemies {
    private Image enemyImage;

    public CustomEnemy(int x, int y, int height, int width, double enemySpeed) {
        super(x, y, height, width, enemySpeed);
        try {
            enemyImage = ImageIO.read(getClass().getResource("asteroid.png")).getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    public void move(double moveX, double moveY) {
        int newX = (int) (getBounds().x + moveX);
        int newY = (int) (getBounds().y + moveY);
        setLocation(newX, newY);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (enemyImage != null) {
            g.drawImage(enemyImage, getBounds().x, getBounds().y, null);
        }
    }
}
