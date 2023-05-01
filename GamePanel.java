import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements MouseMotionListener, MouseListener {
    private static final long serialVersionUID = 1L;
    private ArrayList<Enemies> enemies;
    private ArrayList<Projectiles> projectiles; 
    private Random random;
    private int enemySize;
    private int enemyDeaths;
    private Tank turret; 
    private HiddenItem item; 
    private int deathGoal = 15;
    private long timeGoal = 120000;
    private int scoreGoal = 1000;
    private double itemMulti = 0.5;
    private double timeMulti = 0.75; 
    private static int deathCount = 0;
    private static int score;
    private static boolean hiddenHit; 

    public GamePanel() {
        addMouseMotionListener(this);
        addMouseListener(this);
        enemies = new ArrayList<>();
        projectiles = new ArrayList<Projectiles>();
        random = new Random();
        enemySize = 30; // Adjust this value for desired enemy size
        enemyDeaths = 0;
         // Creates the hidden item.
        int x = (int)(Math.random() * 800); 
        int y = (int)(Math.random() * 600); 
        item = new HiddenItem(x, y, 10, 10, 0.0); 
        
        turret = new Tank(); 
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
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
    
    public boolean detectHiddenItem() {
        ArrayList<Enemies> hiddenItem = new ArrayList<>(); 
        hiddenItem.add(item); 
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
    
    public boolean getHit() {
        return hiddenHit; 
    }

    public void mouseMoved() {
        
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Enemies enemy : enemies) {
            enemy.paint(g);
        }
        for (Projectiles proj: projectiles) {
            proj.paint(g);
        }
        item.paintComponent(g); 
        turret.paintComponent(g); // Paint the turret object

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
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
       Projectiles proj = new Projectiles(turret.angle); 
       projectiles.add(proj);
       proj.move(turret.angle);
       repaint();
       System.out.println("Clicked");
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
}
