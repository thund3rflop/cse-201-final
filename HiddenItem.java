import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
* A class that creates a hidden item randomly throughout the levels.
*
* @author Abigail Jackson
*/ 
public class HiddenItem extends Enemies {
    
    /** Global instance variables that create
    * the positions, size, and speed of the hidden item.
    */ 
    protected static int xPos = (int) (Math.random() * 800);
    protected static int yPos = (int) (Math.random() * 600); 
    protected static int height = 20;
    protected static int width = 20; 
    protected static int size = 40; 
    protected static double speed = 0; 
    
    /**
    * A constructor that creates a hidden item object.
    * @param x The x-position.
    * @param y The y-position.
    * @param height The height of the item (half its size).
    * @param width The width of the item (half its size).
    * @param enemySpeed The speed of the item (zero). 
    */ 
    public HiddenItem(int x, int y, int height, int width, double enemySpeed) {
        super(xPos, yPos, height, width, speed);
    }
    
    /**
    * A main method just for functionality.
    */ 
    public static void main(String[] args) {
       return; 
    }
    
    /**
    * Paints the hidden item.
    * @param g The graphics to allow the item to be painted. 
    */ 
    protected void paintComponent(Graphics g) {     
        g.setColor(Color.DARK_GRAY);
        g.drawOval(xPos - width, yPos - height, size, size); 
        g.fillOval(xPos - width, yPos - height, size, size); 
    }
}
