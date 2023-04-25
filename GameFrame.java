import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameFrame extends JFrame {
    private GamePanel gamePanel;

    public GameFrame() {
        gamePanel = new GamePanel();
        setContentPane(gamePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
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
    }
}
