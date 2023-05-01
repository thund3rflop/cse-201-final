import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * A class that creates the basic outline
 * for enemies (asteroids) within the game. 
 * 
 * @author Abigail Jackson
 */
public abstract class Enemies extends JComponent {

    /**
     * Instance variable that represents enemies' atributes. 
     */
    protected double enemySpeed;
    protected int enemyCount; 
    protected Color enemyColor; 
    protected static int deathCount; 
    
    /**
     * Constructor that initializes bounds/speeds. 
     * @param x The x-coordinate bounds. 
     * @param y The y-coordinate bounds. 
     * @param height The height of the object. 
     * @param width The width of the object. 
     * @param enemySpeed The speed of the Enemy object. 
     * @param enemySize The size of the Enemy object.
     */
    public Enemies(int x, int y, int height, int width, double enemySpeed) {
        this.setBounds(x, y, width, height);
        this.enemySpeed = enemySpeed; 
    }
    
    /**
    * Deletes enemies when they are hit.
    * @param list The list of enemies. 
    * @param enemy The enemy that is hit and removed. 
    */ 
    public void processCollision(ArrayList<Enemies> list, int enemy) {
        Enemies e = list.get(enemy); 
        list.remove(enemy);  
    }
     
    /**
     * Draws a filled circle with Enemy's color & bounds. 
     * @param g The Graphics that include the color & bounds. 
     */
    public void drawEnemy(Graphics g) {
        final Rectangle bounds = this.getBounds();
        g.setColor(this.enemyColor);
        g.fillOval(bounds.x, bounds.y, bounds.height, bounds.width); 
    }
    
    /**
     * Sets the enemy speed. 
     * @param speed Double that holds the changed speed. 
     */
    public void setEnemySpeed(double speed) {
        enemySpeed = speed; 
    }
    
    /**
     * Gets enemy speed. 
     * @return Double that is Enemy's speed. 
     */
    public double getEnemySpeed() {
        return this.enemySpeed; 
    }
    
    /**
     * Sets the number of enemies per level. 
     * @param count Number of enemies. 
     */
    public void SetEnemyCount(int count) {
        enemyCount = count; 
    }
    
    /**
     * Gets the number of enemies. 
     * @return The number of enemies. 
     */
    public int GetEnemyCount() {
        return this.enemyCount; 
    }
    
    /**
     * Used to paint the enemies.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawEnemy(g);
    }
}
