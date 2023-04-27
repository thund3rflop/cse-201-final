import java.awt.Color;

/**
 * Creates the framework for the first level. 
 * 
 * @author abigailjackson
 *
 */
public class LevelOne {
    
    /**
     * Global instance vairable that sets the goal time. 
     */
    protected static long goalTime = 120000;
    
    /**
     * Global instance variables that sets the time goal multiplier. 
     */
    protected static double timeMultiplier = 0.5;
    
    /**
     * Global instance variable that determines if item was hit. 
     */
    protected static boolean hiddenItem; 
    
    /**
     * Global instance variable that sets the multiplier for the hidden item. 
     */
    protected static double itemMultiplier = 0.25; 
    
    /**
     * Global instance variable that sets base score for beating the level. 
     */
    protected static double score = 1000; 
    
    /**
     * Main method that deals with the level's functionality. 
     * @param args
     */
    public static void main(String[] args) {
        // Sets the start time of the system.
        long startTime = System.currentTimeMillis(); 
        
        // Creates parameters for the hidden item. 
        double speed = 0.25;  
        Color c = Color.DARK_GRAY; 
        int size = 40;
        int x = 400;
        int y = 300; 
        
        // Creates hidden item. 
        HiddenItem item = new HiddenItem(speed, c, size, x, y); 
        
        // If the hidden item was hit, add multiplier. 
        if (hiddenItem = true) {
            score = score + (score * itemMultiplier); 
        }
        
        // Sets the end time of the system. 
        long finishTime = System.currentTimeMillis(); 
        
        // Calculates total time. 
        long timePassed = finishTime - startTime;

        // If total time is less than or equal to goal, add mutliplier. 
        if (timePassed <= goalTime) {
            score = score + (score * timeMultiplier); 
        }
       
    }

}
