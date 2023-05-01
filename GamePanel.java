import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
* A class that creates the game panel.
* 
* @authors Chase Hollander, Abby Jackson, Sam Kujawa, Chanakya Pandya
*/ 
public class GamePanel extends JPanel implements MouseMotionListener {
    private static final long serialVersionUID = 1L;
    /**
    * Creates array list of enemies.
    */ 
    private ArrayList<Enemies> enemies;
    
    /**
    * Creates array list of projectiles.
    */ 
    private ArrayList<Projectiles> projectiles; 
    
    /**
    * Creates random generator.
    */ 
    private Random random;
    
    /**
    * Enemy's size. 
    */ 
    private int enemySize;
    
    /**
    * Creates turret. 
    */
    private Tank turret; 
    
    /**
    * Creates hidden item. 
    */
    private HiddenItem item; 
    
    /**
    * Players can only have 25 deaths. 
    */ 
    private int deathGoal = 25;
    
    /**
    * Player have to beat the level in 2 minutes for a bonus. 
    */ 
    private long timeGoal = 120000;
    
    /**
    * The base score in which players win the level. 
    */ 
    private int scoreGoal = 1000;
    
    /** 
    * Multiplier for hititng the hidden item. 
    */ 
    private double itemMulti = 0.5;
    
    /** 
    * Multiplier for beating time goal. 
    */ 
    private double timeMulti = 0.75; 
    
    /**
    * Total amount of deaths. 
    */ 
    private static int deathCount = 0;
    
    /**
    * Total score. 
    */ 
    private static int score;
    
    /** 
    * Whether or not hidden item was hit. 
    */ 
    private static boolean hiddenHit; 
    
    /**
    ** Background image for game
    */
    private BufferedImage backgroundImage;


    /**
    * Creates the game panel.
    */ 
    public GamePanel() {
        // Creates mouse motion listener. 
        addMouseMotionListener(this);
        // Initializes arrays. 
        enemies = new ArrayList<>();
        random = new Random();
        enemySize = 30; // Adjust this value for desired enemy size
        enemyDeaths = 0;
        // Creates the hidden item.
        int x = (int)(Math.random() * 800); 
        int y = (int)(Math.random() * 600); 
        item = new HiddenItem(x, y, 10, 10, 0.0); 
        
        // Creates turret and sets backgrgound. 
        turret = new Tank(); 
        setPreferredSize(new Dimension(800, 600));
        // Loads in background image
        try {
            backgroundImage = ImageIO.read(getClass().getResourceAsStream("/space.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
    * Detects if enemies are hit by projectiles. 
    */
    public void detectCollision() {
        // Uses bounds for enemies and projectiles to detect intersection.
        for (int i = 0; i < enemies.size(); i++) {
            Rectangle enemyRec = enemies.get(i).getBounds();
            for (int j = 0; j < projectiles.size(); j++) {
                Rectangle projectileHit = projectiles.get(j).getBounds();
                if (projectileHit.intersects(enemyRec)) {
                    // Projectile has hit an enemy!
                    enemies.get(i).processCollision(enemies, i);
                    projectiles.remove(j);
                    score += 10;
                }
            }
        }
    }
    
    /**
    * Gets the total score.
    *
    * @return The score. 
    */ 
    public int getScore() {
        return this.score; 
    }
    
    /**
    * Detects if enemies reach the turret. 
    */ 
    public void detectDeath() {
        // Uses bounds for enemies and turret to detect death. 
        for (int i = 0; i < enemies.size(); i++) {
            Rectangle enemyRec = enemies.get(i).getBounds();
            Rectangle turretRec = new Rectangle(400, 300); 
            if (turretRec.intersects(enemyRec)) { 
                deathCount++;
                enemies.remove(i); 
            }
        }
    }   
    
    /**
    * Gets number of deaths.
    * 
    * @return The number of deaths. 
    */
    public int getDeath() {
        return this.deathCount; 
    }
    
    /**
    * Detects if the hidden item was hit.
    *
    * @return A boolean stating if hidden item was hit by a projectile. 
    */ 
    public boolean detectHiddenItem() {
        ArrayList<Enemies> hiddenItem = new ArrayList<>(); 
        hiddenItem.add(item); 
        // Gets the bounds of the hidden item and all projectiles. 
        Rectangle hiddenRec = item.getBounds();
        for (int i = 0; i < projectiles.size(); i++) {
            Rectangle projectileHit = projectiles.get(i).getBounds();
            if (projectileHit.intersects(hiddenRec)) {
                hiddenHit = true; 
                hiddenItem.remove(item); 
            } else {
                hiddenHit = false; 
            }
        }
        return hiddenHit; 
    }
    
    /**
    * Gets if the hidden item was hit or not.
    * 
    * @return Whether or not the player hit the hidden item.
    */ 
    public boolean getHit() {
        return hiddenHit; 
    }

    /**
    * Determines if mouse moved.
    */ 
    public void mouseMoved() {
        
    }
    
    /**
    * Overriden class that paints everything.
    */ 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        // Paints the enemies. 
        for (Enemies enemy : enemies) {
            enemy.paint(g);
        }
        // Paints the hidden item. 
        item.paintComponent(g); 
        // Paints the turret object
        turret.paintComponent(g); 
    }

    /**
    * Spawn enemies.
    */ 
    public void spawnEnemies() {
        if (random.nextInt(100) < .5) { // Adjust this value for desired spawn rate
            int x, y;
            int edge = random.nextInt(4);
            if (edge == 0) { // top
                x = random.nextInt(getWidth() - enemySize);
                y = getHeight() - enemySize;
            } else if (edge == 1) { // right
                x = getWidth() - enemySize;
                y = random.nextInt(getHeight() - enemySize);
            } else if (edge == 2) { // bottom
                x = random.nextInt(getWidth() - enemySize);
                y = getHeight() - enemySize;
            } else { // left
                x = getWidth() - enemySize;
                y = random.nextInt(getHeight() - enemySize);
            }

            Enemies newEnemy = new CustomEnemy(x, y, enemySize, enemySize, 1.5);
            enemies.add(newEnemy);
        }
    }

    /**
    * Move the enemies.
    */ 
    public void moveEnemies() {
        double centerX = getWidth() / 2.0;
        double centerY = getHeight() / 2.0;

        for (Enemies enemy : enemies) {
            double dx = centerX - (enemy.getBounds().x + enemy.getBounds().width / 2.0);
            double dy = centerY - (enemy.getBounds().y + enemy.getBounds().height / 2.0);
            double distance = Math.sqrt(dx * dx + dy * dy);
            double moveX = dx * enemy.getEnemySpeed() / distance;
            double moveY = dy * enemy.getEnemySpeed() / distance;
            if (enemy instanceof CustomEnemy) {
                ((CustomEnemy) enemy).move(moveX, moveY);
            }
        }
    }

    // Overriden methods for the mouse motion listener. 
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        turret.angle = Math.atan2(e.getY() - turret.rotationPoint.getY(), e.getX() - turret.rotationPoint.getX());
        // Repaint the panel with the new rotation
        repaint();
        
    }   
}
