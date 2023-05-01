import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
* A class that extends enemies to create custom
* enemies using an image. 
*
* @author Sam Kujawa
*/ 
public class CustomEnemy extends Enemies {
    private Image enemyImage;

    /**
    * Creates a custom enemy with an image.
    * @param x The x-position. 
    * @param y The y-position. 
    * @param height The hieght of the enemy. 
    * @param width The width of the enemy.
    * @param enemySpeed The enemy's speed.
    */ 
    public CustomEnemy(int x, int y, int height, int width, double enemySpeed) {
        super(x, y, height, width, enemySpeed);
        try {
            enemyImage = ImageIO.read(getClass().getResource("asteroid.png")).getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    /**
    * Moves the enemies towards the center.
    * @param moveX Moves the enemy along x-axis.
    * @param moveY Moves the enemy along the y-axis.
    */
    public void move(double moveX, double moveY) {
        int newX = (int) (getBounds().x + moveX);
        int newY = (int) (getBounds().y + moveY);
        setLocation(newX, newY);
        repaint();
    }

    // Overriden method that paints the custom enemy with an image. 
    @Override
    public void paint(Graphics g) {
        if (enemyImage != null) {
            g.drawImage(enemyImage, getBounds().x, getBounds().y, null);
        }
    }
}