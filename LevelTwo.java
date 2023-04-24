/*
 * Deals with the second level of the game. 
 */
public class LevelTwo {
    
    // Global instance variables. 
    protected static long goalTime = 90000;
    protected static double multiplier = 0.75;
    protected static double score = 2000; 
    
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); 
        long finishTime = System.currentTimeMillis(); 
        long timePassed = finishTime - startTime; 
        
        if (timePassed <= goalTime) {
            score = score + (score * multiplier); 
        }
    }

}
