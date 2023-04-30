import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Enemies> enemies;
	private ArrayList<Projectiles> projectiles; 
	private Random random;
	private int enemySize;
	private HiddenItem item; 
	private Tank turret; 
    private int deathGoal = 15;
    private long timeGoal = 120000;
    private int scoreGoal = 1000;
    private double itemMulti = 0.5;
    private double timeMulti = 0.75; 
    private static int deathCount = 0;
    private static int score;
    

	public GamePanel() {
		random = new Random();
		enemies = new ArrayList<>(); 
		enemySize = 30; // Adjust this value for desired enemy size
		
	    
		// Initialize and set up the turret object
//        int turretWidth = 30;
//        int turretHeight = 30;
//        int turretX = (800 - turretWidth) / 2;
//        int turretY = (600 - turretHeight) / 2;
//        turret = new Tank(turretX, turretY, turretHeight, turretWidth, 0, Color.GREEN, Color.RED);
//        turret.addMouseMotionListener(turret); // Add MouseMotionListener to turret
		turret = new Tank();
		
		// Creates the hidden item.
		int x = (int)(Math.random() * 800); 
		int y = (int)(Math.random() * 600); 
		item = new HiddenItem(x, y, 20, 20, 0.0); 

        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Enemies enemy : enemies) {
			enemy.paint(g);
		}
        //turret.paintComponent(g); // Paint the turret object
		// Paints the hidden item. 
        item.paintComponent(g); 
	}

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
	
	// Detects if enemies are hit by projectiles. 
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
	
	public int getScore() {
        return this.score; 
    }
	
	// Detects if enemies reach the turret. 
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
	
	public int getDeath() {
	    return this.deathCount; 
	}
} 
