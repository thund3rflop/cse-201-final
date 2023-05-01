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
* Creates the framework of the game.
*
* @authors Chase Hollander, Abby Jackson, Sam Kujawa, Chanakya Pandya.
*/ 
public class GameFrame extends JFrame {
    /**
    * Creates the game panel.
    */ 
    private static GamePanel gamePanel;
    
    /** 
    * Beginning time for the system.
    */ 
    private static long startTime;
    
    /**
    * Finishing time for the system.
    */ 
    private static long finishTime;
    
    /**
    * Total time the system was ran.
    */ 
    private static long totalTime;
    
    /**
    * The goal time to beat.
    */ 
    private static long timeGoal = 120000;
    
    /**
    * The score multiplier for hitting the hidden item.
    */ 
    private static double itemMulti = 0.5;
    
    /**
    * The time multiplier for beating the time.
    */ 
    private static double timeMulti = 0.75;
    
    /**
    * Labels for the game.
    */ 
    private static JLabel scoreLabel;
    private static JLabel deathLabel;
    private static GameFrame frame;
    
    /**
    * Sound effects.
    */ 
    static Sound se = new Sound();

    /**
    * Creates the game frame and labels.
    */ 
    public GameFrame() {
        setLayout(new BorderLayout());
        gamePanel = new GamePanel();
        this.add(gamePanel, BorderLayout.CENTER);

        // Creates labels. 
        scoreLabel = new JLabel("Score: ");
        deathLabel = new JLabel("Deaths: ");
        scoreLabel.setForeground(Color.WHITE);
        deathLabel.setForeground(Color.WHITE);

        // Create a new JPanel for the labels
        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        labelPanel.setBackground(Color.BLACK);
        labelPanel.add(scoreLabel);
        labelPanel.add(deathLabel);

        // More labels. 
        JLabel timeGoalLabel = new JLabel("Time goal: 2 minutes");
        timeGoalLabel.setForeground(Color.WHITE);
        labelPanel.add(timeGoalLabel);

        // Add the labelPanel to the top. 
        add(labelPanel, BorderLayout.NORTH);

        // Makes everything visible. 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Bullet Blitz");
    }

    /**
    * A main method that deals with some basic functionality.
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
                frame.gamePanel.detectDeath();
                frame.gamePanel.detectCollision();
                frame.gamePanel.moveEnemies();
                frame.gamePanel.spawnEnemies();
                frame.gamePanel.repaint();
                gameStuff();
                frame.gamePanel.updateProjectiles();

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
    * Deals with more functionality for the game.
    */ 
    public static void gameStuff() {
        // Begins beginning time. 
        startTime = System.currentTimeMillis();

        // Updates score label. 
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

            // Prints winning image. 
            JOptionPane.showMessageDialog(null, "You Win! Score: " + score,
                    "SCORE GOAL REACHED",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        // Checks if the death limit is reached. 
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
