/*
 * Deals with the first level of the game. 
 */
public class LevelOne {
    
    // Global instance variables. 
    protected static long goalTime = 120000;
    protected static double multiplier = 0.5;
    protected static double score = 1000; 
    
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); 
        long finishTime = System.currentTimeMillis(); 
        long timePassed = finishTime - startTime; 
        
        if (timePassed <= goalTime) {
            score = score + (score * multiplier); 
        }
    }

}
