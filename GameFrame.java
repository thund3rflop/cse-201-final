import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
* A class that creates the game frame.
* 
* @authors Chase Hollander, Abigail Jackson, Sam Kujawa, Chanakya Pandya
*/
public class GameFrame extends JFrame {
    /**
    * Creates the game panel.
    */ 
    private static GamePanel gamePanel;
    
    /** 
    * The start time of the system. 
    */
    private static long startTime;
    
    /** 
    * The end time of the system. 
    */ 
    private static long finishTime;
    
    /** 
    * The total time of the system was run.
    */ 
    private static long totalTime;
    
    /**
    * The goal time in milliseconds. = 2 minutes. 
    */ 
    private static long timeGoal = 120000;
    
    /**
    * The multiplier for hitting the hidden item.
    */ 
    private static double itemMulti = 0.5;
    
    /**
    * The multiplier for beating the time goal. 
    */ 
    private static double timeMulti = 0.75;
    
    /**
    * Label for the score.
    */
    private static JLabel scoreLabel;
    
    /**
    * Label for the amount of deaths.
    */ 
    private static JLabel deathLabel;
    
    /**
    * Actual game frame.
    */ 
    private static GameFrame frame;
    
    /**
    * Sound effects.
    */ 
    static Sound se = new Sound();

    /**
    * A constructor that creates the game frame and labels.
    */
    public GameFrame() {
        setLayout(new BorderLayout());
        gamePanel = new GamePanel();
        this.add(gamePanel, BorderLayout.CENTER);

        // Creates score and death labels. 
        scoreLabel = new JLabel("Score: ");
        deathLabel = new JLabel("Deaths: ");
        scoreLabel.setForeground(Color.WHITE);
        deathLabel.setForeground(Color.WHITE);

        // Create a new JPanel for the labels
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.setBackground(Color.BLACK);
        labelPanel.add(scoreLabel);
        labelPanel.add(deathLabel);

        // Creates time goal label. 
        JLabel timeGoalLabel = new JLabel("Time goal: 2 minutes");
        timeGoalLabel.setForeground(Color.WHITE);
        labelPanel.add(timeGoalLabel);

        // Add the labelPanel to the NORTH region
        add(labelPanel, BorderLayout.NORTH);

        // Makes all labels visible on game panel. 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Bullet Blitz");
    }

    /**
    * Main method that does some basic functions.
    */ 
    public static void main(String[] args) {
        // Display rules for game
        JOptionPane.showMessageDialog(null, "Rules: "
                + ("\n1. Hit the asteroids before they reach the turret."
                        + "\n2. Beat the time for extra points! \n3. Hit the hidden item for more extra points!"),
                "Rules",
                JOptionPane.INFORMATION_MESSAGE);
        frame = new GameFrame();

        // Game loop
        int delay = 1000 / 60; // 60 FPS
        ActionListener gameLoop = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Determines if enemies have hit turret. 
                frame.gamePanel.detectDeath();
                // frame.gamePanel.detectCollision();
                frame.gamePanel.moveEnemies();
                frame.gamePanel.spawnEnemies();
                frame.gamePanel.repaint();
                gameStuff();
            }
        };
        Timer timer = new Timer(delay, gameLoop);
        timer.start();
        // Begin background music
        se.setFile("BlueBoyAdventure.wav");
        se.play();
        se.loop();
    }

    /**
    * A method that helps with more functionality. 
    */ 
    public static void gameStuff() {
        // Starts the time. 
        startTime = System.currentTimeMillis();

        // Updates the score label. 
        int score = frame.gamePanel.getScore();
        scoreLabel.setText("Score: " + score);
        if (score > 1000) {

            // Checks to see if time goal was beat.
            finishTime = System.currentTimeMillis();
            totalTime = finishTime - startTime;
            if (totalTime <= timeGoal) {
                score = (int) (score + (score * timeMulti));
            }

            // Checks to see if the hidden item was hit.
            boolean hiddenItemHit = frame.gamePanel.getHit();
            if (hiddenItemHit = true) {
                score = (int) (score + (score * itemMulti));
            }
            // Displays win message. 
            JOptionPane.showMessageDialog(null, "You Win! Score: " + score,
                    "SCORE GOAL REACHED",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        // Checks to see if the player has too many deaths. 
        int deaths = frame.gamePanel.getDeath();
        deathLabel.setText("Deaths: " + deaths);
        if (deaths >= 25) {
            // Displays death message. 
            JOptionPane.showMessageDialog(null, "You Lose!",
                    "DEATH LIMIT REACHED",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
}
