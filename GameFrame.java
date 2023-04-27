import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;
    static Sound se = new Sound();

    public GameFrame() {
        gamePanel = new GamePanel();
        setContentPane(gamePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Bullet Blitz");

    }

    public static void main(String[] args) {
        // Display new rules for game
        JOptionPane.showMessageDialog(null, "Rules: ",
                "Rules",
                JOptionPane.INFORMATION_MESSAGE);
        GameFrame frame = new GameFrame();

        // Game loop
        int delay = 1000 / 60; // 60 FPS
        ActionListener gameLoop = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.gamePanel.moveEnemies();
                frame.gamePanel.spawnEnemies();
                frame.gamePanel.repaint();
            }
        };
        Timer timer = new Timer(delay, gameLoop);
        timer.start();
        // Begins background music by calling sound class
        se.setFile("BlueBoyAdventure.wav");
        se.play();
        se.loop();
    }
}
