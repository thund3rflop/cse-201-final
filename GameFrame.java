import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
* Creates the frame of the game.
*
* @authors Sam Kujawa, Abigail Jackson, Chase Hollander, Chanakya Pandya
*/ 
public class GameFrame extends JFrame {
    private static GamePanel gamePanel;
    private static long startTime;
    private static long finishTime;
    private static long totalTime; 
    private static long timeGoal = 120000; 
    private static double itemMulti = 0.5;
    private static double timeMulti = 0.75;
    private static JLabel scoreLabel; 
    private static JLabel deathLabel; 
    private static GameFrame frame; 
    private static JButton shoot; 
    static Sound se = new Sound();

    public GameFrame() {
        setLayout(new BorderLayout());
        gamePanel = new GamePanel();
        this.add(gamePanel, BorderLayout.CENTER); 
        setContentPane(gamePanel);
        
        scoreLabel = new JLabel("Score: ");
        deathLabel = new JLabel("Deaths: "); 
        scoreLabel.setForeground(Color.WHITE);
        deathLabel.setForeground(Color.WHITE); 
        add(scoreLabel, BorderLayout.NORTH); 
        add(deathLabel, BorderLayout.NORTH); 
        JLabel timeGoalLabel = new JLabel("Time goal: 2 minutes"); 
        timeGoalLabel.setForeground(Color.WHITE);
        add(timeGoalLabel, BorderLayout.NORTH); 
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Bullet Blitz");  
    }

    public static void main(String[] args) {
        // Display new rules for game
        JOptionPane.showMessageDialog(null, "Rules: "
                +  ("\n1. Hit the asteroids before they reach the turret."
                        + "\n2. Beat the time for extra points! \n3. Hit the hidden item for more extra points!"),
                "Rules",
                JOptionPane.INFORMATION_MESSAGE);
        frame = new GameFrame();
        
        // Game loop
        int delay = 1000 / 60; // 60 FPS
        ActionListener gameLoop = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        // Begins background music by calling sound class
        se.setFile("BlueBoyAdventure.wav");
        se.play();
        se.loop(); 
    }
    
    public static void gameStuff() {
        startTime = System.currentTimeMillis(); 
        
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
            boolean hiddenItemHit;
            if (hiddenItemHit = true) {
                score = (int) (score + (score * itemMulti)); 
            }
            
            JOptionPane.showMessageDialog(null, "You Win! Score: " + score,
                    "SCORE GOAL REACHED",
                    JOptionPane.INFORMATION_MESSAGE); 
            System.exit(0);
        }
        
        int deaths = frame.gamePanel.getDeath();
        deathLabel.setText("Deaths: " + deaths); 
        if (deaths >= 25) {
            JOptionPane.showMessageDialog(null, "You Lose!",
                    "DEATH LIMIT REACHED",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
}
