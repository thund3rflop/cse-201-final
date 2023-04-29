import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ArrayList<Enemies> enemies;
	private Random random;
	private int enemySize;
	private int enemyDeaths;
	private Tank turret; 

	public GamePanel() {
		enemies = new ArrayList<>();
		random = new Random();
		enemySize = 30; // Adjust this value for desired enemy size
		enemyDeaths = 0;
		
	    
		// Initialize and set up the turret object
//        int turretWidth = 30;
//        int turretHeight = 30;
//        int turretX = (800 - turretWidth) / 2;
//        int turretY = (600 - turretHeight) / 2;
//        turret = new Tank(turretX, turretY, turretHeight, turretWidth, 0, Color.GREEN, Color.RED);
//        turret.addMouseMotionListener(turret); // Add MouseMotionListener to turret
		turret = new Tank();

        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Enemies enemy : enemies) {
			enemy.paint(g);
		}
        //turret.paintComponent(g); // Paint the turret object

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
		deathProcess();

	}

	public void deathProcess() {
		List<Enemies> deadEnemies = new ArrayList<>();

		for (Enemies enemy : enemies) {
			double centerX = getWidth() / 2.0;
			double centerY = getHeight() / 2.0;

			if (enemy.isCenterReached(centerX, centerY, enemySize / 2)) {
				deadEnemies.add(enemy);
			}
		}

		for (Enemies enemy : deadEnemies) {
			enemies.remove(enemy);
			enemyDeaths++;

			if (enemyDeaths >= 200) {
				JOptionPane.showMessageDialog(null, "Quit ", "Quit", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
				break;
			}
		}
	}
	
	
	

}
