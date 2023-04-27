import java.awt.Color;

/**
 * A class that creates an object that blends
 * into the background. If users hit this object,
 * they get a score bonus.
 * 
 * @author abigailjackson
 *
 */
public class HiddenItem {
    
    /**
     * Global instance variable for the item's speed. 
     */
    protected double itemSpeed;
    
    /**
     * Global instance variable for the item's color. 
     */
    protected Color itemColor;
    
    /**
     * Global instance variable for the item's size.
     */
    protected int itemSize; 
    
    /**
     * Global instance variable for the item's x-axis position.  
     */
    protected int xPos;
    
    /**
     * Global instance variable for the item's y-axis position.  
     */
    protected int yPos; 
    
    /**
     * Constructor that creates a hidden item. 
     * @param speed The item's speed. 
     * @param c The item's color. 
     * @param size The item's size. 
     * @param x The item's x-axis position. 
     * @param y The item's y-axis position. 
     */
    public HiddenItem(double speed, Color c, int size, int x, int y) {
        itemSpeed = speed;
        itemColor = c;
        itemSize = size; 
        xPos = x;
        yPos = y; 
    }
}
