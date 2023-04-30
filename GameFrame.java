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

public class GameFrame extends JFrame {
    private static GamePanel gamePanel;
    
    /**
     * The starting time of the system. 
     */
    private static long startTime;
    
    /**
     * The finishing time of the system.
     */
    private static long finishTime;
    
    /**
     * The total time on the system.
     */
    private static long currentTime; 
    
    private static JLabel scoreLabel; 
    private static JLabel deathLabel; 
    private static GameFrame frame; 
    
    static Sound se = new Sound();

    public GameFrame() {
        setLayout(new FlowLayout());
        gamePanel = new GamePanel();
        this.add(gamePanel, BorderLayout.CENTER); 
        setContentPane(gamePanel);
        
        scoreLabel = new JLabel("Score: ");
        deathLabel = new JLabel("Deaths: "); 
        scoreLabel.setForeground(Color.WHITE);
        deathLabel.setForeground(Color.WHITE); 
        add(scoreLabel, BorderLayout.NORTH); 
        add(deathLabel, BorderLayout.NORTH); 
        
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
        int score = frame.gamePanel.getScore();
        scoreLabel.setText("Score: " + score);
        if (score > 1000) {
            JOptionPane.showMessageDialog(null, "You Win! Score: " + score,
                    "Game Finished Message",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        
        int deaths = frame.gamePanel.getDeath();
        deathLabel.setText("Deaths: " + deaths); 
        if (deaths > 25) {
            JOptionPane.showMessageDialog(null, "You Lose!",
                    "Game Finished Message",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
}
